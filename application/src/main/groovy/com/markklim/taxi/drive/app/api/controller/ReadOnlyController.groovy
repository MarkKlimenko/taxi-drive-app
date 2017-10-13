package com.markklim.taxi.drive.app.api.controller

import com.markklim.taxi.drive.app.service.ClientService
import com.markklim.taxi.drive.app.service.RideService
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
    ClientService clientService

    @Autowired
    RideService rideService

    @GET
    @Path('client/all')
    getAllClients() {
        clientService.getAll()
    }

    @GET
    @Path('client/login/{login}')
    getClientByLogin(@PathParam('login') String login) {
        clientService.getByLogin(login)
    }

    @GET
    @Path('ride/all')
    getAllRides() {
        rideService.getAll()
    }
}
