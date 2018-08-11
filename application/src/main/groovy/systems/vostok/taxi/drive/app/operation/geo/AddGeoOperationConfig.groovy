package systems.vostok.taxi.drive.app.operation.geo

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import systems.vostok.taxi.drive.app.dao.entity.geo.*
import systems.vostok.taxi.drive.app.dao.repository.impl.geo.*
import systems.vostok.taxi.drive.app.operation.EntityAddOperation
import systems.vostok.taxi.drive.app.operation.Operation

import static systems.vostok.taxi.drive.app.dao.domain.operation.OperationName.*

@Configuration
class AddGeoOperationConfig {
    @Bean
    Operation addCountry(CountryRepository countryRepository) {
        new EntityAddOperation<Country, String>(
                operationName: ADD_COUNTRY_OPERATION,
                entityRepository: countryRepository
        )
    }

    @Bean
    Operation addState(StateRepository stateRepository) {
        new EntityAddOperation<State, String>(
                operationName: ADD_STATE_OPERATION,
                entityRepository: stateRepository
        )
    }

    @Bean
    Operation addCity(CityRepository cityRepository) {
        new EntityAddOperation<City, String>(
                operationName: ADD_CITY_OPERATION,
                entityRepository: cityRepository
        )
    }

    @Bean
    Operation addDistrict(DistrictRepository districtRepository) {
        new EntityAddOperation<District, String>(
                operationName: ADD_DISTRICT_OPERATION,
                entityRepository: districtRepository
        )
    }

    @Bean
    Operation addStreet(StreetRepository streetRepository) {
        new EntityAddOperation<Street, String>(
                operationName: ADD_STREET_OPERATION,
                entityRepository: streetRepository
        )
    }
}
