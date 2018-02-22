package systems.vostok.taxi.drive.app.api.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import systems.vostok.taxi.drive.app.service.GeoService

import javax.ws.rs.*

import static systems.vostok.taxi.drive.app.util.constant.MediaType.APPLICATION_JSON

@Controller
@Path('api')
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
class GeoController {
    @Autowired
    GeoService geoService

    @GET
    @Path('geo/all')
    getGeoInfo() {
        geoService.getGeoInfo()
    }

    @GET
    @Path('geo/{entityType}')
    getAllGeoEntities(@PathParam('entityType') String entityType) {
        geoService.getAllGeoEntities(entityType)
    }

    @GET
    @Path('geo/{entityType}/{entityId}')
    getGeoEntity(@PathParam('entityType') String entityType,
                 @PathParam('entityId') String entityId) {
        geoService.getGeoEntity(entityType, entityId)
    }

    @DELETE
    @Path('geo/{entityType}/{entityId}')
    deleteGeoEntity(@PathParam('entityType') String entityType,
                    @PathParam('entityId') String entityId) {
        geoService.deleteGeoEntity(entityType, entityId)
    }

    @PUT
    @Path('geo/{entityType}')
    putGeoEntity(@PathParam('entityType') String entityType, Map entityMap) {
        geoService.putGeoEntity(entityType, entityMap)
    }

    @POST
    @Path('geo/version/update')
    updateGeoVersion() {
        geoService.updateGeoCache()
    }
}
