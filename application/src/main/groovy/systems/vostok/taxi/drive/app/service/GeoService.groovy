package systems.vostok.taxi.drive.app.service

import systems.vostok.taxi.drive.app.dao.entity.geo.City
import systems.vostok.taxi.drive.app.dao.entity.geo.District
import systems.vostok.taxi.drive.app.dao.entity.SystemProperty
import systems.vostok.taxi.drive.app.dao.repository.BasicRepository
import systems.vostok.taxi.drive.app.dao.repository.impl.GeoDao
import systems.vostok.taxi.drive.app.dao.repository.impl.SystemPropertyDao
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service
import systems.vostok.taxi.drive.app.dao.repository.impl.geo.CountryRepository
import systems.vostok.taxi.drive.app.util.WordUtil

import static systems.vostok.taxi.drive.app.util.constant.SqlEntities.*

@Service
class GeoService {
    @Autowired
    GeoDao geoDao

    @Autowired
    WordUtil wordUtil

    @Autowired
    SystemPropertyDao systemPropertyDao

    @Autowired
    CountryRepository countryRepository

    @Autowired
    CrudService crudService

    Map getGeoInfo() {
        [geoVersion : crudService.getById('systemProperty', 'geoVersion').value,
         states : getAllGeoEntities(STATE),
         cities : getAllGeoEntities(CITY),
         streets: getAllGeoEntities(STR),
         districts: getAllGeoEntities('district')]
    }

    def getAllGeoEntities(String entityType) {
        crudService.getAll(entityType)
    }

    def deleteGeoEntity(String entityType, String entityId) {
        crudService.deleteById(entityType, entityId)
       /* updateGeoVersion()*/
        entityId
    }

    def putGeoEntity(String entityType, Map entityMap) {
        def entity = crudService.put(entityType, entityMap)
        /*updateGeoVersion();*/
        entity
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
