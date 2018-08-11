package systems.vostok.taxi.drive.app.operation;

import systems.vostok.taxi.drive.app.dao.domain.operation.OperationContext;
import systems.vostok.taxi.drive.app.dao.domain.operation.OperationName;

public interface Operation {
    OperationName getOperationName();

    Object enroll(OperationContext context);

    Object rollback(OperationContext context);
}