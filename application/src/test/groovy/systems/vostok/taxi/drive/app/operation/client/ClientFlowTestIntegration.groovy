package systems.vostok.taxi.drive.app.operation.client

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import systems.vostok.taxi.drive.app.dao.domain.operation.OperationResponse
import systems.vostok.taxi.drive.app.dao.repository.impl.ClientRepository

import static org.junit.jupiter.api.Assertions.assertThrows

@ExtendWith(SpringExtension.class)
@SpringBootTest
@DisplayName('Client CRUD flow tests')
class ClientFlowTestIntegration {
    @Autowired
    ClientRepository clientRepository

    @Autowired
    ClientFlowTestUtil clientUtil

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
        OperationResponse response = clientUtil.createClient('simple_client')
        clientUtil.checkClient('simple_client', response)
        clientUtil.rollbackCreateClient(response)
        clientUtil.checkClientNonexistence('simple_client')
    }

    @Test
    @DisplayName('Rollback edit client operation')
    void rollbackEditTest() {

    }

    @Test
    @DisplayName('Rollback delete client operation')
    void rollbackDeleteTest() {

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

    }

    @Test
    @DisplayName('Rollback of double edited client')
    void rollbackDoubleEditedTest() {

    }
}
