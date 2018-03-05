package systems.vostok.taxi.drive.app.component

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import systems.vostok.taxi.drive.app.dao.entity.Address
import systems.vostok.taxi.drive.app.dao.entity.geo.Street
import systems.vostok.taxi.drive.app.dao.repository.impl.StreetDistrictMapperRepository
import systems.vostok.taxi.drive.app.dao.repository.impl.geo.StreetRepository
import systems.vostok.taxi.drive.app.util.JavaConverters
import systems.vostok.tda.domain.Mapper
import systems.vostok.tda.service.DistrictMapperService

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

    static final String DEFAULT_CITY = 'spa'

    String getDistrictId(Address address) {
        address.district ? entityMatcher.getDistrictId(address.district) : defineDistrict(address)
    }

    String defineDistrict(Address address) {
        Street street = streetRepository.findByNameAndCity(address.street, DEFAULT_CITY)

        if (!street) {
            throw new IllegalArgumentException('no_such_street_in_db')
        }

        scala.collection.immutable.List<Mapper> mapper = sdMapperRepository.findByStreetId(street.id)
                .collect { new Mapper(it.streetId, it.building,  it.districtId) }
                .with(JavaConverters.&convertList)

        districtMapperService.getDistrict(mapper, new systems.vostok.tda.domain.Address(street.id, address.building))
    }
}
