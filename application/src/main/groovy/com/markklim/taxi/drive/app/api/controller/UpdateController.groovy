package com.markklim.taxi.drive.app.api.controller

import com.markklim.taxi.drive.app.model.Client
import com.markklim.taxi.drive.app.model.PriceDtd
import com.markklim.taxi.drive.app.model.Ride
import com.markklim.taxi.drive.app.service.EvaluationService
import com.markklim.taxi.drive.app.service.entity.ClientService
import com.markklim.taxi.drive.app.service.entity.RideService
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
    ClientService clientService

    @Autowired
    RideService rideService

    @Autowired
    EvaluationService evaluationService

    @PUT
    @Path('client')
    putClient(Client client) {
        clientService.add(client)
    }

    @PUT
    @Path('ride')
    putRide(Ride ride) {
        rideService.add(ride)
    }

    @PUT
    @Path('price/dtd')
    putPriceDtd(PriceDtd priceDtd) {
        evaluationService.addPriceDtd(priceDtd)
    }
}
