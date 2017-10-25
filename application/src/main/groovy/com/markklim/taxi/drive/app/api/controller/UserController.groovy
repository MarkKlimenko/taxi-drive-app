package com.markklim.taxi.drive.app.api.controller

import com.markklim.taxi.drive.app.model.User
import com.markklim.taxi.drive.app.service.entity.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller

import javax.ws.rs.Consumes
import javax.ws.rs.GET
import javax.ws.rs.PUT
import javax.ws.rs.Path
import javax.ws.rs.PathParam
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

@Controller
@Path("api")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
class UserController {

    @Autowired
    UserService userService

    @GET
    @Path('user/all')
    getAllUsers() {
        userService.getAll()
    }

    @GET
    @Path('user/email/{email}')
    getUserByEmail(@PathParam('email') String email) {
        userService.getByEmail(email)
    }

    @PUT
    @Path('user/create')
    createUser(User user) {
        userService.add(user)
    }
}
