package systems.vostok.taxi.drive.app.api.controller.v1

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import systems.vostok.taxi.drive.app.dao.entity.Ride
import systems.vostok.taxi.drive.app.service.ClientManagementService

import javax.ws.rs.*

import static systems.vostok.taxi.drive.app.util.constant.CustomMediaType.APPLICATION_JSON

@Controller
@Path('api')
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
class ClientManagementController {
    @Autowired
    ClientManagementService clientManagementService

    @GET
    @Path('client/check/{id}')
    checkUserById(@PathParam('id') String id) {
        clientManagementService.checkClient(id)
    }

    @POST
    @Path('ride/evaluate')
    evaluateRide(Ride ride) {
       clientManagementService.evaluateRide(ride)
    }

    @PUT
    @Path('ride')
    addNewRide(Ride ride) {
        clientManagementService.addNewRide(ride)
    }

    @GET
    @Path('ride/active')
    getActiveRides() {
        clientManagementService.getActiveRides()
    }
}
