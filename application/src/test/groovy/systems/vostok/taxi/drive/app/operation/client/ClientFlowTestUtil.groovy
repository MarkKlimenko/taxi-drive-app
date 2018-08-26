package systems.vostok.taxi.drive.app.operation.client

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import systems.vostok.taxi.drive.app.dao.domain.operation.OperationResponse
import systems.vostok.taxi.drive.app.dao.entity.Client
import systems.vostok.taxi.drive.app.dao.repository.impl.ClientRepository
import systems.vostok.taxi.drive.app.executor.OperationService
import systems.vostok.taxi.drive.app.operation.OperationRequest

import static org.junit.jupiter.api.Assertions.assertEquals
import static org.junit.jupiter.api.Assertions.assertFalse
import static systems.vostok.taxi.drive.app.dao.domain.operation.CoreOperationNames.*
import static systems.vostok.taxi.drive.app.operation.OperationDirection.enroll
import static systems.vostok.taxi.drive.app.test.Dataset.getJsonDataset
import static systems.vostok.taxi.drive.app.test.Dataset.getRawJsonDataset

@Component
class ClientFlowTestUtil {
    @Autowired
    ClientRepository clientRepository

    @Autowired
    OperationService operationService

    void removeAllClients() {
        clientRepository.deleteAll()
    }

    OperationResponse createClient(String datasetName) {
        OperationRequest operationRequest = new OperationRequest(
                operationName: ADD_CLIENT_OPERATION.name,
                direction: enroll,
                stringPayload: getRawJsonDataset('client', datasetName)
        )

        operationService.execute(operationRequest)
    }

    OperationResponse editClient(String datasetName) {
        OperationRequest operationRequest = new OperationRequest(
                operationName: EDIT_CLIENT_OPERATION.name,
                direction: enroll,
                stringPayload: getRawJsonDataset('client', datasetName)
        )

        operationService.execute(operationRequest)
    }

    OperationResponse deleteClient(String datasetName) {
        OperationRequest operationRequest = new OperationRequest(
                operationName: DELETE_CLIENT_OPERATION.name,
                direction: enroll,
                stringPayload: getRawJsonDataset('client', datasetName)
        )

        operationService.execute(operationRequest)
    }

    void checkClient(String datasetName, OperationResponse response) {
        Client expectedClient = getJsonDataset('client', datasetName) as Client
        Client actualResponseClient = response.body as Client
        Client actualClient = clientRepository.findById(expectedClient.login).get()

        expectedClient.with {
            assertEquals(it, actualResponseClient)
            assertEquals(it, actualClient)
        }
    }

    void checkClientNonexistence(String datasetName) {
        Client expectedClient = getJsonDataset('client', datasetName) as Client
        assertFalse(clientRepository.existsById(expectedClient.login))
    }
}
