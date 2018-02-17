package systems.vostok.taxi.drive.app.api.controller

import systems.vostok.taxi.drive.app.dao.repository.sql.impl.RideRepositoryT
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import systems.vostok.taxi.drive.app.dao.entity.Client
import systems.vostok.taxi.drive.app.dao.entity.Ride
import systems.vostok.taxi.drive.app.dao.repository.sql.impl.ClientDao

import javax.ws.rs.Consumes
import javax.ws.rs.DELETE
import javax.ws.rs.PUT
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

@Controller
@Path('api')
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
class UpdateController {
    @Autowired
    ClientDao clientDao

    @Autowired
    RideRepositoryT rideDao

    @PUT
    @Path('client')
    putClient(Client client) {
        clientDao.add(client)
    }

    @DELETE
    @Path('client')
    deleteClient(Client client) {
        clientDao.delete(client)
    }

    @PUT
    @Path('ride')
    putRide(Ride ride) {
        rideDao.add(ride)
    }
}
