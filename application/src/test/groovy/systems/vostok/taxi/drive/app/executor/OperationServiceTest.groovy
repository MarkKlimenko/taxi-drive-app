package systems.vostok.taxi.drive.app.executor

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import systems.vostok.taxi.drive.app.operation.OperationFlowTestUtil
import systems.vostok.taxi.drive.app.util.exception.OperationExecutionException

import static org.junit.jupiter.api.Assertions.assertEquals
import static org.junit.jupiter.api.Assertions.assertThrows

@ExtendWith(SpringExtension.class)
@SpringBootTest
@DisplayName('Operation service test')
class OperationServiceTest {
    @Autowired
    OperationFlowTestUtil operationUtil

    @Test
    @DisplayName('Operation timeout test')
    void operationTimeoutTest() {
        operationUtil.enrollOperation('CORE_TIMEOUT_OPERATION', [:])
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
