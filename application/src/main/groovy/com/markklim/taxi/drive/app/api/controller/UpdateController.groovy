package com.markklim.taxi.drive.app.api.controller

import com.markklim.taxi.drive.app.dao.impl.RideDao
import com.markklim.taxi.drive.app.dao.entity.Client
import com.markklim.taxi.drive.app.dao.entity.Ride
import com.markklim.taxi.drive.app.dao.impl.ClientDao
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller

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
    RideDao rideDao

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
