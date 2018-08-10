package systems.vostok.taxi.drive.app.operation.geo

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import systems.vostok.taxi.drive.app.dao.entity.geo.*
import systems.vostok.taxi.drive.app.dao.repository.impl.geo.*

import static systems.vostok.taxi.drive.app.util.constant.OperationName.*

@Configuration
class EditGeoOperationConfig {
    @Bean
    EditGeoOperation editCountry(CountryRepository countryRepository) {
        new EditGeoOperation<Country>(
                operationName: EDIT_COUNTRY_OPERATION,
                entityRepository: countryRepository
        )
    }

    @Bean
    EditGeoOperation editState(StateRepository stateRepository) {
        new EditGeoOperation<State>(
                operationName: EDIT_STATE_OPERATION,
                entityRepository: stateRepository
        )
    }

    @Bean
    EditGeoOperation editCity(CityRepository cityRepository) {
        new EditGeoOperation<City>(
                operationName: EDIT_CITY_OPERATION,
                entityRepository: cityRepository
        )
    }

    @Bean
    EditGeoOperation editDistrict(DistrictRepository districtRepository) {
        new EditGeoOperation<District>(
                operationName: EDIT_DISTRICT_OPERATION,
                entityRepository: districtRepository
        )
    }

    @Bean
    EditGeoOperation editStreet(StreetRepository streetRepository) {
        new EditGeoOperation<Street>(
                operationName: EDIT_STREET_OPERATION,
                entityRepository: streetRepository
        )
    }
}
