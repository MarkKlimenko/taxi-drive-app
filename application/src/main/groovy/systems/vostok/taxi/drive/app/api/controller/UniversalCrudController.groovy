package systems.vostok.taxi.drive.app.api.controller

import org.springframework.beans.factory.annotation.Autowired
import systems.vostok.taxi.drive.app.dao.repository.sql.UniversalCrudRepository

import javax.ws.rs.Consumes
import javax.ws.rs.DELETE
import javax.ws.rs.GET
import javax.ws.rs.PUT
import javax.ws.rs.Path
import javax.ws.rs.PathParam
import javax.ws.rs.Produces

import static systems.vostok.taxi.drive.app.util.constant.MediaType.APPLICATION_JSON

@Path('api')
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
class UniversalCrudController {
    @Autowired
    UniversalCrudRepository crudRepository

    @PUT
    @Path('{entityType}')
    putGeoEntity(@PathParam('entityMap') String entityType, Map entityMap) {
        crudRepository.put(entityType, entityMap)
    }

    @GET
    @Path('{entityType}')
    getAllGeoEntities(@PathParam('entityType') String entityType) {
        crudRepository.getAll(entityType)
    }

    @GET
    @Path('{entityType}/{entityId}')
    getGeoEntity(@PathParam('entityType') String entityType,
                 @PathParam('entityId') String entityId) {
        crudRepository.getById(entityType, entityId)
    }

    @DELETE
    @Path('{entityType}/{entityId}')
    deleteGeoEntity(@PathParam('entityType') String entityType,
                    @PathParam('entityId') String entityId) {
        crudRepository.deleteById(entityType, entityId)
    }
}
