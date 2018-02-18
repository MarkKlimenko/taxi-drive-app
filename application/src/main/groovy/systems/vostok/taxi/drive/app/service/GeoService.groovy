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

import static systems.vostok.taxi.drive.app.util.constant.Properties.GEO_VERSION
import static systems.vostok.taxi.drive.app.util.constant.SqlEntities.*

@Service
class GeoService {
    @Autowired
    WordUtil wordUtil

    @Autowired
    UniversalCrudRepository crudRepository

    @Autowired
    StreetDistrictMapperRepository streetDistrictMapperRepository

    Map getGeoInfo() {
        [geoVersion: crudRepository.getById(SYSTEM_PROPERTY, GEO_VERSION).value,
         states    : getAllGeoEntities(STATE),
         cities    : getAllGeoEntities(CITY),
         streets   : getAllGeoEntities(STREET),
         districts : getAllGeoEntities(DISTRICT)]
    }

    def getAllGeoEntities(String entityType) {
        crudRepository.getAll(entityType)
    }

    def deleteGeoEntity(String entityType, String entityId) {
        crudRepository.deleteById(entityType, entityId)
        updateGeoCache()
        entityId
    }

    def putGeoEntity(String entityType, Map entityMap) {
        def entity = crudRepository.put(entityType, entityMap)
        updateGeoCache();
        entity
    }

    @CacheEvict(['citiesModifiedList', 'cityIdByName', 'districtsModifiedList', 'districtIdByName'])
    def updateGeoCache() {
        crudRepository.put(SYSTEM_PROPERTY, [property: GEO_VERSION,
                                             value   : UUID.randomUUID().toString()])
    }

    @Cacheable(value = 'citiesModifiedList')
    List<City> getCitiesModifiedList() {
        crudRepository.getAll(CITY)
                .each { it.name = wordUtil.modifyGeoName(it.name) }
    }

    @Cacheable(value = 'districtsModifiedList')
    List<District> getDistrictsModifiedList() {
        crudRepository.getAll(DISTRICT)
                .each { it.name = wordUtil.modifyGeoName(it.name) }
    }
}
