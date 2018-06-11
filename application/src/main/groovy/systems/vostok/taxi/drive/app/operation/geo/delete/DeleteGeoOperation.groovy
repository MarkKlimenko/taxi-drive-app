package systems.vostok.taxi.drive.app.operation.geo.delete

import systems.vostok.taxi.drive.app.dao.domain.operation.OperationContext
import systems.vostok.taxi.drive.app.dao.entity.geo.GeoEntity
import systems.vostok.taxi.drive.app.dao.repository.BasicRepository
import systems.vostok.taxi.drive.app.operation.Operation

class DeleteGeoOperation<T extends GeoEntity> implements Operation {
    String operationName
    BasicRepository<T, String> entityRepository

    // TODO: Implement delete geo operation type

    @Override
    Object enroll(OperationContext context) {
        return null
    }

    @Override
    Object rollback(OperationContext context) {
        return null
    }
}
