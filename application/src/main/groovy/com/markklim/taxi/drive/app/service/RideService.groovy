package com.markklim.taxi.drive.app.service

import com.datastax.driver.core.querybuilder.QueryBuilder
import com.datastax.driver.core.querybuilder.Select
import com.markklim.taxi.drive.app.model.Ride
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.cassandra.core.CassandraTemplate
import org.springframework.stereotype.Service

@Service
class RideService {

    @Autowired
    private CassandraTemplate cassandraTemplate

    List<Ride> getAll() {
        Select select = QueryBuilder.select().from('ride')
        executeQuery(select)
    }

    void add(Ride ride) {
        cassandraTemplate.insert(ride)
    }

    protected List<Ride> executeQuery(Select select) {
        cassandraTemplate.select(select, Ride.class)
    }
}
