package systems.vostok.taxi.drive.app.util.exception

import org.springframework.amqp.AmqpRejectAndDontRequeueException
import systems.vostok.taxi.drive.app.dao.domain.operation.OperationContext
import systems.vostok.taxi.drive.app.operation.OperationDirection

class OperationExecutionException extends AmqpRejectAndDontRequeueException {
    OperationExecutionException() {
        super('Operation execution exception')
    }

    OperationExecutionException(String message) {
        super(message)
    }

    OperationExecutionException(Throwable cause) {
        super(cause)
    }


    static OperationExecutionException unsupportedOperationDirectionException(OperationDirection direction) {
        new OperationExecutionException("Unsupported operation direction: { ${direction.name()} }")
    }

    static OperationExecutionException noOperationExecutorException(String operationName) {
        new OperationExecutionException("No operation Executor for target Operation: { ${operationName} }")
    }

    static OperationExecutionException noContextMessageException(UUID contextMessageId) {
        new OperationExecutionException("No such Context message with ID: { ${contextMessageId.toString()} }")
    }

    static OperationExecutionException noContextMessageException(String contextMessageId) {
        new OperationExecutionException("No such Context message with ID: { ${contextMessageId} }")
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
