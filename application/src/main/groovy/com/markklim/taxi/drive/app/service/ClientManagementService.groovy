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
                clientInfo << [nextRideFree: settingDao.getValue('freeRideAmount')]
                clientInfo << [previousRides: rideDao.getPreviousRides(id, 5,3)]
                clientInfo
            } else { [:] }
        }
    }

    Integer calculatePrice(Ride ride) {
        // TODO: Используя номер клиента надо реализовать возможность скидок
        findDiscount(ride.clientLogin)
        if (ride.fromAddress.city == ride.toAddress.city) {
            priceFormer.formDtdPrice(ride.fromAddress, ride.toAddress)
        } else {
            priceFormer.formCtcPrice(ride.fromAddress.city, ride.toAddress.city)
        }
    }

    Map addNewRide(Ride ride) {
        ride.state = 'active'
        rideDao.add(ride)
        [ride: ride, status: 'OK']
    }

    List<Ride> getActiveRides() {
        rideDao.getActiveRides()
    }

    private findDiscount(String clientLogin) {
        clientDao.getByLogin(clientLogin)
    }
}
