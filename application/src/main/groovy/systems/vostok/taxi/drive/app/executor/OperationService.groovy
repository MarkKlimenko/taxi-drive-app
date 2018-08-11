package systems.vostok.taxi.drive.app.executor

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import systems.vostok.taxi.drive.app.dao.domain.operation.OperationRequest
import systems.vostok.taxi.drive.app.util.constant.OperationDirection
import systems.vostok.taxi.drive.app.util.exception.OperationExecutionException

import static systems.vostok.taxi.drive.app.util.constant.OperationDirection.ENROLL
import static systems.vostok.taxi.drive.app.util.constant.OperationDirection.ROLLBACK

@Service
class OperationService {
    @Autowired
    OperationExecutor coreOperationExecutor

    Operation process(String direction, OperationRequest operationRequest) {
        if(direction == ENROLL.type) {
            executeOperation(operationRequest)
        } else if(direction == ROLLBACK.type) {
            executeRollback(operationRequest)
        } else {
            throw new OperationExecutionException("Not supported direction type { ${direction} }")
        }
    }

    Object executeOperation(OperationRequest operationRequest) {
        coreOperationExecutor.execute(operationRequest)
    }

    Object executeRollback(OperationRequest operationRequest) {
        //coreOperationExecutor.execute(operationRequest)
    }
}
