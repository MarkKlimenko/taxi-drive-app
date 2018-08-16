package systems.vostok.taxi.drive.app.executor.impl

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import systems.vostok.taxi.drive.app.dao.domain.operation.OperationContext
import systems.vostok.taxi.drive.app.dao.repository.impl.ContextMessageRepository
import systems.vostok.taxi.drive.app.executor.ContextHelper
import systems.vostok.taxi.drive.app.executor.OperationExecutor
import systems.vostok.taxi.drive.app.operation.CoreOperation

import static systems.vostok.taxi.drive.app.dao.domain.operation.OperationExecutorNames.CORE_OPERATION_EXECUTOR

@Service
class CoreOperationExecutor implements OperationExecutor {
    String executorName = CORE_OPERATION_EXECUTOR.name

    private static Map<String, CoreOperation> operationNameToOperationMap = null

    @Autowired
    List<CoreOperation> operations

    @Autowired
    ContextHelper contextHelper

    @Autowired
    ContextMessageRepository contextMessageRepository

    @Override
    Set<String> refreshOperations() {
        Map<String, CoreOperation> map = operations.collectEntries { [it.operationName, it] }

        operationNameToOperationMap = Collections.unmodifiableMap(map)
        operationNameToOperationMap.keySet()
    }

    @Override
    Object enrollOperation(OperationContext context) {
        operationNameToOperationMap[context.contextMessage.operationName]
                .enroll(context)
    }

    @Override
    Object rollbackOperation(OperationContext context) {
        operationNameToOperationMap[context.contextMessage.operationName]
                .rollback(context)
    }
}
