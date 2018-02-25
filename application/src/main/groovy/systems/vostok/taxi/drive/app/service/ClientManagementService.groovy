package systems.vostok.taxi.drive.app.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import systems.vostok.taxi.drive.app.component.PriceFormer
import systems.vostok.taxi.drive.app.dao.entity.Client
import systems.vostok.taxi.drive.app.dao.entity.Ride
import systems.vostok.taxi.drive.app.dao.repository.sql.impl.ClientRepository
import systems.vostok.taxi.drive.app.dao.repository.sql.impl.RideRepository
import systems.vostok.taxi.drive.app.dao.repository.sql.impl.geo.AddressRepository

import java.time.LocalDateTime

import static systems.vostok.taxi.drive.app.dao.entity.Ride.Constants.STATE_ACTIVE

@Service
class ClientManagementService {

    @Autowired
    ClientRepository clientRepository

    @Autowired
    RideRepository rideRepository

    @Autowired
    AddressRepository addressRepository

    @Autowired
    PriceFormer priceFormer

    Client checkClient(String id) {
        clientRepository.findOne(id)?.with {
            it.rideFree = priceFormer.isRideFree(it.ridesAmount)
            it.previousRides = findPreviousRides(id)
            it
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

    Ride addNewRide(Ride ride) {
        ride.state = STATE_ACTIVE

        // TODO: Calculate price

        rideRepository.save(ride)
    }

    List<Ride> getActiveRides() {
        rideRepository.findByState(STATE_ACTIVE)
    }

    private List<Ride> findPreviousRides(String clientId) {
        def getTargetDateFrom = {
            // TODO: Change to db value
            final Integer PERIOD_MONTHS = 5
            LocalDateTime.now().minusMonths(PERIOD_MONTHS)
        }

        def getTargetRidesAmount = {
            // TODO: Change to db value
            3
        }

        def constructPageable = {
            new PageRequest(0, getTargetRidesAmount())
        }

        def addRawAddresses = {
            it.rawFromAddress = addressRepository.findOne(it.fromAddress)
            it.rawToAddress = addressRepository.findOne(it.toAddress)
        }

        rideRepository.findPreviousRides(clientId, getTargetDateFrom(), constructPageable())
                .each(addRawAddresses)
    }
}
