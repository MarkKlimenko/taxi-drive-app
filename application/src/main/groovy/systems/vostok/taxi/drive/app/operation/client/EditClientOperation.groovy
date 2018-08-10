package systems.vostok.taxi.drive.app.operation.client

import groovy.json.JsonSlurper
import org.springframework.beans.factory.annotation.Autowired
import systems.vostok.taxi.drive.app.dao.domain.operation.OperationContext
import systems.vostok.taxi.drive.app.dao.entity.Client
import systems.vostok.taxi.drive.app.dao.repository.BasicRepository
import systems.vostok.taxi.drive.app.operation.Operation
import systems.vostok.taxi.drive.app.util.constant.OperationName
import systems.vostok.taxi.drive.app.util.exception.OperationExecutionException

import static systems.vostok.taxi.drive.app.util.constant.OperationName.EDIT_CLIENT_OPERATION

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

class EditClientOperation implements Operation {
    OperationName operationName = EDIT_CLIENT_OPERATION

    @Autowired
    BasicRepository clientRepository

    @Override
    Object enroll(OperationContext context) {
        Client contextClient = clientRepository.convertToEntityType(context.operationRequest.body) as Client
        Client persistentClient = clientRepository.getOne(contextClient.login) as Client

        if (!persistentClient) {
            throw new OperationExecutionException('Client with target ID does not exist')
        }

        Client resultClient = clientRepository.save(contextClient)
        context.contextHelper.setContext(context, [before: persistentClient, after: resultClient])
    }

    @Override
    Object rollback(OperationContext context) {
        Map parsedContext = context.rollbackContextMessage.context
                .with(new JsonSlurper().&parseText) as Map

        Client contextClientBefore = parsedContext.before.with(clientRepository.&convertToEntityType)
        Client contextClientAfter = parsedContext.after.with(clientRepository.&convertToEntityType)
        Client persistentEntity = clientRepository.getOne(contextClientAfter.login)

        if (!contextClientAfter) {
            throw new OperationExecutionException('Rollback rejected: context client must not be null')
        }

        if (contextClientAfter != persistentEntity) {
            throw new OperationExecutionException('Rollback rejected: client was modified')
        }

        clientRepository.save(contextClientBefore)
        context.contextHelper.setContext(context, context.operationRequest.id)
    }
}
