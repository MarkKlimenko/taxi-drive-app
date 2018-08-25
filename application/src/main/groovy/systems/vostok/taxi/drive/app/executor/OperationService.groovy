package systems.vostok.taxi.drive.app.executor

import org.springframework.amqp.core.AmqpTemplate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import systems.vostok.taxi.drive.app.configuration.MessagingConfig
import systems.vostok.taxi.drive.app.dao.domain.operation.OperationContext
import systems.vostok.taxi.drive.app.dao.domain.operation.OperationResponse
import systems.vostok.taxi.drive.app.operation.OperationRequest
import systems.vostok.taxi.drive.app.util.exception.OperationExecutionException

import javax.annotation.PostConstruct

import static systems.vostok.taxi.drive.app.dao.domain.operation.OperationStates.FAILED
import static systems.vostok.taxi.drive.app.dao.domain.operation.OperationStates.IN_PROCESS
import static systems.vostok.taxi.drive.app.dao.domain.operation.OperationStates.PENDING
import static systems.vostok.taxi.drive.app.dao.domain.operation.OperationStates.SUCCESS
import static systems.vostok.taxi.drive.app.operation.OperationDirection.enroll
import static systems.vostok.taxi.drive.app.operation.OperationDirection.rollback
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

    @Autowired
    AmqpTemplate amqpTemplate

    @Autowired
    MessagingConfig messagingConfig

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
        OperationContext operationContext = contextHelper.createPrimaryOperationContext(request)

        try {
            if (request.async) {
                executeAsync(operationContext)
            } else {
                executeSync(operationContext)
            }
        } catch (Exception e) {
            contextHelper.setState(operationContext, FAILED)
            throw new OperationExecutionException(e)
        }
    }

    OperationResponse executeSync(OperationContext operationContext) {
        OperationExecutor executor = operationToExecutorMap[operationContext.operationRequest.operationName]
        OperationResponse operationResponse = null

        if (!executor) {
            throw noOperationExecutorException(operationContext.operationRequest.operationName)
        }

        contextHelper.setState(operationContext, IN_PROCESS)

        if (operationContext.direction == enroll) {
            operationResponse = operationManager.enrollOperation(executor, operationContext)
        } else if (operationContext.direction == rollback) {
            operationResponse = operationManager.rollbackOperation(executor, operationContext)
        } else {
            throw unsupportedOperationDirectionException(operationContext.direction)
        }

        contextHelper.setState(operationContext, SUCCESS)
        operationResponse
    }

    OperationResponse executeAsync(OperationContext operationContext) {
        OperationRequest operationRequestWithId = OperationRequest.newBuilder(operationContext.operationRequest)
                .setId(operationContext.contextMessage.id as String)
                .build()

        amqpTemplate.convertAndSend(messagingConfig.queue.operationExecutor, operationRequestWithId.toByteBuffer().array())
        contextHelper.setState(operationContext, PENDING)

        OperationManager.createOperationResponse(operationContext, null)
    }
}
