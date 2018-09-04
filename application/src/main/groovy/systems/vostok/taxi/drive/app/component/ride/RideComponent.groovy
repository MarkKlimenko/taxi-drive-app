package systems.vostok.taxi.drive.app.component.ride

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import systems.vostok.taxi.drive.app.dao.domain.RideState
import systems.vostok.taxi.drive.app.dao.entity.ActiveRide
import systems.vostok.taxi.drive.app.dao.entity.Ride
import systems.vostok.taxi.drive.app.dao.repository.impl.ActiveRideRepository

@Component
class RideComponent {
    @Autowired
    ActiveRideRepository activeRideRepository

    Optional<ActiveRide> addNewRideToActiveList(Ride ride) {
        if (ride.dateIn == ride.rideIn) {
            ride.state = RideState.STATE_PENDING.state
            Optional.of(activeRideRepository.save(new ActiveRide(ride)))
        } else {
            Optional.empty()
        }
    }
}
