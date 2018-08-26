package systems.vostok.taxi.drive.app.operation

import groovy.json.JsonOutput
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import systems.vostok.taxi.drive.app.dao.domain.operation.CoreOperationNames
import systems.vostok.taxi.drive.app.dao.domain.operation.OperationResponse
import systems.vostok.taxi.drive.app.executor.OperationService

import static systems.vostok.taxi.drive.app.operation.OperationDirection.enroll
import static systems.vostok.taxi.drive.app.operation.OperationDirection.rollback

@Component
class OperationFlowTestUtil {
    @Autowired
    OperationService operationService

    OperationResponse enrollOperation(String operationName, Map body) {
        enrollOperationWithParameters(operationName, body, false)
    }

    OperationResponse enrollAsyncOperation(String operationName, Map body) {
        enrollOperationWithParameters(operationName, body, true)
    }

    private OperationResponse enrollOperationWithParameters(String operationName, Map body, Boolean async) {
        OperationRequest operationRequest = new OperationRequest(
                operationName: operationName,
                direction: enroll,
                async: async,
                stringPayload: JsonOutput.toJson(body)
        )

        operationService.execute(operationRequest)
    }

    OperationResponse rollbackOperation(CoreOperationNames operationName, OperationResponse response) {
        OperationRequest rollbackRequest = new OperationRequest(
                operationName: operationName.name,
                direction: rollback,
                stringPayload: JsonOutput.toJson([id: response.id.toString()])
        )

        operationService.execute(rollbackRequest)
    }
}
