package systems.vostok.taxi.drive.app.operation.geo.add

import groovy.json.JsonSlurper
import systems.vostok.taxi.drive.app.dao.domain.operation.OperationContext
import systems.vostok.taxi.drive.app.dao.entity.geo.GeoEntity
import systems.vostok.taxi.drive.app.dao.repository.BasicRepository
import systems.vostok.taxi.drive.app.operation.Operation

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
    String operationName
    BasicRepository<T, String> entityRepository

    @Override
    Object enroll(OperationContext context) {
        def getTargetEntity = {
            entityRepository.convertToEntityType(context.operationRequest.body)
        }

        def checkEntity = { T targetEntity ->
            T checkedEntity = entityRepository.findOne(targetEntity.id)
            assert !checkedEntity: 'Geo entity with target ID already exists'
            targetEntity
        }

        def setContext = { T entity ->
            context.contextHelper.setContext(context, entity)
            entity
        }

        getTargetEntity()
                .with(checkEntity)
                .with(entityRepository.&save)
                .with(setContext)
    }

    @Override
    Object rollback(OperationContext context) {
        T contextEntity = null
        T persistentEntity = null

        def getTargetEntities = {
            contextEntity = context.rollbackContextMessage.context
                    .with(new JsonSlurper().&parseText)
                    .with(entityRepository.&convertToEntityType)

            persistentEntity = entityRepository.findOne(contextEntity.id)
        }

        def checkEntity = {
            assert contextEntity: 'Rollback rejected: context entity must not be null'
            assert contextEntity == persistentEntity: 'Rollback rejected: entity was modified'
        }

        def executeRollback = {
            entityRepository.delete(contextEntity.id)
            persistentEntity
        }

        getTargetEntities()
                .with(checkEntity)
                .with(executeRollback)
    }
}
