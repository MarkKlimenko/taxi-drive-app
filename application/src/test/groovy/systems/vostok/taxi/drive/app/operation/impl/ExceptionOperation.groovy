package systems.vostok.taxi.drive.app.operation.impl

import org.springframework.stereotype.Component
import systems.vostok.taxi.drive.app.dao.domain.operation.OperationContext
import systems.vostok.taxi.drive.app.operation.CoreOperation

@Component
class ExceptionOperation<T, ID extends Serializable> implements CoreOperation {
    String operationName = 'CORE_EXCEPTION_OPERATION'
    String operationTimeout

    static final String ENROLL_EXCEPTION_MESSAGE = 'Enroll operation exception in ExceptionOperation'
    static final String ROLLBACK_EXCEPTION_MESSAGE = 'Rollback operation exception in ExceptionOperation'

    @Override
    Object enroll(OperationContext context) {
        throw new NullPointerException(ENROLL_EXCEPTION_MESSAGE)
    }

    @Override
    Object rollback(OperationContext context) {
        throw new NullPointerException(ROLLBACK_EXCEPTION_MESSAGE)
    }
}
