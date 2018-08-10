package systems.vostok.taxi.drive.app.operation.client

import groovy.json.JsonSlurper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import systems.vostok.taxi.drive.app.dao.domain.operation.OperationContext
import systems.vostok.taxi.drive.app.dao.entity.Client
import systems.vostok.taxi.drive.app.dao.entity.geo.GeoEntity
import systems.vostok.taxi.drive.app.dao.repository.BasicRepository
import systems.vostok.taxi.drive.app.operation.Operation
import systems.vostok.taxi.drive.app.util.constant.OperationName
import systems.vostok.taxi.drive.app.util.exception.OperationExecutionException

import static systems.vostok.taxi.drive.app.util.constant.OperationName.DELETE_CLIENT_OPERATION

/*
enroll
 {
    "operationName": "DELETE_CLIENT",
    "direction": "enroll",
    "body": {
        "id": "89147217660"
    }
 }

rollback
 {
    "id": "51ae64c4-3327-4b73-9498-1fa3347d2a15",
    "operationName": "DELETE_CLIENT",
    "direction": "rollback"
 }
*/

@Component
class DeleteClientOperation implements Operation {
    OperationName operationName = DELETE_CLIENT_OPERATION

    @Autowired
    BasicRepository clientRepository

    @Override
    Object enroll(OperationContext context) {
        Client targetClient = clientRepository.getOne(context.operationRequest.body.id) as Client

        if(!targetClient) {
            throw new OperationExecutionException('Client with target ID does not exist')
        }

        clientRepository.delete(targetClient)
        context.contextHelper.setContext(context, targetClient)
    }

    @Override
    Object rollback(OperationContext context) {
        Client contextClient = context.rollbackContextMessage.context
                .with(new JsonSlurper().&parseText)
                .with(clientRepository.&convertToEntityType) as Client

        if(!contextClient) {
            throw new OperationExecutionException('Rollback rejected: client must not be null')
        }
        if(clientRepository.getOne(contextClient.login)) {
            throw new OperationExecutionException('Rollback rejected: client already exists')
        }

        clientRepository.save(contextClient)
        context.contextHelper.setContext(context, context.operationRequest.id)
    }
}
