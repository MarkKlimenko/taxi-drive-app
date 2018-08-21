package systems.vostok.taxi.drive.app.executor

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import systems.vostok.taxi.drive.app.dao.domain.operation.OperationResponse
import systems.vostok.taxi.drive.app.operation.OperationFlowTestUtil
import systems.vostok.taxi.drive.app.util.exception.OperationExecutionException

import static org.junit.jupiter.api.Assertions.assertEquals
import static org.junit.jupiter.api.Assertions.assertThrows
import static org.junit.jupiter.api.Assertions.assertTrue

@ExtendWith(SpringExtension.class)
@SpringBootTest
@DisplayName('Operation service test')
class OperationServiceTestIntegration {
    @Autowired
    OperationFlowTestUtil operationUtil

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
}
