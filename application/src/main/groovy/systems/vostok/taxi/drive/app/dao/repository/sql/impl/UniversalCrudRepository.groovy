package systems.vostok.taxi.drive.app.dao.repository.sql.impl

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import systems.vostok.taxi.drive.app.dao.repository.sql.BasicRepository

@Service
class UniversalCrudRepository {
    @Autowired
    List<BasicRepository> repositories

    def put(String entityType, Map entityMap) {
        BasicRepository repository = repositories.find { entityType == it.entityType }
        def entity = repository.convertToEntityType(entityMap)
        repository.save(entity)
    }

    def getAll(String entityType) {
        repositories.find { entityType == it.entityType }.findAll()
    }

    def getById(String entityType, String entityId) {
        BasicRepository repository = repositories.find { entityType == it.entityType }
        def id = repository.convertToIdType(entityId)
        repository.findOne(id)
    }

    def deleteById(String entityType, String entityId) {
        BasicRepository repository = repositories.find { entityType == it.entityType }
        def id = repository.convertToIdType(entityId)
        repository.delete(id)
        entityId
    }
}
