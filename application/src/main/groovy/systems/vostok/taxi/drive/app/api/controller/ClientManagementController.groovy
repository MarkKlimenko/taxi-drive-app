package systems.vostok.taxi.drive.app.api.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import systems.vostok.taxi.drive.app.dao.entity.Ride
import systems.vostok.taxi.drive.app.service.ClientManagementService

import javax.ws.rs.*

import static systems.vostok.taxi.drive.app.util.constant.MediaType.APPLICATION_JSON

@Controller
@Path('api')
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
class ClientManagementController {

    @Autowired
    @Delegate
    ClientManagementService clientManagementService

    @GET
    @Path('client/check/{id}')
    checkUserById(@PathParam('id') String id) {
        checkClient(id)
    }

    @POST
    @Path('ride/evaluate')
    getPrice(Ride ride) {
        try {
            [status: "OK",
             price : calculatePrice(ride) as String]
        } catch (IllegalArgumentException e) {
            [status : "ERROR",
             message: e.message]
        }
    }

    @POST
    @Path('ride/new')
    addNewRide(Ride ride) {
        clientManagementService.addNewRide(ride)
    }

    @GET
    @Path('ride/active')
    getActiveRides() {
        clientManagementService.getActiveRides()
    }
}
