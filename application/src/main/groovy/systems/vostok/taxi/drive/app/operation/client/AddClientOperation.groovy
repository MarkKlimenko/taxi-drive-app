package systems.vostok.taxi.drive.app.operation.client

import groovy.json.JsonSlurper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import systems.vostok.taxi.drive.app.dao.domain.operation.OperationContext
import systems.vostok.taxi.drive.app.dao.entity.Client
import systems.vostok.taxi.drive.app.dao.repository.BasicRepository
import systems.vostok.taxi.drive.app.operation.Operation
import systems.vostok.taxi.drive.app.util.constant.OperationName
import systems.vostok.taxi.drive.app.util.exception.OperationExecutionException

import static OperationName.ADD_CLIENT_OPERATION

/*
enroll
 {
    "operationName": "ADD_CLIENT",
    "direction": "enroll",
    "body": { }
 }

rollback
 {
    "id": "51ae64c4-3327-4b73-9498-1fa3347d2a15",
    "operationName": "ADD_CLIENT",
    "direction": "rollback"
 }
*/

@Component
class AddClientOperation implements Operation {
    OperationName operationName = ADD_CLIENT_OPERATION

    @Autowired
    BasicRepository clientRepository

    @Override
    Object enroll(OperationContext context) {
        Client client = clientRepository.convertToEntityType(context.operationRequest.body) as Client

        if (clientRepository.getOne(client.login)) {
            throw new OperationExecutionException('Client with target ID already exists')
        }

        clientRepository.save(client)
                .with { context.contextHelper.setContext(context, it); it }
    }

    @Override
    Object rollback(OperationContext context) {
        Client contextClient = context.rollbackContextMessage.context
                .with(new JsonSlurper().&parseText)
                .with(clientRepository.&convertToEntityType) as Client

        Client persistentClient = clientRepository.getOne(contextClient.login) as Client

        if (!contextClient) {
            throw new OperationExecutionException('Rollback rejected: context entity must not be null')
        }
        if (contextClient != persistentClient) {
            throw new OperationExecutionException('Rollback rejected: entity was modified')
        }

        clientRepository.deleteById(contextClient.login)
        context.contextHelper.setContext(context, context.operationRequest.id)
    }
}
