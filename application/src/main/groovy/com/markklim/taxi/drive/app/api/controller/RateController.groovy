package com.markklim.taxi.drive.app.api.controller

import com.markklim.taxi.drive.app.service.RateService
import org.glassfish.jersey.media.multipart.FormDataParam
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller

import javax.ws.rs.Consumes
import javax.ws.rs.POST
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

@Controller
@Path('api')
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.MULTIPART_FORM_DATA)
class RateController {
    @Autowired
    RateService rateService

    @POST
    @Path('dtd/config/upload')
    uploadDtdConfig(@FormDataParam("file") InputStream file) {
        rateService.uploadDtdConfig(file)
        [status: 'success']
    }

    @POST
    @Path('ctc/config/upload')
    uploadCtcConfig(@FormDataParam("file") InputStream file) {
        rateService.uploadCtcConfig(file)
        [status: 'success']
    }
}