package systems.vostok.taxi.drive.app.operation.client

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
class ClientFlowTestIntegration {
    @Autowired
    ClientRepository clientRepository

    @Autowired
    OperationService operationService

    @Test
    void createClient() {
        OperationRequest operationRequest = new OperationRequest(
                operationName: ADD_CLIENT_OPERATION.name,
                body: getJsonDataset('client', 'simple_client')
        )

        operationService.execute(ENROLL, operationRequest)
    }
}
