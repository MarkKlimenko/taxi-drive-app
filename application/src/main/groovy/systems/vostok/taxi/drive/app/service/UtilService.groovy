package systems.vostok.taxi.drive.app.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.data.cassandra.core.CassandraTemplate
import org.springframework.stereotype.Service

import javax.persistence.EntityManager
import javax.persistence.PersistenceContext


// TODO: Implement actuator
@Service
@ConfigurationProperties(prefix = 'project')
class UtilService {
    String version

    @PersistenceContext
    EntityManager entityManager

    @Autowired
    CassandraTemplate cassandraTemplate

    String getDbStatus() {
        try {
            entityManager.createNativeQuery('SELECT version()')
                    .getSingleResult()
            'OK'
        } catch (Exception e) {
            e.toString()
        }
    }

    String getDbSupportStatus() {
        try {
            //cassandraTemplate.execute('SELECT * FROM system_schema.keyspaces;')
            'OK'
        } catch (Exception e) {
            e.toString()
        }
    }
}
