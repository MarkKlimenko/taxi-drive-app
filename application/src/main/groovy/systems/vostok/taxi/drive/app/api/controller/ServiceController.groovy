package systems.vostok.taxi.drive.app.api.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import systems.vostok.taxi.drive.app.service.UtilService

import javax.ws.rs.Consumes
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces

import static systems.vostok.taxi.drive.app.util.constant.MediaType.APPLICATION_JSON

@Controller
@Path('service')
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
class ServiceController {
    @Autowired
    @Delegate
    UtilService utilService

    @GET
    @Path('status')
    getStatus() {
        [version  : version,
         db       : dbStatus,
         dbSupport: dbSupportStatus]
    }

    // TODO: Add update cache controller by entity
    // TODO: Add update cache controller all
}
