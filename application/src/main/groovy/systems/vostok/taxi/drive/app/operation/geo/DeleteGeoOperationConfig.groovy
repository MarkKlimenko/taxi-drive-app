package systems.vostok.taxi.drive.app.operation.geo

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import systems.vostok.taxi.drive.app.dao.entity.geo.*
import systems.vostok.taxi.drive.app.dao.repository.impl.geo.*
import systems.vostok.taxi.drive.app.operation.EntityDeleteOperation
import systems.vostok.taxi.drive.app.operation.Operation

import static systems.vostok.taxi.drive.app.dao.domain.operation.OperationName.*

@Configuration
class DeleteGeoOperationConfig {
    @Bean
    Operation deleteCountry(CountryRepository countryRepository) {
        new EntityDeleteOperation<Country, String>(
                operationName: DELETE_COUNTRY_OPERATION,
                entityRepository: countryRepository
        )
    }

    @Bean
    Operation deleteState(StateRepository stateRepository) {
        new EntityDeleteOperation<State, String>(
                operationName: DELETE_STATE_OPERATION,
                entityRepository: stateRepository
        )
    }

    @Bean
    Operation deleteCity(CityRepository cityRepository) {
        new EntityDeleteOperation<City, String>(
                operationName: DELETE_CITY_OPERATION,
                entityRepository: cityRepository
        )
    }

    @Bean
    Operation deleteDistrict(DistrictRepository districtRepository) {
        new EntityDeleteOperation<District, String>(
                operationName: DELETE_DISTRICT_OPERATION,
                entityRepository: districtRepository
        )
    }

    @Bean
    Operation deleteStreet(StreetRepository streetRepository) {
        new EntityDeleteOperation<Street, String>(
                operationName: DELETE_STREET_OPERATION,
                entityRepository: streetRepository
        )
    }
}
