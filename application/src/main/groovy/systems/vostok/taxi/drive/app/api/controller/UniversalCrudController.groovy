package systems.vostok.taxi.drive.app.api.controller

import javax.ws.rs.Consumes
import javax.ws.rs.Path
import javax.ws.rs.Produces

import static systems.vostok.taxi.drive.app.util.constant.MediaType.APPLICATION_JSON

@Path('api')
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
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
