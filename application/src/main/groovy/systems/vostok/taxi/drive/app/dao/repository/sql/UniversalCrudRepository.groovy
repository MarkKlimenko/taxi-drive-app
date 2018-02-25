package systems.vostok.taxi.drive.app.dao.repository.sql

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import scala.collection.Iterable

/**
 * Methods def put and def putAll could be applied for both - direct entity object and entity map
 */

@Service
class UniversalCrudRepository {
    @Autowired
    List<BasicRepository> repositories

    def put(String entityType, Object entityObject) {
        BasicRepository repository = repositories.find { entityType == it.entityType }
        def entity = repository.convertToEntityType(entityObject)
        repository.save(entity)
    }

    def putAll(String entityType, Collection<Object> entityList) {
        BasicRepository repository = repositories.find { entityType == it.entityType }
        def entities = entityList.collect(repository.&convertToEntityType)
        repository.save(entities)
    }

    def findAll(String entityType) {
        repositories.find { entityType == it.entityType }.findAll()
    }

    def findById(String entityType, String entityId) {
        BasicRepository repository = repositories.find { entityType == it.entityType }
        def id = repository.convertToIdType(entityId)
        repository.findOne(id)
    }

    void deleteById(String entityType, String entityId) {
        BasicRepository repository = repositories.find { entityType == it.entityType }
        repository.convertToIdType(entityId)
            .with(repository.&delete)
    }
}
