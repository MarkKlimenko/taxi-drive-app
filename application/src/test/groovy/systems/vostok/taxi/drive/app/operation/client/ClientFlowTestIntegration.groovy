package systems.vostok.taxi.drive.app.operation.client

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import systems.vostok.taxi.drive.app.dao.domain.operation.OperationResponse
import systems.vostok.taxi.drive.app.dao.repository.impl.ClientRepository
import systems.vostok.taxi.drive.app.operation.OperationFlowTestUtil

import static org.junit.jupiter.api.Assertions.assertThrows
import static systems.vostok.taxi.drive.app.dao.domain.operation.CoreOperationNames.*

@ExtendWith(SpringExtension.class)
@SpringBootTest
@DisplayName('Client CRUD flow tests')
class ClientFlowTestIntegration {
    @Autowired
    ClientRepository clientRepository

    @Autowired
    ClientFlowTestUtil clientUtil

    @Autowired
    OperationFlowTestUtil operationUtil

    @Test
    @DisplayName('Create client')
    void createTest() {
        clientUtil.removeAllClients()
        clientUtil.createClient('simple_client')
                .with { clientUtil.checkClient('simple_client', it) }
    }

    @Test
    @DisplayName('Edit client')
    void editTest() {
        clientUtil.removeAllClients()

        clientUtil.createClient('simple_client')
        clientUtil.editClient('simple_client_edited')
                .with { clientUtil.checkClient('simple_client_edited', it) }
    }

    @Test
    @DisplayName('Delete client')
    void deleteTest() {
        clientUtil.removeAllClients()

        clientUtil.createClient('simple_client')
        clientUtil.deleteClient('simple_client')
        clientUtil.checkClientNonexistence('simple_client')
    }

    @Test
    @DisplayName('Rollback create client operation')
    void rollbackCreationTest() {
        clientUtil.removeAllClients()

        OperationResponse addResponse = clientUtil.createClient('simple_client')
        clientUtil.checkClient('simple_client', addResponse)

        operationUtil.rollbackOperation(ADD_CLIENT_OPERATION, addResponse)
        clientUtil.checkClientNonexistence('simple_client')
    }

    @Test
    @DisplayName('Rollback edit client operation')
    void rollbackEditTest() {
        clientUtil.removeAllClients()

        OperationResponse addResponse = clientUtil.createClient('simple_client')
        OperationResponse editResponse = clientUtil.editClient('simple_client_edited')

        operationUtil.rollbackOperation(EDIT_CLIENT_OPERATION, editResponse)
        clientUtil.checkClient('simple_client', addResponse)
    }

    @Test
    @DisplayName('Rollback delete client operation')
    void rollbackDeleteTest() {
        clientUtil.removeAllClients()

        OperationResponse addResponse = clientUtil.createClient('simple_client')
        OperationResponse deleteResponse = clientUtil.deleteClient('simple_client')
        clientUtil.checkClientNonexistence('simple_client')

        operationUtil.rollbackOperation(DELETE_CLIENT_OPERATION, deleteResponse)
        clientUtil.checkClient('simple_client', addResponse)
    }

    @Test
    @DisplayName('Create already existing client')
    void editAlreadyExistingTest() {
        clientUtil.removeAllClients()
        clientUtil.createClient('simple_client')

        assertThrows(
                Exception.class,
                { clientUtil.createClient('simple_client') },
                'Entity with target ID already exists'
        )
    }

    @Test
    @DisplayName('Delete nonexistent client')
    void deleteNonexistentTest() {
        clientUtil.removeAllClients()

        assertThrows(
                Exception.class,
                { clientUtil.deleteClient('simple_client') },
                'Entity with target ID does not exist'
        )
    }

    @Test
    @DisplayName('Rollback creation of edited client')
    void rollbackCreationOfEditedTest() {
        clientUtil.removeAllClients()

        OperationResponse addResponse = clientUtil.createClient('simple_client')
        OperationResponse editResponse = clientUtil.editClient('simple_client_edited')

        assertThrows(
                Exception.class,
                { operationUtil.rollbackOperation(ADD_CLIENT_OPERATION, addResponse) },
                'Rollback rejected: entity was modified or removed'
        )

        clientUtil.checkClient('simple_client_edited', editResponse)
    }

    @Test
    @DisplayName('Rollback of double edited client')
    void rollbackDoubleEditedTest() {
        clientUtil.removeAllClients()

        clientUtil.createClient('simple_client')
        OperationResponse editFirstResponse = clientUtil.editClient('simple_client_edited')
        OperationResponse editSecondResponse = clientUtil.editClient('simple_client_edited_2')

        assertThrows(
                Exception.class,
                { operationUtil.rollbackOperation(EDIT_CLIENT_OPERATION, editFirstResponse) },
                'Rollback rejected: entity was modified or removed'
        )

        clientUtil.checkClient('simple_client_edited_2', editSecondResponse)
    }

    @Test
    @DisplayName('Rollback creation of already deleted client')
    void rollbackAlreadyDeletedTest() {
        clientUtil.removeAllClients()

        OperationResponse addResponse = clientUtil.createClient('simple_client')
        clientUtil.deleteClient('simple_client')

        assertThrows(
                Exception.class,
                { operationUtil.rollbackOperation(ADD_CLIENT_OPERATION, addResponse) },
                'Rollback rejected: entity was modified or removed'
        )

        clientUtil.checkClientNonexistence('simple_client')
    }
}
