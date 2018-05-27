package systems.vostok.taxi.drive.app.operation;

import systems.vostok.taxi.drive.app.dao.domain.OperationRequest;

public interface Operation {
    String getOperationName();
    
    Object enroll(OperationRequest request);
    Object rollback(OperationRequest request);
}