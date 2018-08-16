package systems.vostok.taxi.drive.app.operation.geo

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import systems.vostok.taxi.drive.app.dao.entity.geo.*
import systems.vostok.taxi.drive.app.dao.repository.impl.geo.*
import systems.vostok.taxi.drive.app.operation.CoreOperation
import systems.vostok.taxi.drive.app.operation.EntityAddOperation

import static systems.vostok.taxi.drive.app.dao.domain.operation.CoreOperationNames.*

@Configuration
class AddGeoOperationConfig {
    @Bean
    CoreOperation addCountry(CountryRepository countryRepository) {
        new EntityAddOperation<Country, String>(
                operationName: ADD_COUNTRY_OPERATION.name,
                entityRepository: countryRepository
        )
    }

    @Bean
    CoreOperation addState(StateRepository stateRepository) {
        new EntityAddOperation<State, String>(
                operationName: ADD_STATE_OPERATION.name,
                entityRepository: stateRepository
        )
    }

    @Bean
    CoreOperation addCity(CityRepository cityRepository) {
        new EntityAddOperation<City, String>(
                operationName: ADD_CITY_OPERATION.name,
                entityRepository: cityRepository
        )
    }

    @Bean
    CoreOperation addDistrict(DistrictRepository districtRepository) {
        new EntityAddOperation<District, String>(
                operationName: ADD_DISTRICT_OPERATION.name,
                entityRepository: districtRepository
        )
    }

    @Bean
    CoreOperation addStreet(StreetRepository streetRepository) {
        new EntityAddOperation<Street, String>(
                operationName: ADD_STREET_OPERATION.name,
                entityRepository: streetRepository
        )
    }
}
