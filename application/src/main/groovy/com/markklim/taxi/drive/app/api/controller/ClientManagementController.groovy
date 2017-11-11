package com.markklim.taxi.drive.app.api.controller

import com.markklim.taxi.drive.app.service.ClientManagementService
import org.springframework.beans.factory.annotation.Autowired

import javax.ws.rs.*
import javax.ws.rs.core.MediaType

@Path('api')
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
class ClientManagementController {

    @Autowired
    ClientManagementService clientManagementService

    @GET
    @Path('client/check/{id}')
    checkUserById(@PathParam('id') String id) {
        clientManagementService.checkUserById(id)
    }
}
