package systems.vostok.taxi.drive.app.operation.geo

import org.springframework.stereotype.Component
import systems.vostok.taxi.drive.app.dao.domain.OperationRequest
import systems.vostok.taxi.drive.app.dao.entity.ContextMessage
import systems.vostok.taxi.drive.app.dao.entity.geo.GeoEntity
import systems.vostok.taxi.drive.app.dao.repository.BasicRepository
import systems.vostok.taxi.drive.app.operation.Operation

@Component
class DeleteGeoOperation<T extends GeoEntity> implements Operation {
    String operationName
    BasicRepository<T, String> entityRepository

    // TODO: Implement delete geo operation type

    @Override
    Object enroll(OperationRequest request) {
        return null
    }

    @Override
    Object rollback(OperationRequest request, ContextMessage contextMessage) {
        return null
    }
}
