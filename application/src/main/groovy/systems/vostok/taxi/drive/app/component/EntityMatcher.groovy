package systems.vostok.taxi.drive.app.component

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Component
import systems.vostok.taxi.drive.app.service.GeoService
import systems.vostok.taxi.drive.app.util.WordUtil

@Component
class EntityMatcher {
    @Autowired
    GeoService geoService

    @Cacheable('districtIdByName')
    String getDistrictId(String name) {
        getEntityId(name, geoService.getDistrictsModifiedList())
    }

    @Cacheable('cityIdByName')
    String getCityId(String name) {
        getEntityId(name, geoService.getCitiesModifiedList())
    }

    protected String getEntityId(String name, List entities) {
        name = WordUtil.modifyGeoName(name)
        Object entity = entities.find { it.name == name }
        if (!entity) {
            throw new NullPointerException("No such entity ${name} in geo table")
        }
        entity.id
    }
}
