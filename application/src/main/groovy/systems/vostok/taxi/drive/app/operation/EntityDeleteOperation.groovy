package systems.vostok.taxi.drive.app.operation

import groovy.json.JsonSlurper
import systems.vostok.taxi.drive.app.dao.domain.operation.OperationContext
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

class EntityDeleteOperation<T, ID extends Serializable> implements Operation {
    OperationName operationName
    BasicRepository<T, ID> entityRepository

    @Override
    Object enroll(OperationContext context) {
        T targetEntity = entityRepository.findById(context.operationRequest.body.id)
                .orElseThrow({ new OperationExecutionException('Entity with target ID does not exist') })

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

        if (entityRepository.getByEntityId(contextEntity)) {
            throw new OperationExecutionException('Rollback rejected: entity already exists')
        }

        entityRepository.save(contextEntity)
                .with { context.contextHelper.setContext(context, context.operationRequest.id) }
    }
}
