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

    OperationResponse execute(OperationDirections direction, OperationRequest request) {
        OperationContext operationContext = createOperationContext(direction, request)

        try {
            OperationExecutor executor = operationToExecutorMap[request.operationName]
            OperationResponse operationResponse = null

            if (!executor) {
                throw noOperationExecutorException(request.operationName)
            }

            if (direction == ENROLL) {
                operationResponse = operationManager.enrollOperation(executor, operationContext)
            } else if (direction == ROLLBACK) {
                operationResponse = operationManager.rollbackOperation(executor, operationContext)
            } else {
                throw unsupportedOperationDirectionException(direction)
            }

            contextHelper.setSuccess(operationContext)
            operationResponse
        } catch (Exception e) {
            contextHelper.setFailed(operationContext)
            throw new OperationExecutionException(e)
        }
    }

    protected OperationContext createOperationContext(OperationDirections direction, OperationRequest request) {
        new OperationContext(
                contextHelper: contextHelper,
                operationRequest: request,
                contextMessage: contextHelper.createContextMessage(direction, request)
        )
    }
}
