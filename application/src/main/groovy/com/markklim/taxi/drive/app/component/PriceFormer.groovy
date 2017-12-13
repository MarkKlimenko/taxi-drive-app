package com.markklim.taxi.drive.app.component

import com.markklim.taxi.drive.app.dao.entity.Client
import com.markklim.taxi.drive.app.dao.entity.Ride
import com.markklim.taxi.drive.app.dao.impl.ClientDao
import com.markklim.taxi.drive.app.dao.impl.PriceDao
import com.markklim.taxi.drive.app.dao.impl.SettingDao
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class PriceFormer {
    @Autowired
    PriceDao priceDao

    @Autowired
    DistrictMatcher districtMatcher

    @Autowired
    SettingDao settingDao

    @Autowired
    ClientDao clientDao

    private static final VIP_DISCOUNT = 0.2
    private static final FREE_DISCOUNT = 1

    Integer calculateDtdPrice(Ride ride) {
        Double discount = calculateDiscount(ride.clientLogin)

        if (discount == FREE_DISCOUNT) {
            0
        } else {
            priceDao.getDistrictsRidePrice(districtMatcher.getDistrictId(ride.fromAddress), districtMatcher.getDistrictId(ride.toAddress)) * (1 - discount)
        }
    }

    Integer calculateCtcPrice(Ride ride) {
        priceDao.getCitiesRidePrice(ride.fromAddress.city, ride.toAddress.city)
    }

    Boolean isRideFree(Integer ridesAmount) {
        settingDao.getValue('ride_free')
            .collect { (ridesAmount + 1) % (it as Integer) == 0 }
            .first()
    }

    private Double calculateDiscount(String clientLogin) {
        Client client = clientDao.getByLogin(clientLogin)
        if (client) {
            if (isRideFree(client.ridesAmount)) {
                return FREE_DISCOUNT
            } else if (client.clientType == 'VIP') {
                return VIP_DISCOUNT
            }
        }
        0
    }
}
