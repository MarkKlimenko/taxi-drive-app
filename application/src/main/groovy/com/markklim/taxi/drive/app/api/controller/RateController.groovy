package com.markklim.taxi.drive.app.api.controller


import com.markklim.taxi.drive.app.service.settings.FillPriceTableService
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
    FillPriceTableService fillPriceTableService

    @POST
    @Path('file/dtd/load')
    priceDtdListFile(@FormDataParam("file") InputStream inputFile){
        fillPriceTableService.fillPriceDtdTableFromExcel(inputFile)
    }

    @POST
    @Path('file/ctc/load')
    priceCtcListFile(@FormDataParam("file") InputStream inputFile){
        fillPriceTableService.fillPriceCtcTableFromExcel(inputFile)
    }
}