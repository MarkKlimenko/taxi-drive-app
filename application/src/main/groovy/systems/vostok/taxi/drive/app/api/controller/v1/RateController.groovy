package systems.vostok.taxi.drive.app.api.controller.v1

import org.glassfish.jersey.media.multipart.FormDataParam
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import systems.vostok.taxi.drive.app.executor.OperationService
import systems.vostok.taxi.drive.app.operation.OperationRequest

import javax.ws.rs.Consumes
import javax.ws.rs.POST
import javax.ws.rs.Path
import javax.ws.rs.Produces
import java.nio.ByteBuffer

import static systems.vostok.taxi.drive.app.dao.domain.operation.CoreOperationNames.UPLOAD_CTC_RATES
import static systems.vostok.taxi.drive.app.dao.domain.operation.CoreOperationNames.UPLOAD_DTD_RATES
import static systems.vostok.taxi.drive.app.operation.OperationDirection.enroll
import static systems.vostok.taxi.drive.app.util.constant.CustomMediaType.APPLICATION_JSON
import static systems.vostok.taxi.drive.app.util.constant.CustomMediaType.MULTIPART_FORM_DATA

@Controller
@Path('api')
@Produces(APPLICATION_JSON)
class RateController {
    @Autowired
    OperationService operationService

    @POST
    @Path('rate-dtd/upload')
    @Consumes(MULTIPART_FORM_DATA)
    uploadDtdConfig(@FormDataParam('file') InputStream file) {
        OperationRequest operationRequest = new OperationRequest(
                operationName: UPLOAD_DTD_RATES.name,
                direction: enroll,
                bytePayload: ByteBuffer.wrap(file.bytes)
        )

        operationService.execute(operationRequest)
    }

    @POST
    @Path('rate-ctc/upload')
    @Consumes(MULTIPART_FORM_DATA)
    uploadCtcConfig(@FormDataParam('file') InputStream file) {
        OperationRequest operationRequest = new OperationRequest(
                operationName: UPLOAD_CTC_RATES.name,
                direction: enroll,
                bytePayload: ByteBuffer.wrap(file.bytes)
        )

        operationService.execute(operationRequest)
    }
}