package systems.vostok.taxi.drive.app.util.exception

import systems.vostok.taxi.drive.app.dao.domain.operation.OperationDirections

class OperationExecutionException extends RuntimeException {
    OperationExecutionException(String message) {
        super(message)
    }


    static OperationExecutionException unsupportedOperationDirectionException(OperationDirections direction) {
        new OperationExecutionException("Unsupported operation direction: { ${direction.type} }")
    }

    static OperationExecutionException noContextMessageException(UUID contextMessageId) {
        new OperationExecutionException("No such Context message with ID: { ${contextMessageId.toString()} }")
    }

    static OperationExecutionException contextMessageStateException(String contextMessageState) {
        new OperationExecutionException("Unappropriate Context message state: { ${contextMessageState} }")
    }
}
