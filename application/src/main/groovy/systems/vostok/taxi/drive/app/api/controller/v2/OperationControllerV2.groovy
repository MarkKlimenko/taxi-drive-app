package systems.vostok.taxi.drive.app.api.controller.v2

import org.springframework.beans.factory.annotation.Autowired
import systems.vostok.taxi.drive.app.dao.domain.operation.OperationResponse
import systems.vostok.taxi.drive.app.executor.OperationService
import systems.vostok.taxi.drive.app.operation.OperationRequest

import javax.ws.rs.Consumes
import javax.ws.rs.POST
import javax.ws.rs.Path
import javax.ws.rs.Produces
import java.nio.ByteBuffer

import static systems.vostok.taxi.drive.app.util.constant.CustomMediaType.APPLICATION_JSON
import static systems.vostok.taxi.drive.app.util.constant.CustomMediaType.APPLICATION_OCTET_STREAM

@Path('api/v2/operation')
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_OCTET_STREAM)
class OperationControllerV2 {
    @Autowired
    OperationService operationService

    @POST
    OperationResponse execute(byte[] operationRequest) {
        ByteBuffer.wrap(operationRequest)
            .with(OperationRequest.&fromByteBuffer)
            .with(operationService.&execute)
    }
}
