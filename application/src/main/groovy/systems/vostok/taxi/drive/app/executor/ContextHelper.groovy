package systems.vostok.taxi.drive.app.executor

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import systems.vostok.taxi.drive.app.dao.domain.operation.OperationContext
import systems.vostok.taxi.drive.app.dao.domain.operation.OperationDirections
import systems.vostok.taxi.drive.app.dao.domain.operation.OperationRequest
import systems.vostok.taxi.drive.app.dao.entity.ContextMessage
import systems.vostok.taxi.drive.app.dao.repository.impl.ContextMessageRepository

import java.time.LocalDateTime

import static systems.vostok.taxi.drive.app.dao.domain.operation.OperationStates.*

@Service
class ContextHelper {
    @Autowired
    ContextMessageRepository contextMessageRepository

    ContextMessage createContextMessage(OperationDirections direction, OperationRequest request) {
        ContextMessage contextMessage = new ContextMessage(
                id: request.id,
                operationName: request.operationName,
                owner        : 'test', // TODO: get from security context
                dateIn       : LocalDateTime.now(),
                state        : IN_PROCESS,
                direction    : direction,
                requestBody  : request.body
        )

        contextMessageRepository.save(contextMessage)
    }

    OperationContext setSuccess(OperationContext operationContext) {
        operationContext.contextMessage
                .with { it.state = SUCCESS; it }
                .with(contextMessageRepository.&save)

        operationContext
    }

    OperationContext setFailed(OperationContext operationContext) {
        operationContext.contextMessage
                .with { it.state = FAILED; it }
                .with(contextMessageRepository.&save)

        operationContext
    }

    OperationContext setContext(OperationContext operationContext, Object context) {
        operationContext.contextMessage.context = context
        operationContext
    }

    OperationContext setEntityId(OperationContext operationContext, Object entityId) {
        operationContext.contextMessage.entityId = entityId as String
        operationContext
    }
}
