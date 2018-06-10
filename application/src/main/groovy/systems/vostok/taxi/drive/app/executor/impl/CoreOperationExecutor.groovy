package systems.vostok.taxi.drive.app.executor.impl

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import systems.vostok.taxi.drive.app.dao.domain.OperationRequest
import systems.vostok.taxi.drive.app.dao.entity.ContextMessage
import systems.vostok.taxi.drive.app.dao.repository.impl.ContextMessageRepository
import systems.vostok.taxi.drive.app.executor.ContextHelper
import systems.vostok.taxi.drive.app.executor.OperationExecutor
import systems.vostok.taxi.drive.app.operation.Operation

import static systems.vostok.taxi.drive.app.util.constant.OperationState.CANCELED_OPERATION_STATE
import static systems.vostok.taxi.drive.app.util.constant.OperationState.SUCCESS_OPERATION_STATE

@Service
class CoreOperationExecutor implements OperationExecutor {
    @Autowired
    List<Operation> operations

    @Autowired
    ContextHelper contextHelper

    @Autowired
    ContextMessageRepository contextMessageRepository

    Map<String, Closure> directionMap = [
            enroll  : { Operation operation, OperationRequest request -> executeEnroll(operation, request) },
            rollback: { Operation operation, OperationRequest request -> executeRollback(operation, request) }
    ]

    Object execute(OperationRequest request) {
        Operation operation = getOperation(request.operationName)
        String direction = request.direction

        try {
            Object operationContext = directionMap[direction].call(operation, request)
            contextHelper.reportSuccess(request, operationContext)
            operationContext
        } catch (Throwable e) {
            contextHelper.reportFail(request, e.message)
            throw new Throwable(e)
        }
    }

    private Object executeEnroll(Operation operation, OperationRequest request) {
        operation.enroll(request)
    }

    private Object executeRollback(Operation operation, OperationRequest request) {
        ContextMessage contextMessage = contextMessageRepository.findById(request.id)
        assert contextMessage.state == SUCCESS_OPERATION_STATE: 'Rollback rejected: not suitable state for rollback'

        operation.rollback(request, contextMessage)

        contextMessage.with { it.state = CANCELED_OPERATION_STATE; it }
                .with(contextMessageRepository.&save)
    }

    private Operation getOperation(String operationName) {
        operations.find { it.operationName == operationName }
    }
}
