package systems.vostok.taxi.drive.app.executor

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import systems.vostok.taxi.drive.app.dao.domain.operation.OperationContext
import systems.vostok.taxi.drive.app.dao.domain.operation.OperationDirections
import systems.vostok.taxi.drive.app.dao.domain.operation.OperationRequest
import systems.vostok.taxi.drive.app.dao.domain.operation.OperationResponse
import systems.vostok.taxi.drive.app.dao.repository.impl.ContextMessageRepository
import systems.vostok.taxi.drive.app.util.exception.OperationExecutionException

import javax.annotation.PostConstruct

import static OperationDirections.ENROLL
import static OperationDirections.ROLLBACK
import static systems.vostok.taxi.drive.app.dao.domain.operation.OperationStates.*
import static systems.vostok.taxi.drive.app.util.ContentTypeConverter.toMap
import static systems.vostok.taxi.drive.app.util.exception.OperationExecutionException.*

@Service
class OperationService {
    private static Map<String, OperationExecutor> operationToExecutorMap = null

    @Autowired
    List<OperationExecutor> operationExecutors

    @Autowired
    ContextHelper contextHelper

    @Autowired
    ContextMessageRepository contextMessageRepository

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

            if (direction == ENROLL) {
                operationResponse = enrollOperation(executor, operationContext)
            } else if (direction == ROLLBACK) {
                operationResponse = rollbackOperation(executor, operationContext)
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

    protected OperationResponse enrollOperation(OperationExecutor executor, OperationContext context) {
        createOperationResponse(context, executor.enrollOperation(context))
    }

    protected OperationResponse rollbackOperation(OperationExecutor executor, OperationContext context) {
        try {
            UUID rolledBackContextMessageId = UUID.fromString(toMap(context.operationRequest.body).id)
            context.rolledBackContextMessage = contextMessageRepository.findOneById(rolledBackContextMessageId)
                    .orElseThrow({ noContextMessageException(context.operationRequest.id) })

            if (context.rolledBackContextMessage.operationName != context.operationRequest.operationName) {
                throw rollbackOperationNamesException(context)
            }

            if (context.rolledBackContextMessage.state != SUCCESS.state) {
                throw contextMessageStateException(context.rolledBackContextMessage.state)
            }

            Object result = executor.rollbackOperation(context)

            context.rolledBackContextMessage.state = ROLLBACK_SUCCESS
            contextMessageRepository.save(context.rolledBackContextMessage)

            return createOperationResponse(context, result)
        } catch (Exception e) {
            if (context.rolledBackContextMessage) {
                context.rolledBackContextMessage.state = ROLLBACK_FAILED
                contextMessageRepository.save(context.rolledBackContextMessage)
            }

            throw e
        }
    }

    protected OperationContext createOperationContext(OperationDirections direction, OperationRequest request) {
        new OperationContext(
                contextHelper: contextHelper,
                operationRequest: request,
                contextMessage: contextHelper.createContextMessage(direction, request)
        )
    }

    protected OperationResponse createOperationResponse(OperationContext context, Object result) {
        new OperationResponse(
                id: context.operationRequest.id,
                operationName: context.operationRequest.operationName,
                state: context.contextMessage.state,
                owner: context.operationRequest.owner,
                body: result
        )
    }
}
