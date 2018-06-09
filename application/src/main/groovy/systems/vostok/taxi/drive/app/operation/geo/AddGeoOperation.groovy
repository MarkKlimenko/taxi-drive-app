package systems.vostok.taxi.drive.app.operation.geo

import groovy.json.JsonSlurper
import systems.vostok.taxi.drive.app.dao.domain.OperationRequest
import systems.vostok.taxi.drive.app.dao.entity.ContextMessage
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
    Object enroll(OperationRequest request) {
        def getTargetEntity = {
            entityRepository.convertToEntityType(request.body)
        }

        def checkEntity = { T targetEntity ->
            T checkedEntity = entityRepository.findOne(targetEntity.id)
            assert !checkedEntity: 'Geo entity with target ID already exists'
            targetEntity
        }

        getTargetEntity()
                .with(checkEntity)
                .with(entityRepository.&save)
    }

    @Override
    Object rollback(OperationRequest request, ContextMessage contextMessage) {
        T contextEntity = null
        T persistentEntity = null

        def getTargetEntities = {
            contextEntity = contextMessage.context
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
