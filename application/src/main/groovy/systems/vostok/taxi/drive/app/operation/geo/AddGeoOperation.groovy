package systems.vostok.taxi.drive.app.operation.geo

import groovy.json.JsonSlurper
import systems.vostok.taxi.drive.app.dao.domain.operation.OperationContext
import systems.vostok.taxi.drive.app.dao.entity.geo.GeoEntity
import systems.vostok.taxi.drive.app.dao.repository.BasicRepository
import systems.vostok.taxi.drive.app.operation.Operation
import systems.vostok.taxi.drive.app.util.constant.OperationName
import systems.vostok.taxi.drive.app.util.exception.OperationExecutionException

/*
enroll
 {
    "operationName": "ADD_CITY",
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
    "operationName": "ADD_CITY",
    "direction": "rollback"
 }
*/

class AddGeoOperation<T extends GeoEntity> implements Operation {
    OperationName operationName
    BasicRepository<T, String> entityRepository

    @Override
    Object enroll(OperationContext context) {
        T targetEntity = entityRepository.convertToEntityType(context.operationRequest.body)

        if (entityRepository.getOne(targetEntity.id)) {
            throw new OperationExecutionException('Geo entity with target ID already exists')
        }

        entityRepository.save(targetEntity)
                .with { context.contextHelper.setContext(context, it); it }
    }

    @Override
    Object rollback(OperationContext context) {
        T contextEntity = context.rollbackContextMessage.context
                .with(new JsonSlurper().&parseText)
                .with(entityRepository.&convertToEntityType)

        T persistentEntity = entityRepository.getOne(contextEntity.id)

        if (!contextEntity) {
            throw new OperationExecutionException('Rollback rejected: context entity must not be null')
        }
        if (contextEntity != persistentEntity) {
            throw new OperationExecutionException('Rollback rejected: entity was modified')
        }

        entityRepository.deleteById(contextEntity.id)
                .with { context.contextHelper.setContext(context, context.operationRequest.id) }
    }
}
