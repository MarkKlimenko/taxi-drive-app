package systems.vostok.taxi.drive.app.operation.geo.edit

import systems.vostok.taxi.drive.app.dao.domain.operation.OperationContext
import systems.vostok.taxi.drive.app.dao.entity.geo.GeoEntity
import systems.vostok.taxi.drive.app.dao.repository.BasicRepository
import systems.vostok.taxi.drive.app.operation.Operation

/*
enroll
 {
    "operationName": "EDIT_CITY",
    "direction": "enroll",
    "body": {
        "id": "spdTest",
        "name": "Спасск-Дальний-Тест",
        "state": "pk"
    }
 }

rollback
 {
    "id": "51ae64c4-3327-4b73-9498-1fa3347d2a15",
    "operationName": "EDIT_CITY",
    "direction": "rollback"
 }
*/

class EditGeoOperation<T extends GeoEntity> implements Operation {
    String operationName
    BasicRepository<T, String> entityRepository

    // TODO: Implement edit geo operation type

    @Override
    Object enroll(OperationContext context) {
        null
    }

    @Override
    Object rollback(OperationContext context) {
        null
    }
}
