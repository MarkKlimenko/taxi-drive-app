package com.markklim.taxi.drive.app.api.controller

import com.markklim.taxi.drive.app.model.Person
import com.markklim.taxi.drive.app.model.Ride
import com.markklim.taxi.drive.app.service.PersonService
import com.markklim.taxi.drive.app.service.RideService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller

import javax.ws.rs.Consumes
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
    PersonService personService

    @Autowired
    RideService rideService

    @PUT
    @Path('person')
    putPerson(Person person) {
        personService.add(person)
    }

    @PUT
    @Path('ride')
    putRide(Ride ride) {
        rideService.add(ride)
    }
}
