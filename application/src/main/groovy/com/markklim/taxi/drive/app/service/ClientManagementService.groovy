package com.markklim.taxi.drive.app.service

import com.markklim.taxi.drive.app.component.PriceFormer
import com.markklim.taxi.drive.app.dao.entity.Client
import com.markklim.taxi.drive.app.dao.entity.Ride
import com.markklim.taxi.drive.app.dao.impl.ClientDao
import com.markklim.taxi.drive.app.dao.impl.RideDao
import com.markklim.taxi.drive.app.dao.impl.SettingDao
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ClientManagementService {

    private static final VIP_DISCOUNT = 0.2
    private static final FREE_DISCOUNT = 1

    @Autowired
    ClientDao clientDao

    @Autowired
    RideDao rideDao

    @Autowired
    SettingDao settingDao

    @Autowired
    PriceFormer priceFormer

    Map checkClient(String id) {
        clientDao.getByLogin(id).with {
            if(it) {
                Map clientInfo = it.asMap()
                clientInfo << [isRideFree: isRideFree(clientInfo.ridesAmount)]
                clientInfo << [previousRides: rideDao.getPreviousRides(id, 5,3)]
                clientInfo
            } else { [:] }
        }
    }

    Integer calculatePrice(Ride ride) {
        if (ride.fromAddress.city == ride.toAddress.city && ride.toAddress.city == 'Спасск-Дальний') {
            calculateDtdPrice(ride)
        } else {
            priceFormer.formCtcPrice(ride.fromAddress.city, ride.toAddress.city)
        }
    }

    Map addNewRide(Ride ride) {
        ride.state = 'active'

        // TODO: Calculate price

        rideDao.add(ride)
        [ride: ride, status: 'OK']
    }

    List<Ride> getActiveRides() {
        rideDao.getActiveRides()
    }

    private Integer calculateDtdPrice(Ride ride) {
        double discount = calculateDiscount(ride.clientLogin)

        if(discount == 1.0) {
            return 0
        }

        int basePrice = priceFormer.formDtdPrice(ride.fromAddress, ride.toAddress)
        basePrice - (basePrice * discount)
    }

    private double calculateDiscount(String clientLogin) {
        Client client = clientDao.getByLogin(clientLogin)
        double discount = 0
        if (client) {
            if (client.clientType == "VIP") {
                discount = VIP_DISCOUNT
            }
            if (isNextRideFree(client.ridesAmount)) {
                discount = FREE_DISCOUNT
            }
        }
        return discount
    }

    private isNextRideFree(int ridesAmount) {
        (ridesAmount + 1) % getFreeRideNumber() == 0
    }

    private getFreeRideNumber() {
        settingDao.getValue('ride_free').toInteger()
    }

    protected Boolean isRideFree (Integer ridesAmount) {
        Integer freeRideAmount = settingDao.getValue('ride_free') as Integer

        if((ridesAmount+1) % freeRideAmount == 0) { return true }
        false
    }
}
