package systems.vostok.taxi.drive.app.executor;

import systems.vostok.taxi.drive.app.dao.domain.operation.OperationContext;

import java.util.Set;

public interface OperationExecutor {
    String getExecutorName();

    Set<String> refreshOperations();

    Object enrollOperation(OperationContext context);
    Object rollbackOperation(OperationContext context);

    Object breakEnrollOperation(OperationContext context);
    Object breakRollbackOperation(OperationContext context);
}
