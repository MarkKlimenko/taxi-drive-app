package systems.vostok.taxi.drive.app.api.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import systems.vostok.taxi.drive.app.dao.entity.geo.City
import systems.vostok.taxi.drive.app.dao.entity.geo.Street
import systems.vostok.taxi.drive.app.dao.impl.GeoDao
import systems.vostok.taxi.drive.app.service.GeoService

import javax.ws.rs.Consumes
import javax.ws.rs.DELETE
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

    @Autowired
    GeoDao geoDao

    @GET
    @Path('geo/all')
    putClient() {
        geoService.getGeoInfo()
    }

    @GET
    @Path('geo/city/all')
    getAllCities() {
        geoDao.getAllCities()
    }

    @GET
    @Path('geo/street/all')
    getAllStreets() {
        geoDao.getAllStreets()
    }

    @GET
    @Path('geo/district/all')
    getAllDistricts() {
        geoDao.getAllDistricts()
    }

    @PUT
    @Path('geo/city')
    putCity(City city) {
        geoService.addCity(city)
    }

    @PUT
    @Path('geo/street')
    putRide(Street street) {
        geoService.addStreet(street)
    }

    @DELETE
    @Path('geo/city')
    deleteCity(City city) {
        geoService.deleteCity(city)
    }

    @DELETE
    @Path('geo/street')
    deleteStreet(Street street) {
        geoService.deleteStreet(street)
    }

    @POST
    @Path('geo/version/update')
    updateGeoVersion() {
        geoService.updateGeoVersion()
    }
}