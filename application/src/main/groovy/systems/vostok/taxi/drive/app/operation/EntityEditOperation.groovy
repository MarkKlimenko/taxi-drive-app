package systems.vostok.taxi.drive.app.operation

import groovy.json.JsonSlurper
import systems.vostok.taxi.drive.app.dao.domain.operation.OperationContext
import systems.vostok.taxi.drive.app.dao.repository.BasicRepository
import systems.vostok.taxi.drive.app.util.exception.OperationExecutionException

import javax.transaction.Transactional

import static systems.vostok.taxi.drive.app.util.ContentTypeConverter.toMap

/*
enroll
 {
    "operationName": "OPERATION_NAME",
    "body": {:}
 }

rollback
 {
    "operationName": "OPERATION_NAME",
    "body": {
        "id" : "51ae64c4-3327-4b73-9498-1fa3347d2a15"
    }
 }
*/

class EntityEditOperation<T, ID extends Serializable> implements CoreOperation {
    String operationName
    String operationTimeout
    BasicRepository<T, ID> entityRepository

    @Override
    @Transactional
    Object enroll(OperationContext context) {
        T contextEntity = entityRepository.convertToEntityType(toMap(context.operationRequest.body))
        T persistentEntity = entityRepository.getByEntityId(contextEntity).clone() as T

        if (!persistentEntity) {
            throw new OperationExecutionException('Entity with target ID does not exist')
        }

        T editedEntity = entityRepository.save(contextEntity)
        context.contextHelper.setContext(context, [before: persistentEntity, after: editedEntity])
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

        if (!contextEntityAfter) {
            throw new OperationExecutionException('Rollback rejected: context entities must not be null')
        }

        if (contextEntityAfter != persistentEntity) {
            throw new OperationExecutionException('Rollback rejected: entity was modified or removed')
        }

        entityRepository.save(contextEntityBefore)
        context.contextHelper.setContext(context, context.operationRequest.id)
        context.contextHelper.setEntityId(context, entityRepository.getEntityId(contextEntityBefore))
    }
}
