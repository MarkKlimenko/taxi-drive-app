package systems.vostok.taxi.drive.app.dao.repository

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import systems.vostok.taxi.drive.app.dao.domain.util.QueryFilter
import systems.vostok.taxi.drive.app.dao.domain.util.QueryPagination
import systems.vostok.taxi.drive.app.dao.domain.util.QuerySorter
import systems.vostok.taxi.drive.app.dao.domain.util.SearchParameters
import systems.vostok.taxi.drive.app.dao.repository.util.RepositoryResolver

/**
 * Methods def put and def putAll could be applied for both - direct entity object and entity map
 * def return type is required
 */

@Component
class UniversalCrudRepository {
    @Autowired
    @Delegate
    RepositoryResolver repositoryResolver

    def put(String entityType, Object entityObject) {
        BasicRepository repository = findRepository(entityType)
        repository.convertToEntityType(entityObject)
                .with(repository.&save)
    }

    def putAll(String entityType, Collection<Object> entityList) {
        BasicRepository repository = findRepository(entityType)
        entityList.collect(repository.&convertToEntityType)
                .with(repository.&save)
    }

    def findAll(String entityType) {
        findRepository(entityType).findAll()
    }

    def findByCriteria(String entityType, List<QueryFilter> filter, List<QuerySorter> sorter, QueryPagination pagination) {
        findRepository(entityType).findByCriteria(filter, sorter, pagination)
    }

    def search(String entityType, SearchParameters parameters) {
        findRepository(entityType).search(parameters)
    }

    def findById(String entityType, String entityId) {
        BasicRepository repository = findRepository(entityType)
        repository.convertToIdType(entityId)
                .with { repository.findById(it).get() }
    }

    void deleteById(String entityType, String entityId) {
        BasicRepository repository = findRepository(entityType)
        repository.convertToIdType(entityId)
                .with(repository.&delete)
    }

    Long count(String entityType) {
        findRepository(entityType).count()
    }
}
