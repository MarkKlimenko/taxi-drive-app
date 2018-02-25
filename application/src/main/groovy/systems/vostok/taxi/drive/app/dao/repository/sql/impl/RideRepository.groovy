package systems.vostok.taxi.drive.app.dao.repository.sql.impl

import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import systems.vostok.taxi.drive.app.dao.entity.Ride
import systems.vostok.taxi.drive.app.dao.repository.sql.BasicRepository

import java.sql.Timestamp
import java.time.LocalDateTime

import static systems.vostok.taxi.drive.app.util.constant.SqlEntities.RIDE

@Repository
interface RideRepository extends BasicRepository<Ride, Long> {
    String entityType = RIDE

    //List<Ride> getPreviousRides(String clientLogin, int periodMonth, int ridesAmount)
    //List<Ride> getActiveRides()

    /*List<Ride> getPreviousRides(String clientLogin, int periodMonth, int ridesAmount) {
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

    List<Ride> getActiveRides() {
        Select select = QueryBuilder.select().from('ride')
        select.where(QueryBuilder.eq('state', 'active'))

        selectAll(select, Ride.class)
    }*/

    List<Ride> findByState(String state)

    @Query('SELECT r FROM Ride r WHERE r.client = :client AND r.dateIn > :targetDateFrom ORDER BY r.dateIn DESC')
    List<Ride> findPreviousRides(@Param('client') String client,
                                 @Param('targetDateFrom') LocalDateTime targetDateFrom,
                                 Pageable pageable)

}
