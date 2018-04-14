package systems.vostok.taxi.drive.app.api.controller

import org.springframework.beans.factory.annotation.Autowired
import systems.vostok.taxi.drive.app.dao.repository.UniversalCrudRepository
import systems.vostok.taxi.drive.app.dao.repository.criteria.QueryFilter
import systems.vostok.taxi.drive.app.dao.repository.criteria.QuerySorter

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
    putEntity(@PathParam('entityType') String entityType, Map entityMap) {
        crudRepository.put(entityType, entityMap)
    }

    @GET
    @Path('{entityType}')
    getFilteredEntities(@PathParam('entityType') String entityType,
                        @QueryParam('filter') List<QueryFilter> filter,
                        @QueryParam('sorter') List<QuerySorter> sorter) {
        crudRepository.findByCriteria(entityType, filter, sorter)
    }

    @GET
    @Path('{entityType}/{entityId}')
    getEntityById(@PathParam('entityType') String entityType,
                  @PathParam('entityId') String entityId) {
        crudRepository.findById(entityType, entityId)
    }

    @DELETE
    @Path('{entityType}/{entityId}')
    deleteEntity(@PathParam('entityType') String entityType,
                 @PathParam('entityId') String entityId) {
        crudRepository.deleteById(entityType, entityId)
    }

    @GET
    @Path('{entityType}/count')
    countEntities(@PathParam('entityType') String entityType) {
        crudRepository.count(entityType)
    }
}
