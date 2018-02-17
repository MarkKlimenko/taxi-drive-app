package systems.vostok.taxi.drive.app.api.controller

import org.springframework.beans.factory.annotation.Autowired
import systems.vostok.taxi.drive.app.dao.entity.Client
import systems.vostok.taxi.drive.app.dao.entity.Ride
import systems.vostok.taxi.drive.app.dao.repository.sql.impl.ClientDao
import systems.vostok.taxi.drive.app.dao.repository.sql.impl.RideRepositoryT
import systems.vostok.taxi.drive.app.dao.repository.sql.impl.SystemPropertyDao

import javax.ws.rs.Consumes
import javax.ws.rs.DELETE
import javax.ws.rs.GET
import javax.ws.rs.PUT
import javax.ws.rs.Path
import javax.ws.rs.PathParam
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

@Path('api')
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
class UniversalCrudController {

/*//    @Autowired
//    ClientDao clientDao
//
//    @Autowired
//    RideRepositoryT rideDao
//
//    @Autowired
//    SystemPropertyDao systemPropertyDao

    @GET
    @Path('client/all')
    getAllClients() {
        clientDao.getAll()
    }

    @GET
    @Path('client/login/{login}')
    getClientByLogin(@PathParam('login') String login) {
        clientDao.getByLogin(login)
    }

    @GET
    @Path('system/property/{property}')
    getSystemProperty(@PathParam('property') String property) {
        systemPropertyDao.getValue(property)
    }

//    @Autowired
//    RideRepositoryT rideDao

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
    }*/
}
