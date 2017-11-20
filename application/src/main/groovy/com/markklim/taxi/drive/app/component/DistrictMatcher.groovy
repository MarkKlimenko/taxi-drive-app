package com.markklim.taxi.drive.app.component

import com.markklim.taxi.drive.app.dao.domain.Address
import org.springframework.stereotype.Component

@Component
class DistrictMatcher {

    String defineDistrict(Address address) {
        // TODO: Продумать логику получения района исходя из улицы
        if (address.street == "Vilkova") {
            "Churkin"
        } else if (address.street == "Borisenko") {
            "Tikhaya"
        } else {
            "District"
        }
    }
}
