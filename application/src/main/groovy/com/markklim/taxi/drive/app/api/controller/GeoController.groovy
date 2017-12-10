package com.markklim.taxi.drive.app.api.controller

import com.markklim.taxi.drive.app.dao.entity.Street
import com.markklim.taxi.drive.app.service.GeoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller

import javax.ws.rs.Consumes
import javax.ws.rs.GET
import javax.ws.rs.POST
import javax.ws.rs.PUT
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

@Controller
@Path('api')
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
class GeoController {
    @Autowired
    GeoService geoService

    @GET
    @Path('geo/all')
    putClient() {
        geoService.getGeoInfo()
    }

    @PUT
    @Path('geo/street')
    putRide(Street street) {
        geoService.addStreet(street)
    }

    @POST
    @Path('geo/version/update')
    updateGeoVersion() {
        geoService.updateGeoVersion()
    }
}
