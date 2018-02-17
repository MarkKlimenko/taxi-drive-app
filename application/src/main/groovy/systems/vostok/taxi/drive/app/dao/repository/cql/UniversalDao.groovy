package systems.vostok.taxi.drive.app.dao.repository.cql

import com.datastax.driver.core.querybuilder.Select
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.cassandra.core.CassandraTemplate
import org.springframework.stereotype.Component

@Component
class UniversalDao {
    @Autowired
    CassandraTemplate cassandraTemplate

    public <T> List<T> selectAll(Select select, Class<T> entityClass) {
        cassandraTemplate.select(select, entityClass)
    }

    public <T> T selectSingle(Select select, Class<T> entityClass) {
        cassandraTemplate.select(select, entityClass)
                .with { it ? it.first() : null }

    }

    public <T> List<T> insertAll(List<T> entityList) {
        cassandraTemplate.insert(entityList)
    }

    public <T> T insertSingle(T entity) {
        cassandraTemplate.insert(entity)
    }

    public <T> void deleteSingle(Class<T> entityClass, Object id) {
        cassandraTemplate.deleteById(entityClass, id)
    }
}
