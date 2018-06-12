package systems.vostok.taxi.drive.app.operation.geo.edit

import groovy.json.JsonSlurper
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

    @Override
    Object enroll(OperationContext context) {
        T contextEntity = null
        T persistentEntity = null

        def getTargetEntities = {
            contextEntity = entityRepository.convertToEntityType(context.operationRequest.body)
            persistentEntity = entityRepository.findOne(contextEntity.id)
        }

        def checkPersistentEntity = {
            assert persistentEntity: 'Geo entity with target ID does not exists'
        }

        def setContext = { T resultEntity ->
            Map<String, T> contextBody = [before: persistentEntity,
                                          after : resultEntity]

            context.contextHelper.setContext(context, contextBody)
            resultEntity
        }

        getTargetEntities()
                .with(checkPersistentEntity)
                .with { this.entityRepository.save(contextEntity) }
                .with(setContext)
    }

    @Override
    Object rollback(OperationContext context) {
        T contextEntityBefore = null
        T contextEntityAfter = null
        T persistentEntity = null

        def getContextEntity = { String entityType ->
            context.rollbackContextMessage.context
                    .with(new JsonSlurper().&parseText)
                    .with { it[entityType] }
                    .with(entityRepository.&convertToEntityType)
        }

        def getTargetEntities = {
            contextEntityBefore = getContextEntity('before')
            contextEntityAfter = getContextEntity('after')

            persistentEntity = entityRepository.findOne(contextEntityAfter.id)
        }

        def checkEntity = {
            assert contextEntityAfter: 'Rollback rejected: context entities must not be null'
            assert contextEntityAfter == persistentEntity: 'Rollback rejected: entity was modified'
        }

        def executeRollback = {
            entityRepository.save(contextEntityBefore)
        }

        getTargetEntities()
                .with(checkEntity)
                .with(executeRollback)
    }
}
