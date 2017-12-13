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

    @Autowired
    ClientDao clientDao

    @Autowired
    RideDao rideDao

    @Autowired
    PriceFormer priceFormer

    Map checkClient(String id) {
        clientDao.getByLogin(id).with {
            if(it) {
                Map clientInfo = it.asMap()
                clientInfo << [isRideFree: priceFormer.isRideFree(clientInfo.ridesAmount)]
                clientInfo << [previousRides: rideDao.getPreviousRides(id, 5,3)]
                clientInfo
            } else { [:] }
        }
    }

    Integer calculatePrice(Ride ride) {
        if (ride.fromAddress.city == ride.toAddress.city && ride.toAddress.city == 'Спасск-Дальний') {
            priceFormer.calculateDtdPrice(ride)
        } else {
            priceFormer.calculateCtcPrice(ride)
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
}
