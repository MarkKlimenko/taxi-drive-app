package systems.vostok.taxi.drive.app.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import systems.vostok.taxi.drive.app.component.PriceFormer
import systems.vostok.taxi.drive.app.dao.entity.Client
import systems.vostok.taxi.drive.app.dao.entity.Ride
import systems.vostok.taxi.drive.app.dao.repository.sql.impl.ClientRepository
import systems.vostok.taxi.drive.app.dao.repository.sql.impl.RideRepository

import java.sql.Timestamp
import java.time.LocalDateTime

import static systems.vostok.taxi.drive.app.dao.entity.Ride.Constants.STATE_ACTIVE


@Service
class ClientManagementService {

    @Autowired
    ClientRepository clientRepository

    @Autowired
    RideRepository rideRepository

    @Autowired
    PriceFormer priceFormer

    Client checkClient(String id) {
        clientRepository.getOne(id).with {
            if(it) {
                it.rideFree = priceFormer.isRideFree(it.ridesAmount)
                it.previousRides = rideRepository.getPreviousRides(id, getTargetDateFrom(),getTargetRidesAmount())
                it
            }
            null
        }
    }

    Integer calculatePrice(Ride ride) {
        // TODO: Get default city from settings
        if (ride.fromAddress.city == ride.toAddress.city && ride.toAddress.city == 'Спасск-Дальний') {
            priceFormer.calculateDtdPrice(ride)
        } else {
            priceFormer.calculateCtcPrice(ride)
        }
    }

    Map addNewRide(Ride ride) {
        ride.state = STATE_ACTIVE

        // TODO: Calculate price

        rideRepository.save(ride)
        [ride: ride, status: 'OK']
    }

    List<Ride> getActiveRides() {
        rideRepository.findByState(STATE_ACTIVE)
    }

    private Timestamp getTargetDateFrom() {
        // TODO: Change to db value
        final Integer PERIOD_MONTHS = 5
        Timestamp.valueOf(LocalDateTime.now().minusMonths(PERIOD_MONTHS))
    }

    private Integer getTargetRidesAmount() {
        // TODO: Change to db value
        3
    }
}
