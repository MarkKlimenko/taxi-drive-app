package com.markklim.taxi.drive.app.component

import com.markklim.taxi.drive.app.dao.domain.Address
import com.markklim.taxi.drive.app.dao.entity.StreetDistrictMapper
import com.markklim.taxi.drive.app.dao.impl.GeoDao
import com.markklim.taxi.drive.app.dao.impl.StreetDistrictDao
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import systems.vostok.tda.service.DistrictMapperService

@Component
class DistrictMatcher {

    @Autowired
    StreetDistrictDao streetDistrictDao

    @Autowired
    GeoDao geoDao

    @Autowired
    DistrictMapperService districtMapperService

    String getDistrictId(Address address) {
        address.district ?: defineDistrict(address)
    }

    String defineDistrict(Address address) {
        List<StreetDistrictMapper> mapper = geoDao.getStreetByNameAndCity(address.street, 'spd').id
                .with { streetDistrictDao.getByStreetId(it) }

        districtMapperService.getDistrict(mapper, address as Map)
    }
}
