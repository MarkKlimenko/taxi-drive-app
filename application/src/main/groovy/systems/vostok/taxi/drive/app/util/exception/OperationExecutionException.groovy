package systems.vostok.taxi.drive.app.util.exception

import systems.vostok.taxi.drive.app.dao.domain.operation.OperationContext
import systems.vostok.taxi.drive.app.dao.domain.operation.OperationDirections

class OperationExecutionException extends RuntimeException {
    OperationExecutionException(String message) {
        super(message)
    }

    OperationExecutionException(Throwable cause) {
        super(cause)
    }


    static OperationExecutionException unsupportedOperationDirectionException(OperationDirections direction) {
        new OperationExecutionException("Unsupported operation direction: { ${direction.type} }")
    }

    static OperationExecutionException noOperationExecutorException(String operationName) {
        new OperationExecutionException("No operation Executor for target Operation: { ${operationName} }")
    }

    static OperationExecutionException noContextMessageException(UUID contextMessageId) {
        new OperationExecutionException("No such Context message with ID: { ${contextMessageId.toString()} }")
    }

    static OperationExecutionException contextMessageStateException(String contextMessageState) {
        new OperationExecutionException("Unappropriate Context message state: { ${contextMessageState} }")
    }

    static OperationExecutionException rollbackOperationNamesException(OperationContext context) {
        new OperationExecutionException(
                "Rollback Operation names are not the same: " +
                        "{ ${context.rolledBackContextMessage.operationName} } != { ${context.operationRequest.operationName} }"
        )
    }
}
