package com.markklim.taxi.drive.app.api.controller

import org.springframework.stereotype.Controller

import javax.ws.rs.Consumes
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

@Controller
@Path('service')
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
class StatusController {
    @GET
    @Path('status')
    getStatus() {
        [service:'available']
    }
}
