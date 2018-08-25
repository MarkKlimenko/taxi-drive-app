package systems.vostok.taxi.drive.app.executor

import groovy.util.logging.Slf4j
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import systems.vostok.taxi.drive.app.dao.domain.operation.OperationContext
import systems.vostok.taxi.drive.app.operation.OperationRequest
import systems.vostok.taxi.drive.app.util.exception.OperationExecutionException

import java.nio.ByteBuffer

@Service
@Slf4j
class AsyncOperationHandler {
    @Autowired
    OperationService operationService

    @Autowired
    ContextHelper contextHelper

    @RabbitListener(queues = '${messaging.queue.operation-executor}')
    void executeOperation(byte[] rawMessage) {
        OperationContext operationContext = null

        try {
            OperationRequest operationRequest = OperationRequest.fromByteBuffer(ByteBuffer.wrap(rawMessage))
            log.debug("Execute operation with id: { ${operationRequest.id} }")

            operationContext = contextHelper.createAsyncOperationContext(operationRequest)

            operationService.executeSync(operationContext)
        } catch (Exception e) {
            if(operationContext) {
                contextHelper.setFailed(operationContext)
            }

            throw new OperationExecutionException(e)
        }
    }
}
