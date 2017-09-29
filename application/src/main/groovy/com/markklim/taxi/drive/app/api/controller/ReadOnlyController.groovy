package com.markklim.taxi.drive.app.api.controller

import com.markklim.taxi.drive.app.model.Person
import com.markklim.taxi.drive.app.service.PersonService
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
    PersonService personService

    @Autowired
    RideService rideService

    @GET
    @Path('person/all')
    getAllPersons() {
        personService.getAll()
    }

    @GET
    @Path('person/login/{login}')
    getPersonByLogin(@PathParam('login') String login) {
        personService.getByLogin(login)
    }

    @GET
    @Path('ride/all')
    getAllRides() {
        rideService.getAll()
    }
}
