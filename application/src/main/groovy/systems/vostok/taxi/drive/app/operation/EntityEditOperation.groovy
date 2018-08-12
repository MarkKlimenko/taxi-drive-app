package systems.vostok.taxi.drive.app.operation

import groovy.json.JsonSlurper
import systems.vostok.taxi.drive.app.dao.domain.operation.OperationContext
import systems.vostok.taxi.drive.app.dao.repository.BasicRepository
import systems.vostok.taxi.drive.app.dao.domain.operation.CoreOperationNames
import systems.vostok.taxi.drive.app.util.exception.OperationExecutionException

import javax.transaction.Transactional

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

class EntityEditOperation<T, ID extends Serializable> implements CoreOperation {
    CoreOperationNames operationName
    String operationTimeout
    BasicRepository<T, ID> entityRepository

    @Override
    @Transactional
    Object enroll(OperationContext context) {
        T contextEntity = entityRepository.convertToEntityType(context.operationRequest.body)
        T persistentEntity = entityRepository.getByEntityId(contextEntity)

        if (!persistentEntity) {
            throw new OperationExecutionException('Entity with target ID does not exist')
        }

        T editedEntity = entityRepository.save(contextEntity)
        context.contextHelper.setContext(context, [before: persistentEntity, after : editedEntity])
        context.contextHelper.setEntityId(context, entityRepository.getEntityId(persistentEntity))
        editedEntity
    }

    @Override
    @Transactional
    Object rollback(OperationContext context) {
        Map parsedContext = context.rolledBackContextMessage.context
                .with(new JsonSlurper().&parseText) as Map

        T contextEntityBefore = parsedContext.before.with(entityRepository.&convertToEntityType)
        T contextEntityAfter = parsedContext.after.with(entityRepository.&convertToEntityType)
        T persistentEntity = entityRepository.getByEntityId(contextEntityAfter)

        if(!contextEntityAfter) {
            throw new OperationExecutionException('Rollback rejected: context entities must not be null')
        }

        if(contextEntityAfter != persistentEntity) {
            throw new OperationExecutionException('Rollback rejected: entity was modified')
        }

        entityRepository.save(contextEntityBefore)
        context.contextHelper.setContext(context, context.operationRequest.id)
        context.contextHelper.setEntityId(context, entityRepository.getEntityId(contextEntityBefore))
    }
}
