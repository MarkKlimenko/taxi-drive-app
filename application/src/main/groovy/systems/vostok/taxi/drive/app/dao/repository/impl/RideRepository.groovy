package systems.vostok.taxi.drive.app.dao.repository.impl

import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import systems.vostok.taxi.drive.app.dao.entity.Ride
import systems.vostok.taxi.drive.app.dao.repository.BasicRepository

import java.time.LocalDateTime

import static systems.vostok.taxi.drive.app.util.constant.SqlEntity.RIDE

@Repository
interface RideRepository extends BasicRepository<Ride, Long> {
    String entityType = RIDE

    List<Ride> findByState(String state)

    @Query('SELECT r FROM Ride r WHERE r.client = :client AND r.dateIn > :targetDateFrom ORDER BY r.dateIn DESC')
    List<Ride> findPreviousRides(@Param('client') String client,
                                 @Param('targetDateFrom') LocalDateTime targetDateFrom,
                                 Pageable pageable)

    @Query('SELECT r FROM Ride r WHERE r.state = :state AND r.rideIn < :dateNow')
    List<Ride> getForActive(@Param('state') String state,
                                 @Param('dateNow') LocalDateTime targetDateFrom)
}
