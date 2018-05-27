package systems.vostok.taxi.drive.app.service

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Service

import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@Service
@ConfigurationProperties(prefix = 'project')
class UtilService {
    String version

    @PersistenceContext
    EntityManager entityManager

    String getSqlStatus() {
        try {
            entityManager.createNativeQuery('SELECT version()')
                    .getSingleResult() as String
        } catch (Exception e) {
            e.toString()
        }
    }
}
