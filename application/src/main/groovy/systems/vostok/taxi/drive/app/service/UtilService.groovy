package systems.vostok.taxi.drive.app.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.data.cassandra.core.CassandraTemplate
import org.springframework.stereotype.Service

import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@Service
class UtilService {
    @Value('${project.version}')
    String version

    @Autowired
    private CassandraTemplate cassandraTemplate

    @PersistenceContext
    EntityManager entityManager

    String getCqlStatus() {
        try {
            cassandraTemplate.execute('SELECT * FROM system_schema.keyspaces;')
            'OK'
        } catch (Exception e) {
            e.toString()
        }
    }

    String getSqlStatus() {
        try {
            entityManager.createNativeQuery("SELECT 'OK' FROM flyway_schema_history;")
                    .getSingleResult() as String
        } catch (Exception e) {
            e.toString()
        }
    }
}
