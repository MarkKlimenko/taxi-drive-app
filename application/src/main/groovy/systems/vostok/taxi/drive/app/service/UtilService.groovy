package systems.vostok.taxi.drive.app.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.data.cassandra.core.CassandraTemplate
import org.springframework.stereotype.Service

@Service
class UtilService {
    @Value('${project.version}') String version

    @Autowired
    private CassandraTemplate cassandraTemplate

    String getVersion() {
        version
    }

    String getDbStatus() {
        try {
            cassandraTemplate.execute('SELECT * FROM system_schema.keyspaces;')
            'OK'
        } catch (Exception e) {
            'ERROR'
        }
    }
}
