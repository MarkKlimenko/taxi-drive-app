package systems.vostok.taxi.drive.app.dao.domain.operation

import systems.vostok.taxi.drive.app.dao.entity.ContextMessage
import systems.vostok.taxi.drive.app.executor.ContextHelper

class OperationContext {
    ContextHelper contextHelper

    OperationRequest operationRequest
    ContextMessage contextMessage
    ContextMessage rollbackContextMessage

    static class Builder {
        ContextHelper contextHelper

        OperationRequest operationRequest
        ContextMessage contextMessage

        Builder contextHelper(ContextHelper contextHelper) {
            this.contextHelper = contextHelper
            this
        }

        Builder operationRequest(OperationRequest operationRequest) {
            this.operationRequest = operationRequest
            this
        }

        Builder contextMessage(ContextMessage contextMessage) {
            this.contextMessage = contextMessage
            this
        }

        OperationContext build() {
            new OperationContext(this)
        }
    }

    private OperationContext(Builder builder) {
        contextHelper = builder.contextHelper

        operationRequest = builder.operationRequest
        contextMessage = builder.contextMessage
    }
}
