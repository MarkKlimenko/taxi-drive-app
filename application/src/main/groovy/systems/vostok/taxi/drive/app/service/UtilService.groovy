package systems.vostok.taxi.drive.app.service

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@Service
class UtilService {
    @Value('${project.version}')
    String version

    @PersistenceContext
    EntityManager entityManager

    String getSqlStatus() {
        try {
            entityManager.createNativeQuery("SELECT 'OK' FROM flyway_schema_history;")
                    .getSingleResult() as String
        } catch (Exception e) {
            e.toString()
        }
    }
}
