package systems.vostok.taxi.drive.app.api.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import systems.vostok.taxi.drive.app.dao.repository.impl.GeoDao
import systems.vostok.taxi.drive.app.service.GeoService

import javax.ws.rs.Consumes
import javax.ws.rs.DELETE
import javax.ws.rs.GET
import javax.ws.rs.POST
import javax.ws.rs.PUT
import javax.ws.rs.Path
import javax.ws.rs.PathParam
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
    getGeoInfo() {
        geoService.getGeoInfo()
    }

    @GET
    @Path('geo/{entity}')
    getAllGeoEntities(@PathParam('entity') String entityType) {
        geoService.getAllGeoEntities(entityType)
    }

    @DELETE
    @Path('geo/{entityType}/{entityId}')
    deleteGeoEntity(@PathParam('entityType') String entityType,
               @PathParam('entityId') String entityId) {
        geoService.deleteGeoEntity(entityType, entityId)
    }



    @PUT
    @Path('geo/{entity}')
    putGeoEntity(@PathParam('entity') String entityType, Map entity) {
        geoService.putGeoEntity(entityType, entity)
    }




    @POST
    @Path('geo/version/update')
    updateGeoVersion() {
        geoService.updateGeoVersion()
    }
}
