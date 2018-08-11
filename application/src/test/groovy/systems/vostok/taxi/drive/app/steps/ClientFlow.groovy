package systems.vostok.taxi.drive.app.steps

import cucumber.api.java.en.When
import org.springframework.beans.factory.annotation.Autowired
import systems.vostok.taxi.drive.app.dao.domain.operation.OperationRequest
import systems.vostok.taxi.drive.app.dao.repository.impl.ClientRepository
import systems.vostok.taxi.drive.app.executor.OperationService

import static systems.vostok.taxi.drive.app.test.Dataset.getJsonDataset
import static systems.vostok.taxi.drive.app.dao.domain.operation.OperationDirection.ENROLL
import static systems.vostok.taxi.drive.app.dao.domain.operation.OperationName.ADD_CLIENT_OPERATION

class ClientFlow extends SpringConfig {
    @Autowired
    ClientRepository clientRepository

    @Autowired
    OperationService operationService

    @When(/^Create client '([^"]*)'$/)
    createClient(String clientDataset) {
        OperationRequest operationRequest = new OperationRequest(
                operationName: ADD_CLIENT_OPERATION.name,
                body: getJsonDataset('client', clientDataset)
        )

        operationService.process(ENROLL.type, operationRequest)
        ' '
    }
}
