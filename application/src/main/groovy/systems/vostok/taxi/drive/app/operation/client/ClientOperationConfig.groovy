package systems.vostok.taxi.drive.app.operation.client

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import systems.vostok.taxi.drive.app.dao.entity.geo.Country
import systems.vostok.taxi.drive.app.dao.repository.impl.ClientRepository
import systems.vostok.taxi.drive.app.operation.EntityAddOperation
import systems.vostok.taxi.drive.app.operation.EntityDeleteOperation
import systems.vostok.taxi.drive.app.operation.EntityEditOperation
import systems.vostok.taxi.drive.app.operation.Operation

import static systems.vostok.taxi.drive.app.dao.domain.operation.OperationName.*

@Configuration
class ClientOperationConfig {
    @Bean
    Operation addClient(ClientRepository clientRepository) {
        new EntityAddOperation<Country, String>(
                operationName: ADD_CLIENT_OPERATION,
                entityRepository: clientRepository
        )
    }

    @Bean
    Operation editClient(ClientRepository clientRepository) {
        new EntityEditOperation<Country, String>(
                operationName: EDIT_CLIENT_OPERATION,
                entityRepository: clientRepository
        )
    }

    @Bean
    Operation deleteClient(ClientRepository clientRepository) {
        new EntityDeleteOperation<Country, String>(
                operationName: DELETE_CLIENT_OPERATION,
                entityRepository: clientRepository
        )
    }
}
