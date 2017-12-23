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
    EntityMatcher entityMatcher

    @Autowired
    SettingDao settingDao

    @Autowired
    ClientDao clientDao

    static final Double VIP_DISCOUNT = 0.2
    static final Double FREE_DISCOUNT = 1
    static final Double ZERO_DISCOUNT = 0

    Integer calculateDtdPrice(Ride ride) {
        Double discount = calculateDiscount(ride.clientLogin)

        if (discount == FREE_DISCOUNT) {
            0
        } else {
            String districtFrom = districtMatcher.getDistrictId(ride.fromAddress)
            String districtTo = districtMatcher.getDistrictId(ride.toAddress)
            priceDao.getDistrictsRidePrice(districtFrom, districtTo) * (1 - discount)
        }
    }

    Integer calculateCtcPrice(Ride ride) {
        priceDao.getCitiesRidePrice(entityMatcher.getCityId(ride.fromAddress.city),
                entityMatcher.getCityId(ride.toAddress.city))
    }

    Boolean isRideFree(Integer ridesAmount) {
        (settingDao.getValue('ride_free') as Integer)
            .collect { (ridesAmount + 1) % it == 0 }
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
        ZERO_DISCOUNT
    }
}
