package com.markklim.taxi.drive.app.component.database

import com.datastax.driver.core.querybuilder.Select
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.cassandra.core.CassandraTemplate
import org.springframework.stereotype.Component

@Component
class QueryHelper {
    @Autowired
    private CassandraTemplate cassandraTemplate

    public <T> List<T> getAll(Select select, Class<T> entityClass) {
        cassandraTemplate.select(select, entityClass)
    }

    public <T> T getFirst(Select select, Class<T> entityClass) {
        cassandraTemplate.select(select, entityClass).first()
    }
}
