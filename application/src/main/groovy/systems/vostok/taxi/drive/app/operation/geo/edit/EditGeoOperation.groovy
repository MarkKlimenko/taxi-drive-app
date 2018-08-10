package systems.vostok.taxi.drive.app.operation.geo.edit

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
    OperationName operationName
    BasicRepository<T, String> entityRepository

    @Override
    Object enroll(OperationContext context) {
        T contextEntity = entityRepository.convertToEntityType(context.operationRequest.body)
        T persistentEntity = entityRepository.getOne(contextEntity.id)

        if (!persistentEntity) {
            throw new OperationExecutionException('Geo entity with target ID does not exist')
        }

        T resultEntity = entityRepository.save(contextEntity)
        context.contextHelper.setContext(context, [before: persistentEntity, after : resultEntity])
    }

    @Override
    Object rollback(OperationContext context) {
        Map parsedContext = context.rollbackContextMessage.context
                .with(new JsonSlurper().&parseText) as Map

        T contextEntityBefore = parsedContext.before.with(entityRepository.&convertToEntityType)
        T contextEntityAfter = parsedContext.after.with(entityRepository.&convertToEntityType)
        T persistentEntity = entityRepository.getOne(contextEntityAfter.id)

        if(!contextEntityAfter) {
            throw new OperationExecutionException('Rollback rejected: context entities must not be null')
        }

        if(contextEntityAfter != persistentEntity) {
            throw new OperationExecutionException('Rollback rejected: entity was modified')
        }

        entityRepository.save(contextEntityBefore)
        context.contextHelper.setContext(context, context.operationRequest.id)
    }
}
