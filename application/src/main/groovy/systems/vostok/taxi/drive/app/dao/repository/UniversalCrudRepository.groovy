package systems.vostok.taxi.drive.app.dao.repository

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import systems.vostok.taxi.drive.app.dao.repository.criteria.QueryFilter
import systems.vostok.taxi.drive.app.dao.repository.criteria.QuerySorter

/**
 * Methods def put and def putAll could be applied for both - direct entity object and entity map
 * def return type is required
 */

@Service
class UniversalCrudRepository {
    @Autowired
    List<BasicRepository> repositories

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

    def findByCriteria(String entityType, List<QueryFilter> filter, List<QuerySorter> sorter) {
        findRepository(entityType).findByCriteria(filter, sorter)
    }

    def findById(String entityType, String entityId) {
        BasicRepository repository = findRepository(entityType)
        repository.convertToIdType(entityId)
            .with(repository.&findOne)
    }

    void deleteById(String entityType, String entityId) {
        BasicRepository repository = findRepository(entityType)
        repository.convertToIdType(entityId)
                .with(repository.&delete)
    }

    private BasicRepository findRepository(String entityType) {
        repositories.find { entityType == it.entityType }
    }
}
