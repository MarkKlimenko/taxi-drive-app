package systems.vostok.taxi.drive.app.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import systems.vostok.taxi.drive.app.component.PriceFormer
import systems.vostok.taxi.drive.app.dao.domain.RidePrice
import systems.vostok.taxi.drive.app.dao.entity.Client
import systems.vostok.taxi.drive.app.dao.entity.Ride
import systems.vostok.taxi.drive.app.dao.repository.impl.ClientRepository
import systems.vostok.taxi.drive.app.dao.repository.impl.RideRepository
import systems.vostok.taxi.drive.app.dao.repository.impl.SettingRepository
import systems.vostok.taxi.drive.app.dao.repository.impl.geo.AddressRepository

import java.time.LocalDateTime

import static systems.vostok.taxi.drive.app.dao.entity.Ride.Constants.STATE_ACTIVE
import static systems.vostok.taxi.drive.app.dao.entity.Setting.Constants.SETTING_DEFAULT_CITY

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

    @Autowired
    SettingRepository settingRepository

    Client getClientInfo(String id) {
        clientRepository.findById(id).orElse(null)?.with {
            rideFree = priceFormer.isRideFree(it.ridesAmount)
            previousRides = findPreviousRides(id)
            it
        }
    }

    RidePrice evaluateRide(Ride ride) {
        if (ride.rawFromAddress.city == ride.rawToAddress.city &&
                ride.rawToAddress.city == settingRepository.get(SETTING_DEFAULT_CITY)) {
            [priceFormer.calculateDtdPrice(ride)]
        } else {
            [priceFormer.calculateCtcPrice(ride)]
        }
    }

    Ride addNewRide(Ride ride) {
        ride.state = STATE_ACTIVE

        // TODO: Calculate price

        //TODO: Create operation for it

        rideRepository.save(ride)
    }

    List<Ride> getActiveRides() {
        //TODO: Get active rides from Redis
        rideRepository.findByState(STATE_ACTIVE)
    }

    protected List<Ride> findPreviousRides(String clientId) {
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
