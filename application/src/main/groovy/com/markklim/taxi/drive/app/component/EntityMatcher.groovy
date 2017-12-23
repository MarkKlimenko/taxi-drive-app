package com.markklim.taxi.drive.app.component

import com.markklim.taxi.drive.app.dao.entity.City
import com.markklim.taxi.drive.app.dao.entity.District
import com.markklim.taxi.drive.app.dao.impl.GeoDao
import com.markklim.taxi.drive.app.util.WordUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class EntityMatcher {
    @Autowired
    @Delegate
    WordUtil wordUtil

    @Autowired
    GeoDao geoDao

    String getDistrictId(String name) {
        List<District> districts = geoDao.getAllDistricts()
                .each { it.name = modifyGeoName(it.name) }

        getEntityId(name, districts)
    }

    String getCityId(String name) {
        List<City> cities = geoDao.getAllCities()
                .each { it.name = modifyGeoName(it.name) }

        getEntityId(name, cities)
    }

    protected String getEntityId(String name, List entities) {
        name = modifyGeoName(name)
        Object entity = entities.find { it.name == name }
        if (!entity) {
            throw new NullPointerException("No such entity ${name} in geo table")
        }
        entity.id
    }
}
