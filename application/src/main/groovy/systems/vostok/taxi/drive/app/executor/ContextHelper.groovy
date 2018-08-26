package systems.vostok.taxi.drive.app.executor

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import systems.vostok.taxi.drive.app.dao.domain.operation.OperationContext
import systems.vostok.taxi.drive.app.dao.domain.operation.OperationStates
import systems.vostok.taxi.drive.app.operation.OperationDirection
import systems.vostok.taxi.drive.app.operation.OperationRequest
import systems.vostok.taxi.drive.app.dao.entity.ContextMessage
import systems.vostok.taxi.drive.app.dao.repository.impl.ContextMessageRepository

import java.time.LocalDateTime

import static systems.vostok.taxi.drive.app.dao.domain.operation.OperationStates.*
import static systems.vostok.taxi.drive.app.util.exception.OperationExecutionException.noContextMessageException

@Service
class ContextHelper {
    private final static String ANONYMOUS_USER = 'anonymous'

    @Autowired
    ContextMessageRepository contextMessageRepository

    OperationContext createPrimaryOperationContext(OperationRequest request) {
        new OperationContext(
                contextHelper: this,
                operationRequest: request,
                direction: request.direction,
                contextMessage: this.createContextMessage(request.direction, request)
        )
    }

    OperationContext createAsyncOperationContext(OperationRequest request) {
        new OperationContext(
                contextHelper: this,
                operationRequest: request,
                direction: request.direction,
                contextMessage: contextMessageRepository.findOneById(UUID.fromString(request.id))
                        .orElseThrow({ noContextMessageException(request.id) })
        )
    }

    ContextMessage createContextMessage(OperationDirection direction, OperationRequest request) {
        ContextMessage contextMessage = new ContextMessage(
                id: request.id ? UUID.fromString(request.id) : UUID.randomUUID(),
                operationName: request.operationName,
                owner: SecurityContextHolder.getContext().getAuthentication()?.name ?: ANONYMOUS_USER,
                dateIn: LocalDateTime.now(),
                state: NEW,
                direction: direction,
                requestBody: request.stringPayload
        )

        contextMessageRepository.save(contextMessage)
    }

    OperationContext setState(OperationContext operationContext, OperationStates state) {
        operationContext.contextMessage
                .with { it.state = state; it }
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
