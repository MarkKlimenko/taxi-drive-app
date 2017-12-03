package com.markklim.taxi.drive.app.component

import com.markklim.taxi.drive.app.dao.impl.PriceDao
import com.markklim.taxi.drive.app.dao.domain.Address
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
        def districtFrom = getDistrict(from)
        def districtTo = getDistrict(to)
        formDtdPrice(districtFrom, districtTo)
    }

    Integer formDtdPrice(String districtFrom, String districtTo) {
        getDistrictsRidePrice(districtFrom, districtTo)
    }

    Integer formCtcPrice(String from, String to) {
        getCitiesRidePrice(from, to)
    }

    private getDistrict(Address address) {
        address.district ? address.district : defineDistrict(address)
    }
}
