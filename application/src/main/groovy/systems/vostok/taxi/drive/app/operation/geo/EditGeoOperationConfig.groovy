package systems.vostok.taxi.drive.app.operation.geo

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import systems.vostok.taxi.drive.app.dao.entity.geo.*
import systems.vostok.taxi.drive.app.dao.repository.impl.geo.*
import systems.vostok.taxi.drive.app.operation.EntityEditOperation
import systems.vostok.taxi.drive.app.operation.Operation

import static systems.vostok.taxi.drive.app.util.constant.OperationName.*

@Configuration
class EditGeoOperationConfig {
    @Bean
    Operation editCountry(CountryRepository countryRepository) {
        new EntityEditOperation<Country, String>(
                operationName: EDIT_COUNTRY_OPERATION,
                entityRepository: countryRepository
        )
    }

    @Bean
    Operation editState(StateRepository stateRepository) {
        new EntityEditOperation<State, String>(
                operationName: EDIT_STATE_OPERATION,
                entityRepository: stateRepository
        )
    }

    @Bean
    Operation editCity(CityRepository cityRepository) {
        new EntityEditOperation<City, String>(
                operationName: EDIT_CITY_OPERATION,
                entityRepository: cityRepository
        )
    }

    @Bean
    Operation editDistrict(DistrictRepository districtRepository) {
        new EntityEditOperation<District, String>(
                operationName: EDIT_DISTRICT_OPERATION,
                entityRepository: districtRepository
        )
    }

    @Bean
    Operation editStreet(StreetRepository streetRepository) {
        new EntityEditOperation<Street, String>(
                operationName: EDIT_STREET_OPERATION,
                entityRepository: streetRepository
        )
    }
}
