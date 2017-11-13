package com.markklim.taxi.drive.app.api.controller

import com.markklim.taxi.drive.app.model.Address
import com.markklim.taxi.drive.app.service.entity.RideService
import org.springframework.beans.factory.annotation.Autowired

import javax.ws.rs.Consumes
import javax.ws.rs.POST
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

@Path('api/ride')
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
class RideController {

    @Autowired
    RideService rideService

    @POST
    @Path('price')
    getPrice(Address addressFrom, Address addressTo, String clientLogin) {
        rideService.getPrice(addressFrom, addressTo, clientLogin)
    }
}
