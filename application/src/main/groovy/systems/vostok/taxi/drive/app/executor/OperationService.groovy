package systems.vostok.taxi.drive.app.executor

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import systems.vostok.taxi.drive.app.dao.domain.operation.OperationContext
import systems.vostok.taxi.drive.app.dao.domain.operation.OperationDirections
import systems.vostok.taxi.drive.app.dao.domain.operation.OperationRequest
import systems.vostok.taxi.drive.app.dao.domain.operation.OperationResponse
import systems.vostok.taxi.drive.app.util.exception.OperationExecutionException

import javax.annotation.PostConstruct

import static OperationDirections.ENROLL
import static OperationDirections.ROLLBACK
import static systems.vostok.taxi.drive.app.util.exception.OperationExecutionException.noOperationExecutorException
import static systems.vostok.taxi.drive.app.util.exception.OperationExecutionException.unsupportedOperationDirectionException

@Service
class OperationService {
    private static Map<String, OperationExecutor> operationToExecutorMap = null

    @Autowired
    List<OperationExecutor> operationExecutors

    @Autowired
    ContextHelper contextHelper

    @Autowired
    OperationManager operationManager

    @PostConstruct
    void init() {
        operationToExecutorMap = getOperationToExecutorMap()
    }

    Map<String, OperationExecutor> getOperationToExecutorMap() {
        operationExecutors.collect { executor ->
            executor.refreshOperations()
                    .collectEntries { [(it): executor] }
        }.collectEntries()
    }

    OperationResponse execute(OperationRequest request) {
        OperationContext operationContext = createOperationContext(request)

        try {
            OperationResponse operationResponse = null

            if (request.async) {
                operationResponse = executeAsync(operationContext)
            } else {
                operationResponse = executeSync(operationContext)
            }

            contextHelper.setSuccess(operationContext)
            operationResponse
        } catch (Exception e) {
            contextHelper.setFailed(operationContext)
            throw new OperationExecutionException(e)
        }
    }

    protected OperationContext createOperationContext(OperationRequest request) {
        new OperationContext(
                contextHelper: contextHelper,
                operationRequest: request,
                direction: request.direction,
                contextMessage: contextHelper.createContextMessage(request.direction, request)
        )
    }

    OperationResponse executeSync (OperationContext operationContext) {
        OperationExecutor executor = operationToExecutorMap[operationContext.operationRequest.operationName]

        if (!executor) {
            throw noOperationExecutorException(operationContext.operationRequest.operationName)
        }

        if (operationContext.direction == ENROLL) {
            operationManager.enrollOperation(executor, operationContext)
        } else if (operationContext.direction == ROLLBACK) {
            operationManager.rollbackOperation(executor, operationContext)
        } else {
            throw unsupportedOperationDirectionException(operationContext.direction)
        }
    }

    OperationResponse executeAsync (OperationContext operationContext) {
        null
    }
}
