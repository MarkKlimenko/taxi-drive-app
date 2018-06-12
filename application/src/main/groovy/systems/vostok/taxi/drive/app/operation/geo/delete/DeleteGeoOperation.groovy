package systems.vostok.taxi.drive.app.operation.geo.delete

import groovy.json.JsonSlurper
import systems.vostok.taxi.drive.app.dao.domain.operation.OperationContext
import systems.vostok.taxi.drive.app.dao.entity.geo.GeoEntity
import systems.vostok.taxi.drive.app.dao.repository.BasicRepository
import systems.vostok.taxi.drive.app.operation.Operation

/*
enroll
 {
    "operationName": "DELETE_CITY",
    "direction": "enroll",
    "body": {
        "id": "spdTest"
    }
 }

rollback
 {
    "id": "51ae64c4-3327-4b73-9498-1fa3347d2a15",
    "operationName": "DELETE_CITY",
    "direction": "rollback"
 }
*/

class DeleteGeoOperation<T extends GeoEntity> implements Operation {
    String operationName
    BasicRepository<T, String> entityRepository

    @Override
    Object enroll(OperationContext context) {
        def getTargetEntityId = {
            context.operationRequest.body.id
        }

        def getTargetEntity = { String targetEntityId ->
            T targetEntity = entityRepository.findOne(targetEntityId)
            assert targetEntity: 'Geo entity with target ID does not exist'
            targetEntity
        }

        def setContext = { T entity ->
            context.contextHelper.setContext(context, entity)
            null
        }

        getTargetEntityId()
                .with(getTargetEntity)
                .with{ this.entityRepository.delete(it); it }
                .with(setContext)
    }

    @Override
    Object rollback(OperationContext context) {
        def getTargetEntity = {
            context.rollbackContextMessage.context
                    .with(new JsonSlurper().&parseText)
                    .with(entityRepository.&convertToEntityType)

        }

        def checkEntity = { T contextEntity ->
            assert contextEntity: 'Rollback rejected: context entity must not be null'

            T persistentEntity = entityRepository.findOne(contextEntity.id)
            assert !persistentEntity: 'Rollback rejected: entity already exists'

            contextEntity
        }

        def executeRollback = { T contextEntity ->
            entityRepository.save(contextEntity)
            contextEntity
        }

        def setContext = {
            context.contextHelper.setContext(context, context.operationRequest.id)
        }

        getTargetEntity()
                .with(checkEntity)
                .with(executeRollback)
                .with(setContext)
    }
}
