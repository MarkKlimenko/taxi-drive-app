package systems.vostok.taxi.drive.app.executor.impl

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import systems.vostok.taxi.drive.app.dao.domain.operation.OperationContext
import systems.vostok.taxi.drive.app.dao.domain.operation.OperationName
import systems.vostok.taxi.drive.app.dao.domain.operation.OperationRequest
import systems.vostok.taxi.drive.app.dao.entity.ContextMessage
import systems.vostok.taxi.drive.app.dao.repository.impl.ContextMessageRepository
import systems.vostok.taxi.drive.app.executor.ContextHelper
import systems.vostok.taxi.drive.app.executor.OperationExecutor
import systems.vostok.taxi.drive.app.operation.Operation

import static systems.vostok.taxi.drive.app.dao.domain.operation.OperationState.CANCELED_OPERATION_STATE
import static systems.vostok.taxi.drive.app.dao.domain.operation.OperationState.SUCCESS_OPERATION_STATE

@Service
class CoreOperationExecutor implements OperationExecutor {
    @Autowired
    List<Operation> operations

    @Autowired
    ContextHelper contextHelper

    @Autowired
    ContextMessageRepository contextMessageRepository

    final Map<String, Closure> directionMap = [
            enroll  : { Operation operation, OperationContext context -> executeEnroll(operation, context) },
            rollback: { Operation operation, OperationContext context -> executeRollback(operation, context) }
    ]

    Object execute(OperationRequest request) {
        Operation operation = getOperation(request.operationName)
        String direction = request.direction

        OperationContext operationContext = new OperationContext.Builder()
                .contextHelper(contextHelper)
                .operationRequest(request)
                .contextMessage(contextHelper.createContextMessage(request))
                .build()

        try {
            Object operationResult = directionMap[direction].call(operation, operationContext)
            contextHelper.setSuccess(operationContext)
            operationResult
        } catch (Throwable e) {
            contextHelper.setContext(operationContext, e.message)
            contextHelper.setFail(operationContext)
            throw new Throwable(e)
        }
    }

    private Object executeEnroll(Operation operation, OperationContext context) {
        operation.enroll(context)
    }

    private Object executeRollback(Operation operation, OperationContext context) {
        def getRollbackContextMessage = {
            ContextMessage contextMessage = contextMessageRepository.findById(context.operationRequest.id).get()
            assert contextMessage.state == SUCCESS_OPERATION_STATE: 'Rollback rejected: not suitable state for rollback'
            contextMessage
        }

        def formOperationContext = { ContextMessage rollbackContextMessage ->
            context.rollbackContextMessage = rollbackContextMessage
            rollbackContextMessage
        }

        def executeRollback = { ContextMessage rollbackContextMessage ->
            operation.rollback(context)
            rollbackContextMessage
        }

        def setCanceled = { ContextMessage rollbackContextMessage ->
            rollbackContextMessage.with { it.state = CANCELED_OPERATION_STATE; it }
                    .with(contextMessageRepository.&save)
            rollbackContextMessage
        }

        getRollbackContextMessage()
                .with(formOperationContext)
                .with(executeRollback)
                .with(setCanceled)
    }

    private Operation getOperation(String operationName) {
        operations.find { it.operationName == OperationName.get(operationName) }
    }
}
