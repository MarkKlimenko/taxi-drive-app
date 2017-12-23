package com.markklim.taxi.drive.app.component

import com.markklim.taxi.drive.app.service.GeoService
import com.markklim.taxi.drive.app.util.WordUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Component

@Component
class EntityMatcher {
    @Autowired
    WordUtil wordUtil

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
        name = wordUtil.modifyGeoName(name)
        Object entity = entities.find { it.name == name }
        if (!entity) {
            throw new NullPointerException("No such entity ${name} in geo table")
        }
        entity.id
    }
}
