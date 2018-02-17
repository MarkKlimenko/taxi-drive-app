package systems.vostok.taxi.drive.app.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import systems.vostok.taxi.drive.app.dao.repository.BasicRepository

@Service
class CrudService {
    @Autowired
    List<BasicRepository> repositories

    def put(String entityType, Map entityMap) {
        BasicRepository repository = repositories.find{ entityType == it.entityType }
        def entity = repository.convertToEntityType(entityMap)
        repository.save(entity)
    }

    def getAll(String entityType) {
        repositories.find{ entityType == it.entityType }.findAll()
    }

    def getById(String entityType, String entityId) {
        repositories.find{ entityType == it.entityType }.getOne(entityId)
    }

    def deleteById(String entityType, String entityId) {
        repositories.find{ entityType == it.entityType }.delete(entityId)
        entityId
    }
}
