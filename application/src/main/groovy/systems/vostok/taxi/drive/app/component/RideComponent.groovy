package systems.vostok.taxi.drive.app.component

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import systems.vostok.taxi.drive.app.dao.entity.ActiveRide
import systems.vostok.taxi.drive.app.dao.entity.Ride
import systems.vostok.taxi.drive.app.dao.repository.impl.ActiveRideRepository

@Component
class RideComponent {
    @Autowired
    ActiveRideRepository activeRideRepository

    Optional<ActiveRide> addRideToActiveList(Ride ride) {
        if (ride.dateIn == ride.rideIn) {
            Optional.of (activeRideRepository.save(new ActiveRide(ride)))
        }
        Optional.empty()
    }
}
