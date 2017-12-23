package com.markklim.taxi.drive.app.component

import com.markklim.taxi.drive.app.dao.domain.Address
import com.markklim.taxi.drive.app.dao.entity.Street
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
    EntityMatcher entityMatcher

    @Autowired
    GeoDao geoDao

    @Autowired
    DistrictMapperService districtMapperService

    static final String DEFAULT_CITY = 'spa'

    String getDistrictId(Address address) {
        address.district ? entityMatcher.getDistrictId(address.district) : defineDistrict(address)
    }

    String defineDistrict(Address address) {
        Street street =  geoDao.getStreetByNameAndCity(address.street, DEFAULT_CITY)

        if(!street) {
            throw new IllegalArgumentException('no_such_street_in_db')
        }

        List<Map> mapper = streetDistrictDao.getByStreetId(street.id)
                .collect {  [streetId: it.streetId, building: it.building, districtId: it.districtId] }

        districtMapperService.getDistrict(mapper, [streetId: street.id, building: address.building])
    }
}
