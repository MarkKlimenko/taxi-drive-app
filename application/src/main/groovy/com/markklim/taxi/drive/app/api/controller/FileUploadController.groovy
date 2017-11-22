package com.markklim.taxi.drive.app.api.controller

import com.markklim.taxi.drive.app.service.settings.FillPriceTableService
import org.glassfish.jersey.media.multipart.FormDataContentDisposition
import org.glassfish.jersey.media.multipart.FormDataParam
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller

import javax.ws.rs.Consumes
import javax.ws.rs.POST
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

/**
 * Created by viktor on 02.11.17.
 */
@Controller
@Path('api')
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.MULTIPART_FORM_DATA)
class FileUploadController {
    @Autowired
    FillPriceTableService fillPriceTableService

    @POST
    @Path('file/pricing')
    putPriceListFile(@FormDataParam("file") InputStream inputFile,
                     @FormDataParam("file") FormDataContentDisposition fileMetaData){
        fillPriceTableService.fillPriceTableFromExcel(inputFile, fileMetaData)
    }
}