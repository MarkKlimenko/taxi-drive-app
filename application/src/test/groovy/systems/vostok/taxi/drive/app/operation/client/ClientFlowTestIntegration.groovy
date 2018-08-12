package systems.vostok.taxi.drive.app.operation.client

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import systems.vostok.taxi.drive.app.dao.domain.operation.OperationRequest
import systems.vostok.taxi.drive.app.dao.repository.impl.ClientRepository
import systems.vostok.taxi.drive.app.executor.OperationService

import static systems.vostok.taxi.drive.app.dao.domain.operation.CoreOperationNames.ADD_CLIENT_OPERATION
import static systems.vostok.taxi.drive.app.dao.domain.operation.OperationDirections.ENROLL
import static systems.vostok.taxi.drive.app.test.Dataset.getJsonDataset

@ExtendWith(SpringExtension.class)
@SpringBootTest
@DisplayName('Client CRUD flow tests')
class ClientFlowTestIntegration {
    @Autowired
    ClientRepository clientRepository

    @Autowired
    OperationService operationService

    @Test
    @DisplayName('Create client')
    void createTest() {
        OperationRequest operationRequest = new OperationRequest(
                operationName: ADD_CLIENT_OPERATION.name,
                body: getJsonDataset('client', 'simple_client')
        )

        operationService.execute(ENROLL, operationRequest)
    }

    @Test
    @DisplayName('Edit client')
    void editTest() {

    }

    @Test
    @DisplayName('Delete client')
    void deleteTest() {

    }

    @Test
    @DisplayName('Rollback delete client operation')
    void rollbackDeleteTest() {

    }

    @Test
    @DisplayName('Rollback edit client operation')
    void rollbackEditTest() {

    }

    @Test
    @DisplayName('Rollback create client operation')
    void rollbackCreationTest() {

    }

    @Test
    @DisplayName('Create already existing client')
    void editAlreadyExistingTest() {

    }

    @Test
    @DisplayName('Create nonexistent client')
    void deleteNonexistentTest() {

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
