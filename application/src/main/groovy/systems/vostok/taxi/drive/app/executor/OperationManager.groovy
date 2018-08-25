package systems.vostok.taxi.drive.app.executor

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import systems.vostok.taxi.drive.app.dao.domain.operation.OperationContext
import systems.vostok.taxi.drive.app.dao.domain.operation.OperationResponse
import systems.vostok.taxi.drive.app.dao.repository.impl.ContextMessageRepository

import static systems.vostok.taxi.drive.app.dao.domain.operation.OperationStates.*
import static systems.vostok.taxi.drive.app.util.ContentTypeConverter.toMap
import static systems.vostok.taxi.drive.app.util.exception.OperationExecutionException.*

@Service
class OperationManager {
    @Autowired
    ContextMessageRepository contextMessageRepository

    @HystrixCommand(
            fallbackMethod = 'breakEnrollOperation',
            ignoreExceptions = [Exception.class],
            commandProperties =
                    [@HystrixProperty(
                            name = 'execution.isolation.thread.timeoutInMilliseconds',
                            value = '10000')]
    )
    OperationResponse enrollOperation(OperationExecutor executor, OperationContext context) {
        createOperationResponse(context, executor.enrollOperation(context))
    }

    OperationResponse breakEnrollOperation(OperationExecutor executor, OperationContext context) {
        createOperationResponse(context, executor.breakEnrollOperation(context))
    }

    OperationResponse rollbackOperation(OperationExecutor executor, OperationContext context) {
        try {
            UUID rolledBackContextMessageId = UUID.fromString(toMap(context.operationRequest.body).id)
            context.rolledBackContextMessage = contextMessageRepository.findOneById(rolledBackContextMessageId)
                    .orElseThrow({ noContextMessageException(rolledBackContextMessageId) })

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

    static OperationResponse createOperationResponse(OperationContext context, Object result) {
        new OperationResponse(
                id: context.contextMessage.id,
                operationName: context.operationRequest.operationName,
                state: context.contextMessage.state,
                owner: context.contextMessage.owner,
                body: result
        )
    }
}
