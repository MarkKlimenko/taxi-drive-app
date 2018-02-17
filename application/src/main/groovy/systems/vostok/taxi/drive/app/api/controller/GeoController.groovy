package systems.vostok.taxi.drive.app.api.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import systems.vostok.taxi.drive.app.service.GeoService

import javax.ws.rs.*
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
    getGeoInfo() {
        geoService.getGeoInfo()
    }

    @GET
    @Path('geo/{entityType}')
    getAllGeoEntities(@PathParam('entityType') String entityType) {
        geoService.getAllGeoEntities(entityType)
    }

    @DELETE
    @Path('geo/{entityType}/{entityId}')
    deleteGeoEntity(@PathParam('entityType') String entityType,
                    @PathParam('entityId') String entityId) {
        geoService.deleteGeoEntity(entityType, entityId)
    }

    @PUT
    @Path('geo/{entityMap}')
    putGeoEntity(@PathParam('entityMap') String entityType, Map entityMap) {
        geoService.putGeoEntity(entityType, entityMap)
    }

    @POST
    @Path('geo/version/update')
    updateGeoVersion() {
        geoService.updateGeoCache()
    }
}
