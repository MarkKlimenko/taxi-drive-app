package systems.vostok.taxi.drive.app.operation;

import systems.vostok.taxi.drive.app.dao.domain.operation.OperationContext;
import systems.vostok.taxi.drive.app.util.exception.OperationExecutionException;

public interface CoreOperation {
    String getOperationName();

    String getOperationTimeout();

    Object enroll(OperationContext context);

    Object rollback(OperationContext context);

    default Object breakEnroll(OperationContext context) {
        throw new OperationExecutionException();
    }

    default Object breakRollback(OperationContext context) {
        throw new OperationExecutionException();
    }
}