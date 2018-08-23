package systems.vostok.taxi.drive.app.dao.domain.operation

import systems.vostok.taxi.drive.app.dao.entity.ContextMessage
import systems.vostok.taxi.drive.app.executor.ContextHelper
import systems.vostok.taxi.drive.app.operation.OperationDirection
import systems.vostok.taxi.drive.app.operation.OperationRequest

class OperationContext {
    ContextHelper contextHelper

    OperationRequest operationRequest
    OperationDirection direction

    ContextMessage contextMessage
    ContextMessage rolledBackContextMessage
}
