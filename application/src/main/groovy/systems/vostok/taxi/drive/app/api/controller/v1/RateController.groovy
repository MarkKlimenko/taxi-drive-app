package systems.vostok.taxi.drive.app.api.controller.v1

import org.glassfish.jersey.media.multipart.FormDataParam
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import systems.vostok.taxi.drive.app.service.RateService

import javax.ws.rs.Consumes
import javax.ws.rs.POST
import javax.ws.rs.Path
import javax.ws.rs.Produces

import static systems.vostok.taxi.drive.app.util.constant.CustomMediaType.APPLICATION_JSON
import static systems.vostok.taxi.drive.app.util.constant.CustomMediaType.MULTIPART_FORM_DATA

@Controller
@Path('api')
@Produces(APPLICATION_JSON)
class RateController {
    @Autowired
    RateService rateService

    @POST
    @Path('price-dtd/upload')
    @Consumes(MULTIPART_FORM_DATA)
    uploadDtdConfig(@FormDataParam('file') InputStream file) {
        rateService.uploadDtdConfig(file)
    }

    @POST
    @Path('price-ctc/upload')
    @Consumes(MULTIPART_FORM_DATA)
    uploadCtcConfig(@FormDataParam('file') InputStream file) {
        rateService.uploadCtcConfig(file)
    }
}