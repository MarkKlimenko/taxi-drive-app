package systems.vostok.taxi.drive.app.api.controller

import org.springframework.beans.factory.annotation.Autowired
import systems.vostok.taxi.drive.app.dao.repository.impl.ClientDao
import systems.vostok.taxi.drive.app.dao.repository.impl.RideRepository
import systems.vostok.taxi.drive.app.dao.repository.impl.SystemPropertyDao

import javax.ws.rs.Consumes
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.PathParam
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

@Path('api')
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
class ReadOnlyController {

    @Autowired
    ClientDao clientDao

    @Autowired
    RideRepository rideDao

    @Autowired
    SystemPropertyDao systemPropertyDao

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
}
