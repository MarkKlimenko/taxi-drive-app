package com.markklim.taxi.drive.app.api.controller

import com.markklim.taxi.drive.app.dao.impl.ClientDao
import com.markklim.taxi.drive.app.dao.impl.RideDao
import org.springframework.beans.factory.annotation.Autowired

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
    RideDao rideDao

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
    @Path('ride/all')
    getAllRides() {
        // rideDao.getAll()
    }
}
