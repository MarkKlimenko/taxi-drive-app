package com.markklim.taxi.drive.app.service

import com.markklim.taxi.drive.app.dao.entity.City
import com.markklim.taxi.drive.app.dao.entity.Street
import com.markklim.taxi.drive.app.dao.entity.SystemProperty
import com.markklim.taxi.drive.app.dao.impl.GeoDao
import com.markklim.taxi.drive.app.dao.impl.SystemPropertyDao
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class GeoService {
    @Autowired
    GeoDao geoDao

    @Autowired
    SystemPropertyDao systemPropertyDao

    Map getGeoInfo() {
        [geoVersion : systemPropertyDao.getValue('geoVersion').value,
         states : geoDao.getAllStates(),
         cities : geoDao.getAllCities(),
         streets: geoDao.getAllStreets(),
         districts: geoDao.getAllDistricts()]
    }

    Map addState() {
        [:]
    }

    Map addCity(City city) {
        geoDao.addCity(city)
        updateGeoVersion()
        [state: 'success']
    }

    Map deleteCity(City city) {
        geoDao.deleteCity(city)
        updateGeoVersion()
        [state: 'success']
    }

    Map addStreet(Street street) {
        geoDao.addStreet(street)
        updateGeoVersion()
        [state: 'success']
    }

    void updateGeoVersion() {
        systemPropertyDao.add([property: 'geoVersion',
                               value   : UUID.randomUUID().toString()] as SystemProperty)
    }
}
