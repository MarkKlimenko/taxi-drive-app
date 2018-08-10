package systems.vostok.taxi.drive.app.operation.geo.delete

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
    OperationName operationName
    BasicRepository<T, String> entityRepository

    @Override
    Object enroll(OperationContext context) {
        T targetEntity = entityRepository.getOne(context.operationRequest.body.id)

        if (!targetEntity) {
            throw new OperationExecutionException('Geo entity with target ID does not exist')
        }

        entityRepository.delete(targetEntity)
                .with { context.contextHelper.setContext(context, it) }
    }

    @Override
    Object rollback(OperationContext context) {
        T contextEntity = context.rollbackContextMessage.context
                .with(new JsonSlurper().&parseText)
                .with(entityRepository.&convertToEntityType)

        if (!contextEntity) {
            throw new OperationExecutionException('Rollback rejected: context entity must not be null')
        }

        if (entityRepository.getOne(contextEntity.id)) {
            throw new OperationExecutionException('Rollback rejected: entity already exists')
        }

        entityRepository.save(contextEntity)
                .with { context.contextHelper.setContext(context, context.operationRequest.id) }
    }
}
