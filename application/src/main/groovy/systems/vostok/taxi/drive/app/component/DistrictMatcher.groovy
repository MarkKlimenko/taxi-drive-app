package systems.vostok.taxi.drive.app.component

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import systems.vostok.taxi.drive.app.dao.entity.Address
import systems.vostok.taxi.drive.app.dao.entity.Setting
import systems.vostok.taxi.drive.app.dao.entity.geo.Street
import systems.vostok.taxi.drive.app.dao.repository.impl.SettingRepository
import systems.vostok.taxi.drive.app.dao.repository.impl.StreetDistrictMapperRepository
import systems.vostok.taxi.drive.app.dao.repository.impl.geo.StreetRepository
import systems.vostok.taxi.drive.app.util.JavaConverters
import systems.vostok.tda.domain.Mapper
import systems.vostok.tda.service.DistrictMapperService

import static systems.vostok.taxi.drive.app.dao.entity.Setting.Constants.SETTING_DEFAULT_CITY_ID

@Component
class DistrictMatcher {
    @Autowired
    EntityMatcher entityMatcher

    @Autowired
    StreetRepository streetRepository

    @Autowired
    StreetDistrictMapperRepository sdMapperRepository

    @Autowired
    DistrictMapperService districtMapperService

    @Autowired
    SettingRepository settingRepository

    String getDistrictId(Address address) {
        address.district ? entityMatcher.getDistrictId(address.district) : defineDistrict(address)
    }

    String defineDistrict(Address address) {
        String defaultCityId = settingRepository.get(SETTING_DEFAULT_CITY_ID)

        Street street = streetRepository.findByNameAndCity(address.street, defaultCityId)
                .orElseThrow({ new IllegalArgumentException("No such street in DB { ${address.street} }") })

        scala.collection.immutable.List<Mapper> mapper = sdMapperRepository.findByStreetId(street.id)
                .collect { new Mapper(it.streetId, it.building, it.districtId) }
                .with(JavaConverters.&convertList)

        districtMapperService.getDistrict(mapper, new systems.vostok.tda.domain.Address(street.id, address.building))
    }
}
