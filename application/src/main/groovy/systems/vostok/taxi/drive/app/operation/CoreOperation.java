package systems.vostok.taxi.drive.app.operation;

import systems.vostok.taxi.drive.app.dao.domain.operation.OperationContext;

public interface CoreOperation {
    String getOperationName();

    String getOperationTimeout();

    Object enroll(OperationContext context);

    Object rollback(OperationContext context);

    default Object breakEnroll(OperationContext context) {
        return null;
    }

    default Object breakRollback(OperationContext context) {
        return null;

    }
}