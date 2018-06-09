package systems.vostok.taxi.drive.app.operation.geo

import org.springframework.stereotype.Component
import systems.vostok.taxi.drive.app.dao.domain.OperationRequest
import systems.vostok.taxi.drive.app.dao.entity.ContextMessage
import systems.vostok.taxi.drive.app.operation.Operation

import static systems.vostok.taxi.drive.app.util.constant.OperationName.EDIT_CITY_OPERATION

@Component
class EditCityOperation implements Operation {
    String operationName = EDIT_CITY_OPERATION

    @Override
    Object enroll(OperationRequest request) {
        return null
    }

    @Override
    Object rollback(OperationRequest request, ContextMessage contextMessage) {
        return null
    }
}
