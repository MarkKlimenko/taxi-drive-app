package systems.vostok.taxi.drive.app.operation.geo

import groovy.json.JsonSlurper
import org.springframework.stereotype.Component
import systems.vostok.taxi.drive.app.dao.domain.OperationRequest
import systems.vostok.taxi.drive.app.dao.entity.ContextMessage
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

@Component
class EditGeoOperation<T extends GeoEntity> implements Operation {
    String operationName
    BasicRepository<T, String> entityRepository

    @Override
    Object enroll(OperationRequest request) {
        //TODO: Add transactional here

        def getTargetEntity = {
            entityRepository.convertToEntityType(request.body)
        }

        def checkEntity = { T targetEntity ->
            T checkedEntity = entityRepository.findOne(targetEntity.id)
            assert checkedEntity: 'Geo entity with target ID does NOT exist'
            targetEntity
        }

        getTargetEntity()
                .with(checkEntity)
                .with(entityRepository.&save)

        // TODO: Save proper context (before: after:)
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
            // TODO: add proper checking
            assert contextEntity: 'Rollback rejected: context entity must not be null'
            assert contextEntity == persistentEntity: 'Rollback rejected: entity was modified'
        }

        def executeRollback = {
            // TODO: edit entity here
            // entityRepository.delete(contextEntity.id)
            persistentEntity
        }

        getTargetEntities()
                .with(checkEntity)
                .with(executeRollback)
    }
}
