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
    String getDistrictId(String districtName) {
        // TODO: Use advanced Lucen search OR Use correct city ID
        // Get rid of getEntityId()- get directly from table, using search
        getEntityId(districtName, geoService.getDistrictsModifiedList())
    }

    @Cacheable('cityIdByName')
    String getCityId(String cityName) {
        // Get rid of getEntityId()- get directly from table, using search
        getEntityId(cityName, geoService.getCitiesModifiedList())
    }


    // Get rid of this
    protected String getEntityId(String name, List entities) {
        name = WordUtil.modifyGeoName(name)

        Object entity = entities.find { it.name == name }
        if (!entity) {
            throw new NullPointerException("No such entity $name in geo table")
        }
        entity.id
    }
}
