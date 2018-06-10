package systems.vostok.taxi.drive.app.executor

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import systems.vostok.taxi.drive.app.dao.domain.OperationRequest
import systems.vostok.taxi.drive.app.dao.entity.ContextMessage
import systems.vostok.taxi.drive.app.dao.repository.impl.ContextMessageRepository

import java.time.LocalDateTime

import static systems.vostok.taxi.drive.app.util.constant.OperationState.SUCCESS_OPERATION_STATE
import static systems.vostok.taxi.drive.app.util.constant.OperationState.FAIL_OPERATION_STATE

@Service
class ContextHelper {
    @Autowired
    ContextMessageRepository contextMessageRepository

    void reportSuccess(OperationRequest request, Object operationContext) {
        composeMessage(request, SUCCESS_OPERATION_STATE, operationContext)
                .with(contextMessageRepository.&save)
    }

    void reportFail(OperationRequest request, String exceptionMessage) {
        composeMessage(request, FAIL_OPERATION_STATE, exceptionMessage)
                .with(contextMessageRepository.&save)
    }

    // TODO: get owner name from security context
    private ContextMessage composeMessage(OperationRequest request, String state, Object context) {
        [
                operationName: request.operationName,
                owner        : 'admin',
                dateIn       : LocalDateTime.now(),
                state        : state,
                direction    : request.direction,
                requestBody  : request.body,
                context      : context
        ]
    }
}
