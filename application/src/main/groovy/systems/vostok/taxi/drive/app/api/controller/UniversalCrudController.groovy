package systems.vostok.taxi.drive.app.api.controller

import org.springframework.beans.factory.annotation.Autowired
import systems.vostok.taxi.drive.app.dao.repository.UniversalCrudRepository

import javax.ws.rs.*

import static systems.vostok.taxi.drive.app.util.constant.MediaType.APPLICATION_JSON

@Path('api')
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
class UniversalCrudController {
    @Autowired
    UniversalCrudRepository crudRepository

    @PUT
    @Path('{entityType}')
    putGeoEntity(@PathParam('entityType') String entityType, Map entityMap) {
        crudRepository.put(entityType, entityMap)
    }

    @GET
    @Path('{entityType}')
    getAllGeoEntities(@PathParam('entityType') String entityType) {
        crudRepository.findAll(entityType)
    }

    @GET
    @Path('{entityType}/{entityId}')
    getGeoEntity(@PathParam('entityType') String entityType,
                 @PathParam('entityId') String entityId) {
        crudRepository.findById(entityType, entityId)
    }

    @DELETE
    @Path('{entityType}/{entityId}')
    deleteGeoEntity(@PathParam('entityType') String entityType,
                    @PathParam('entityId') String entityId) {
        crudRepository.deleteById(entityType, entityId)
    }
}
