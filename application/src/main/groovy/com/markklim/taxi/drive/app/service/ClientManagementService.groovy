package com.markklim.taxi.drive.app.service

import com.markklim.taxi.drive.app.component.PriceFormer
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
        // TODO: Используя номер клиента надо реализовать возможность скидок
        if (ride.fromAddress.city == ride.toAddress.city) {
            priceFormer.formDtdPrice(ride.fromAddress, ride.toAddress)
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

    protected Boolean isRideFree (Integer ridesAmount) {
        Integer freeRideAmount = settingDao.getValue('freeRideAmount') as Integer

        if((ridesAmount+1) % freeRideAmount == 0) { return true }
        false
    }
}
