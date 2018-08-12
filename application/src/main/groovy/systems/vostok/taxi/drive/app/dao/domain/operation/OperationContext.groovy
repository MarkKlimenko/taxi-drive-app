package systems.vostok.taxi.drive.app.dao.domain.operation

import systems.vostok.taxi.drive.app.dao.entity.ContextMessage
import systems.vostok.taxi.drive.app.executor.ContextHelper

class OperationContext {
    ContextHelper contextHelper

    OperationRequest operationRequest
    OperationDirections direction

    ContextMessage contextMessage
    ContextMessage rolledBackContextMessage
}
