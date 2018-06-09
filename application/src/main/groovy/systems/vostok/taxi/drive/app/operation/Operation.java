package systems.vostok.taxi.drive.app.operation;

import systems.vostok.taxi.drive.app.dao.domain.OperationRequest;
import systems.vostok.taxi.drive.app.dao.entity.ContextMessage;

public interface Operation {
    String getOperationName();
    
    Object enroll(OperationRequest request);
    Object rollback(OperationRequest request, ContextMessage contextMessage);
}