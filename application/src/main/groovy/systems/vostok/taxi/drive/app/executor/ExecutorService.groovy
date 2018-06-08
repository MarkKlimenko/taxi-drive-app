package systems.vostok.taxi.drive.app.executor

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import systems.vostok.taxi.drive.app.dao.domain.OperationRequest

@Service
class ExecutorService {
    @Autowired
    OperationExecutor coreOperationExecutor

    Object executeOperation(OperationRequest operationRequest) {
        coreOperationExecutor.execute(operationRequest)
    }
}
