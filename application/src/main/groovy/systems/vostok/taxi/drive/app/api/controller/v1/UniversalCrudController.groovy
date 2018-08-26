package systems.vostok.taxi.drive.app.api.controller.v1

import org.springframework.beans.factory.annotation.Autowired
import systems.vostok.taxi.drive.app.dao.repository.UniversalCrudRepository
import systems.vostok.taxi.drive.app.dao.domain.query.QueryFilter
import systems.vostok.taxi.drive.app.dao.domain.query.QueryPagination
import systems.vostok.taxi.drive.app.dao.domain.query.QuerySorter
import systems.vostok.taxi.drive.app.dao.domain.query.SearchParameters

import javax.validation.Valid
import javax.ws.rs.*

import static systems.vostok.taxi.drive.app.util.constant.CustomMediaType.APPLICATION_JSON

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
                        @QueryParam('sorter') List<QuerySorter> sorter,
                        @QueryParam('pagination') QueryPagination pagination) {
        crudRepository.findByCriteria(entityType, filter, sorter, pagination)
    }

    @GET
    @Path('{entityType}/search')
    searchByEntities(@PathParam('entityType') String entityType,
                     @QueryParam('parameters') @Valid SearchParameters parameters) {
        crudRepository.search(entityType, parameters)
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
