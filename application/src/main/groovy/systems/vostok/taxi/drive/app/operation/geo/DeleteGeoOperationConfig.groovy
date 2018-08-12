package systems.vostok.taxi.drive.app.operation.geo

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import systems.vostok.taxi.drive.app.dao.entity.geo.*
import systems.vostok.taxi.drive.app.dao.repository.impl.geo.*
import systems.vostok.taxi.drive.app.operation.CoreOperation
import systems.vostok.taxi.drive.app.operation.EntityDeleteOperation

import static systems.vostok.taxi.drive.app.dao.domain.operation.CoreOperationNames.*

@Configuration
class DeleteGeoOperationConfig {
    @Bean
    CoreOperation deleteCountry(CountryRepository countryRepository) {
        new EntityDeleteOperation<Country, String>(
                operationName: DELETE_COUNTRY_OPERATION,
                entityRepository: countryRepository
        )
    }

    @Bean
    CoreOperation deleteState(StateRepository stateRepository) {
        new EntityDeleteOperation<State, String>(
                operationName: DELETE_STATE_OPERATION,
                entityRepository: stateRepository
        )
    }

    @Bean
    CoreOperation deleteCity(CityRepository cityRepository) {
        new EntityDeleteOperation<City, String>(
                operationName: DELETE_CITY_OPERATION,
                entityRepository: cityRepository
        )
    }

    @Bean
    CoreOperation deleteDistrict(DistrictRepository districtRepository) {
        new EntityDeleteOperation<District, String>(
                operationName: DELETE_DISTRICT_OPERATION,
                entityRepository: districtRepository
        )
    }

    @Bean
    CoreOperation deleteStreet(StreetRepository streetRepository) {
        new EntityDeleteOperation<Street, String>(
                operationName: DELETE_STREET_OPERATION,
                entityRepository: streetRepository
        )
    }
}
