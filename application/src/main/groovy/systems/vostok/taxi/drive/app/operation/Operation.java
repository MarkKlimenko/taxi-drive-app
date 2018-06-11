package systems.vostok.taxi.drive.app.operation;

import systems.vostok.taxi.drive.app.dao.domain.operation.OperationContext;
import systems.vostok.taxi.drive.app.dao.domain.operation.OperationRequest;
import systems.vostok.taxi.drive.app.dao.entity.ContextMessage;

public interface Operation {
    String getOperationName();
    
    Object enroll(OperationContext context);
    Object rollback(OperationContext context);
}