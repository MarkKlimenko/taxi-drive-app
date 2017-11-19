package com.markklim.taxi.drive.app.service

import com.markklim.taxi.drive.app.component.PriceFormer
import com.markklim.taxi.drive.app.dao.impl.ClientDao
import com.markklim.taxi.drive.app.dao.impl.RideDao
import com.markklim.taxi.drive.app.dao.impl.SettingDao
import com.markklim.taxi.drive.app.model.Address
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
                clientInfo << [nextRideFree: settingDao.getValueById('freeRideAmount')]
                clientInfo << [previousRides: rideDao.getPreviousRides(id, 5,3)]
                clientInfo
            } else { [:] }
        }
    }

    Integer calculatePrice(Address addressFrom, Address addressTo, String clientLogin) {
        // TODO: Используя номер клиента надо реализовать возможность скидок
        if (addressFrom.city == addressTo.city) {
            priceFormer.formDtdPrice(addressFrom, addressTo)
        } else {
            priceFormer.formCtcPrice(addressFrom.city, addressTo.city)
        }
    }
}
