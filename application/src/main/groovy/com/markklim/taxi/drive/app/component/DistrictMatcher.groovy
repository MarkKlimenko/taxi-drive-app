package com.markklim.taxi.drive.app.component

import com.markklim.taxi.drive.app.dao.domain.Address
import com.markklim.taxi.drive.app.dao.entity.District
import com.markklim.taxi.drive.app.dao.impl.GeoDao
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class DistrictMatcher {

    @Autowired
    @Delegate
    GeoDao geoDao

    String defineDistrict(Address address) {
        def street = getStreetIdByName(address.street)
        throwEnterDistrictIfNull(street)
        List<District> districts = getDistrictsByStreet(street.id)
        throwEnterDistrictIfNull(districts)
        if (districts.size() == 1) {
            districts[0].shortName
        } else if (districts.size() > 1) {
            def highLimit = findBuildingRange(districts, address.building.toInteger())
            districts.find {it.building.toInteger() == highLimit}.shortName
        }
    }

    private throwEnterDistrictIfNull(Object obj) {
        if (!obj) {
            throw new IllegalArgumentException('enter_district_implicitly')
        }
    }

    private findBuildingRange(List<District> districts, Integer searchBuilding) {
        def highLimit = 0
        districts.each {
            def currentBuilding = it.building.toInteger()
            if (searchBuilding == currentBuilding) {
                highLimit = currentBuilding
                return
            }
            if (highLimit == 0) {
                if (searchBuilding < currentBuilding) {
                    highLimit = currentBuilding
                }
            } else {
                if (searchBuilding < currentBuilding && currentBuilding < highLimit) {
                    highLimit = currentBuilding
                }
            }
        }
        highLimit
    }
}
