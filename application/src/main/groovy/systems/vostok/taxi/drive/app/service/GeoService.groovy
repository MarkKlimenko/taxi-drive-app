package systems.vostok.taxi.drive.app.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service
import systems.vostok.taxi.drive.app.dao.entity.geo.City
import systems.vostok.taxi.drive.app.dao.entity.geo.District
import systems.vostok.taxi.drive.app.dao.repository.sql.impl.StreetDistrictMapperRepository
import systems.vostok.taxi.drive.app.dao.repository.sql.UniversalCrudRepository
import systems.vostok.taxi.drive.app.util.WordUtil

import static systems.vostok.taxi.drive.app.dao.entity.SystemProperty.Constants.PROPERTY_GEO_VERSION
import static systems.vostok.taxi.drive.app.util.constant.SqlEntities.*

@Service
class GeoService {
    @Autowired
    UniversalCrudRepository crudRepository

    @Autowired
    StreetDistrictMapperRepository streetDistrictMapperRepository

    Map getGeoInfo() {
        [geoVersion: crudRepository.findById(SYSTEM_PROPERTY, PROPERTY_GEO_VERSION).value,
         states    : getAllGeoEntities(STATE),
         cities    : getAllGeoEntities(CITY),
         streets   : getAllGeoEntities(STREET),
         districts : getAllGeoEntities(DISTRICT)]
    }

    def getAllGeoEntities(String entityType) {
        crudRepository.findAll(entityType)
    }

    def getGeoEntity(String entityType, String entityId) {
        crudRepository.findById(entityType, entityId)
    }

    void deleteGeoEntity(String entityType, String entityId) {
        crudRepository.deleteById(entityType, entityId)
        updateGeoCache()
    }

    def putGeoEntity(String entityType, Map entityMap) {
        def entity = crudRepository.put(entityType, entityMap)
        updateGeoCache()
        entity
    }

    @CacheEvict(['citiesModifiedList', 'cityIdByName', 'districtsModifiedList', 'districtIdByName'])
    def updateGeoCache() {
        crudRepository.put(SYSTEM_PROPERTY, [property: PROPERTY_GEO_VERSION,
                                             value   : UUID.randomUUID().toString()])
    }

    @Cacheable(value = 'citiesModifiedList')
    List<City> getCitiesModifiedList() {
        crudRepository.findAll(CITY)
                .each { it.name = WordUtil.modifyGeoName(it.name) }
    }

    @Cacheable(value = 'districtsModifiedList')
    List<District> getDistrictsModifiedList() {
        crudRepository.findAll(DISTRICT)
                .each { it.name = WordUtil.modifyGeoName(it.name) }
    }
}
