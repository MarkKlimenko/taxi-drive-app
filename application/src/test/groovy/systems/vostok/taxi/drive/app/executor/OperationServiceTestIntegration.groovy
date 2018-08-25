package systems.vostok.taxi.drive.app.executor

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import systems.vostok.taxi.drive.app.dao.domain.operation.OperationResponse
import systems.vostok.taxi.drive.app.dao.entity.ContextMessage
import systems.vostok.taxi.drive.app.dao.repository.impl.ContextMessageRepository
import systems.vostok.taxi.drive.app.operation.OperationFlowTestUtil
import systems.vostok.taxi.drive.app.util.exception.OperationExecutionException

import static org.junit.jupiter.api.Assertions.*
import static systems.vostok.taxi.drive.app.test.Check.recheck

@ExtendWith(SpringExtension.class)
@SpringBootTest
@DisplayName('Operation service test')
class OperationServiceTestIntegration {
    @Autowired
    OperationFlowTestUtil operationUtil

    @Autowired
    ContextMessageRepository contextMessageRepository

    @Test
    @DisplayName('Operation timeout test')
    void operationTimeoutTest() {
        OperationResponse or = operationUtil.enrollOperation('CORE_TIMEOUT_OPERATION', [:])
        assertTrue(or.body instanceof BigDecimal)
        assertEquals(new BigDecimal(5), or.body)
    }

    @Test
    @DisplayName('Unhandled operation timeout test')
    void unhandledOperationTimeoutTest() {
        OperationExecutionException e = assertThrows(
                OperationExecutionException.class,
                { operationUtil.enrollOperation('CORE_UNHANDLED_TIMEOUT_OPERATION', [:]) }
        )

        assertEquals('enrollOperation timed-out and fallback failed.', e.cause.message)
    }

    @Test
    @DisplayName('Operation exception test')
    void operationExceptionTest() {
        OperationExecutionException e = assertThrows(
                OperationExecutionException.class,
                { operationUtil.enrollOperation('CORE_EXCEPTION_OPERATION', [:]) }
        )

        assertEquals('Enroll operation exception in ExceptionOperation', e.cause.message)
    }

    @Test
    @DisplayName('Async operation execution')
    void asyncOperationExecution() {
        OperationResponse operationResponse = operationUtil.enrollAsyncOperation('CORE_SIMPLE_OPERATION', [sum: 5])
        ContextMessage contextMessage = null

        recheck(5) {
            contextMessage = contextMessageRepository.findOneById(operationResponse.id)
                    .orElseThrow({ new RuntimeException('Context message is not found') })

            assertEquals('success', contextMessage.state)
        }

        assertEquals('25', contextMessage.context)
    }
}
