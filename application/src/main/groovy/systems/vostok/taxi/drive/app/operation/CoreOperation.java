package systems.vostok.taxi.drive.app.operation;

import systems.vostok.taxi.drive.app.dao.domain.operation.OperationContext;
import systems.vostok.taxi.drive.app.dao.domain.operation.CoreOperationNames;

public interface CoreOperation {
    CoreOperationNames getOperationName();

    String getOperationTimeout();

    Object enroll(OperationContext context);

    Object rollback(OperationContext context);

    Object breakOperation(OperationContext context);
}