package systems.vostok.taxi.drive.app.operation.impl

import org.springframework.stereotype.Component
import systems.vostok.taxi.drive.app.dao.domain.operation.OperationContext
import systems.vostok.taxi.drive.app.operation.CoreOperation

import static systems.vostok.taxi.drive.app.util.ContentTypeConverter.toMap

@Component
class SimpleOperation<T, ID extends Serializable> implements CoreOperation {
    String operationName = 'CORE_SIMPLE_OPERATION'
    String operationTimeout

    @Override
    Object enroll(OperationContext context) {
        Map requestBody = toMap(context.operationRequest.body)

        5 * requestBody.sum
    }

    @Override
    Object rollback(OperationContext context) {
        10
    }
}
