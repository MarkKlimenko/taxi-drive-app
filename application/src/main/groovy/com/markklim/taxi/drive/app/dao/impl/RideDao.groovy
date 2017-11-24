package com.markklim.taxi.drive.app.dao.impl

import com.datastax.driver.core.querybuilder.QueryBuilder
import com.datastax.driver.core.querybuilder.Select
import com.markklim.taxi.drive.app.dao.UniversalDao
import com.markklim.taxi.drive.app.dao.entity.Ride
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

import java.sql.Timestamp
import java.time.LocalDateTime

@Component
class RideDao {

    @Autowired
    @Delegate
    UniversalDao universalDao

    List<Ride> getPreviousRides(String clientLogin, int periodMonth, int ridesAmount) {
        Timestamp dateGraterThan =  Timestamp.valueOf(LocalDateTime.now().minusMonths(periodMonth))

        Select select = QueryBuilder.select().from('ride')
        select.where(QueryBuilder.eq('clientLogin', clientLogin))
                .and(QueryBuilder.gt('dateIn', dateGraterThan))
        select.allowFiltering()

        selectAll(select, Ride.class)
                .sort { it.dateIn }
                .reverse()
                .take(ridesAmount)
    }

    void add(Ride ride) {
        insertSingle(ride)
    }

    List<Ride> getActiveRides() {
        Select select = QueryBuilder.select().from('ride')
        select.where(QueryBuilder.eq('state', 'active'))

        selectAll(select, Ride.class)
    }
}
