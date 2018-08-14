package systems.vostok.taxi.drive.app.operation

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import systems.vostok.taxi.drive.app.dao.domain.operation.CoreOperationNames
import systems.vostok.taxi.drive.app.dao.domain.operation.OperationRequest
import systems.vostok.taxi.drive.app.dao.domain.operation.OperationResponse
import systems.vostok.taxi.drive.app.dao.entity.Client
import systems.vostok.taxi.drive.app.dao.repository.impl.ClientRepository
import systems.vostok.taxi.drive.app.executor.OperationService

import static org.junit.jupiter.api.Assertions.assertEquals
import static org.junit.jupiter.api.Assertions.assertFalse
import static systems.vostok.taxi.drive.app.dao.domain.operation.CoreOperationNames.*
import static systems.vostok.taxi.drive.app.dao.domain.operation.OperationDirections.ENROLL
import static systems.vostok.taxi.drive.app.dao.domain.operation.OperationDirections.ROLLBACK
import static systems.vostok.taxi.drive.app.test.Dataset.getJsonDataset

@Component
class OperationFlowTestUtil {
    @Autowired
    OperationService operationService

    OperationResponse rollbackOperation(CoreOperationNames operationName, OperationResponse response) {
        OperationRequest rollbackRequest = new OperationRequest(
                operationName: operationName.name,
                body: [id: response.id.toString()]
        )

        operationService.execute(ROLLBACK, rollbackRequest)
    }
}
