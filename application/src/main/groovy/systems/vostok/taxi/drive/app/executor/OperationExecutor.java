package systems.vostok.taxi.drive.app.executor;

import systems.vostok.taxi.drive.app.dao.domain.operation.OperationRequest;

public interface OperationExecutor {
    Object execute(OperationRequest request);
}
