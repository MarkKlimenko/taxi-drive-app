package systems.vostok.taxi.drive.app.operation

import groovy.json.JsonSlurper
import systems.vostok.taxi.drive.app.dao.domain.operation.OperationContext
import systems.vostok.taxi.drive.app.dao.repository.BasicRepository
import systems.vostok.taxi.drive.app.dao.domain.operation.CoreOperationNames
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

class EntityAddOperation<T, ID extends Serializable> implements CoreOperation {
    CoreOperationNames operationName
    String operationTimeout
    BasicRepository<T, ID> entityRepository

    @Override
    Object enroll(OperationContext context) {
        T targetEntity = entityRepository.convertToEntityType(context.operationRequest.body)

        if (entityRepository.getByEntityId(targetEntity)) {
            throw new OperationExecutionException('Entity with target ID already exists')
        }

        T savedEntity = entityRepository.save(targetEntity)
        context.contextHelper.setContext(context, savedEntity)
        context.contextHelper.setEntityId(context, entityRepository.getEntityId(savedEntity))
        savedEntity
    }

    @Override
    Object rollback(OperationContext context) {
        T contextEntity = context.rolledBackContextMessage.context
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
        context.contextHelper.setContext(context, context.operationRequest.id)
        context.contextHelper.setEntityId(context, entityRepository.getEntityId(contextEntity))
    }

    @Override
    Object breakOperation(OperationContext context) {
        return null
    }
}
