package com.markklim.taxi.drive.app.component

import com.markklim.taxi.drive.app.dao.impl.PriceDao
import com.markklim.taxi.drive.app.model.Address
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class PriceFormer {
    @Autowired
    @Delegate
    PriceDao priceDao

    @Autowired
    @Delegate
    DistrictMatcher districtMatcher

    Integer formDtdPrice(Address from, Address to) {
        getDistrictsRidePrice(defineDistrict(from), defineDistrict(to))
    }

    Integer formDtdPrice(String districtFrom, String districtTo) {
        getDistrictsRidePrice(districtFrom, districtTo)
    }

    Integer formCtcPrice(String from, String to) {
        getCitiesRidePrice(from, to)
    }
}
