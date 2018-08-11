package systems.vostok.taxi.drive.app.operation

import groovy.json.JsonSlurper
import systems.vostok.taxi.drive.app.dao.domain.operation.OperationContext
import systems.vostok.taxi.drive.app.dao.entity.geo.GeoEntity
import systems.vostok.taxi.drive.app.dao.repository.BasicRepository
import systems.vostok.taxi.drive.app.util.constant.OperationName
import systems.vostok.taxi.drive.app.util.exception.OperationExecutionException

/*
enroll
 {
    "operationName": "OPERATION_NAME",
    "direction": "enroll",
    "body": {:}
 }

rollback
 {
    "id": "51ae64c4-3327-4b73-9498-1fa3347d2a15",
    "operationName": "OPERATION_NAME",
    "direction": "rollback"
 }
*/

class EntityAddOperation<T, ID extends Serializable> implements Operation {
    OperationName operationName
    BasicRepository<T, ID> entityRepository

    @Override
    Object enroll(OperationContext context) {
        T targetEntity = entityRepository.convertToEntityType(context.operationRequest.body)

        if (entityRepository.getByEntityId(targetEntity)) {
            throw new OperationExecutionException('Entity with target ID already exists')
        }

        entityRepository.save(targetEntity)
                .with { context.contextHelper.setContext(context, it); it }
    }

    @Override
    Object rollback(OperationContext context) {
        T contextEntity = context.rollbackContextMessage.context
                .with(new JsonSlurper().&parseText)
                .with(entityRepository.&convertToEntityType)

        T persistentEntity = entityRepository.getByEntityId(contextEntity)

        if (!contextEntity) {
            throw new OperationExecutionException('Rollback rejected: context entity must not be null')
        }
        if (contextEntity != persistentEntity) {
            throw new OperationExecutionException('Rollback rejected: entity was modified')
        }

        entityRepository.delete(contextEntity)
                .with { context.contextHelper.setContext(context, context.operationRequest.id) }
    }
}
