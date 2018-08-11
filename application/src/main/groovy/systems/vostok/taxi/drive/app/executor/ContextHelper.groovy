package systems.vostok.taxi.drive.app.executor

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import systems.vostok.taxi.drive.app.dao.domain.operation.OperationContext
import systems.vostok.taxi.drive.app.dao.domain.operation.OperationRequest
import systems.vostok.taxi.drive.app.dao.entity.ContextMessage
import systems.vostok.taxi.drive.app.dao.entity.ContextMessageToEntity
import systems.vostok.taxi.drive.app.dao.repository.impl.ContextMessageRepository
import systems.vostok.taxi.drive.app.dao.repository.impl.ContextMessageToEntityRepository

import java.time.LocalDateTime

import static systems.vostok.taxi.drive.app.util.constant.OperationState.*

@Service
class ContextHelper {
    @Autowired
    ContextMessageRepository contextMessageRepository

    @Autowired
    ContextMessageToEntityRepository contextMessageToEntityRepository

    ContextMessage createContextMessage(OperationRequest request) {
        composeMessage(request)
                .with(contextMessageRepository.&save)
    }

    OperationContext setSuccess(OperationContext operationContext) {
        operationContext.contextMessage
                .with { it.state = SUCCESS_OPERATION_STATE; it }
                .with(contextMessageRepository.&save)

        operationContext
    }

    OperationContext setFail(OperationContext operationContext) {
        operationContext.contextMessage
                .with { it.state = FAIL_OPERATION_STATE; it }
                .with(contextMessageRepository.&save)

        operationContext
    }

    OperationContext setContext(OperationContext operationContext, Object context) {
        operationContext.contextMessage.context = context
        operationContext
    }

    OperationContext setEntityToContextMessage(OperationContext operationContext, Object context) {
        operationContext.contextMessage.context = context

        contextMessageToEntityRepository.save(new ContextMessageToEntity())

        operationContext
    }

    // TODO: get owner name from security context
    private ContextMessage composeMessage(OperationRequest request) {
        [
                operationName: request.operationName,
                owner        : 'admin',
                dateIn       : LocalDateTime.now(),
                state        : IN_PROCESS_OPERATION_STATE,
                direction    : request.direction,
                requestBody  : request.body,
                context      : null
        ]
    }
}
