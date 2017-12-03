package com.markklim.taxi.drive.app.service

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
         streets: geoDao.getAllStreets()]
    }

    Map addState() {
        [:]
    }

    Map addCity() {
        [:]
    }

    Map addStreet(Street street) {
        geoDao.addStreet(street)
        refreshGeoVersion()
        [state: 'success']
    }

    void refreshGeoVersion() {
        systemPropertyDao.add([property: 'geoVersion',
                               value   : UUID.randomUUID().toString()] as SystemProperty)
    }
}
