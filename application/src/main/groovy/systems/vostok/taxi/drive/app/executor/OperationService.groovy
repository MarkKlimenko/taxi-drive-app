package systems.vostok.taxi.drive.app.executor

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import systems.vostok.taxi.drive.app.dao.domain.operation.OperationRequest
import systems.vostok.taxi.drive.app.dao.domain.operation.OperationResponse
import systems.vostok.taxi.drive.app.dao.domain.operation.OperationDirection
import systems.vostok.taxi.drive.app.util.exception.OperationExecutionException

import static OperationDirection.ENROLL
import static OperationDirection.ROLLBACK

@Service
class OperationService {
    @Autowired
    OperationExecutor coreOperationExecutor

    OperationResponse process(OperationDirection directionType, OperationRequest operationRequest) {
        if (directionType == ENROLL) {
            return executeOperation(operationRequest)
        } else if (directionType == ROLLBACK) {
            return executeRollback(operationRequest)
        } else {
            throw new OperationExecutionException("Unsupported Operation direction: { ${directionType} }")
        }
    }

    OperationResponse executeOperation(OperationRequest operationRequest) {
        // coreOperationExecutor.execute(operationRequest)
    }

    OperationResponse executeRollback(OperationRequest operationRequest) {
        // coreOperationExecutor.execute(operationRequest)
    }
}
