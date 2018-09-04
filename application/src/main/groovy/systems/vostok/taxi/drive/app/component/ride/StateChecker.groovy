package systems.vostok.taxi.drive.app.component.ride

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import systems.vostok.taxi.drive.app.dao.domain.RideState
import systems.vostok.taxi.drive.app.dao.entity.Ride
import systems.vostok.taxi.drive.app.dao.repository.impl.RideRepository
import systems.vostok.taxi.drive.app.util.actor.ActorCreator

import java.time.LocalDateTime

@Component
class StateChecker extends ActorCreator {
    @Autowired
    RideRepository rideRepository

    @Override
    void act(Object param) {
        List<Ride> rides = rideRepository.getForActive(RideState.STATE_PENDING.state, LocalDateTime.now())
                .each { it.state = RideState.STATE_SEARCH_DRIVER.state }

        if (rides) {
            rideRepository.saveAll(rides)
        }
    }
}
