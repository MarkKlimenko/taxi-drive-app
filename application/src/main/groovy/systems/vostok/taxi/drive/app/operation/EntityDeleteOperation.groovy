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
    "direction": "enroll",
    "body": "Json payload(Map, List, String...)"
 }

rollback
 {
    "operationName": "OPERATION_NAME",
    "direction": "rollback",
    "body": {
        "id" : "51ae64c4-3327-4b73-9498-1fa3347d2a15"
    }
 }
*/

class EntityDeleteOperation<T, ID extends Serializable> implements CoreOperation {
    String operationName
    String operationTimeout
    BasicRepository<T, ID> entityRepository

    @Override
    @Transactional
    Object enroll(OperationContext context) {
        ID targetEntityId = entityRepository.getEntityId(toMap(context.operationRequest.body))
        T targetEntity = entityRepository.findById(targetEntityId)
                .orElseThrow({ new OperationExecutionException('Entity with target ID does not exist') })

        entityRepository.delete(targetEntity)
        context.contextHelper.setContext(context, targetEntity)
        context.contextHelper.setEntityId(context, entityRepository.getEntityId(targetEntity))

    }

    @Override
    @Transactional
    Object rollback(OperationContext context) {
        T contextEntity = context.rolledBackContextMessage.context
                .with(new JsonSlurper().&parseText)
                .with(entityRepository.&convertToEntityType)

        if (!contextEntity) {
            throw new OperationExecutionException('Rollback rejected: context entity must not be null')
        }

        if (entityRepository.getByEntityId(contextEntity)) {
            throw new OperationExecutionException('Rollback rejected: entity already exists')
        }

        entityRepository.save(contextEntity)
        context.contextHelper.setContext(context, context.operationRequest.id)
        context.contextHelper.setEntityId(context, entityRepository.getEntityId(contextEntity))
    }
}
