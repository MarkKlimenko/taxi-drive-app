package systems.vostok.taxi.drive.app.service

import systems.vostok.taxi.drive.app.dao.entity.geo.City
import systems.vostok.taxi.drive.app.dao.entity.geo.District
import systems.vostok.taxi.drive.app.dao.entity.geo.Street
import systems.vostok.taxi.drive.app.dao.entity.SystemProperty
import systems.vostok.taxi.drive.app.dao.impl.GeoDao
import systems.vostok.taxi.drive.app.dao.impl.SystemPropertyDao
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service
import systems.vostok.taxi.drive.app.util.WordUtil

@Service
class GeoService {
    @Autowired
    GeoDao geoDao

    @Autowired
    WordUtil wordUtil

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

    Map deleteStreet(Street street) {
        geoDao.deleteStreet(street)
        updateGeoVersion()
        [state: 'success']
    }

    Map addStreet(Street street) {
        geoDao.addStreet(street)
        updateGeoVersion()
        [state: 'success']
    }

    @CacheEvict(['citiesModifiedList', 'cityIdByName', 'districtsModifiedList', 'districtIdByName'])
    void updateGeoVersion() {
        systemPropertyDao.add([property: 'geoVersion',
                               value   : UUID.randomUUID().toString()] as SystemProperty)
    }

    @Cacheable(value='citiesModifiedList')
    List<City> getCitiesModifiedList() {
        geoDao.getAllCities()
                .each { it.name = wordUtil.modifyGeoName(it.name) }
    }

    @Cacheable(value='districtsModifiedList')
    List<District> getDistrictsModifiedList() {
        geoDao.getAllDistricts()
                .each { it.name = wordUtil.modifyGeoName(it.name) }
    }
}
