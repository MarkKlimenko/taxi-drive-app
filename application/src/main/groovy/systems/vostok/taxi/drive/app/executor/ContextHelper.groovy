package systems.vostok.taxi.drive.app.executor

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import systems.vostok.taxi.drive.app.dao.domain.OperationRequest
import systems.vostok.taxi.drive.app.dao.entity.ContextMessage
import systems.vostok.taxi.drive.app.dao.repository.impl.ContextMessageRepository

import java.time.LocalDateTime

@Service
class ContextHelper {
    @Autowired
    ContextMessageRepository contextMessageRepository

    void reportSuccess(OperationRequest request, Object operationContext) {
        composeMessage(request, 'success', operationContext)
                .with(contextMessageRepository.&save)
    }

    void reportFail(OperationRequest request, String exceptionMessage) {
        composeMessage(request, 'fail', exceptionMessage)
                .with(contextMessageRepository.&save)
    }

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
