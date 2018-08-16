package systems.vostok.taxi.drive.app.operation.geo

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import systems.vostok.taxi.drive.app.dao.entity.geo.*
import systems.vostok.taxi.drive.app.dao.repository.impl.geo.*
import systems.vostok.taxi.drive.app.operation.CoreOperation
import systems.vostok.taxi.drive.app.operation.EntityEditOperation

import static systems.vostok.taxi.drive.app.dao.domain.operation.CoreOperationNames.*

@Configuration
class EditGeoOperationConfig {
    @Bean
    CoreOperation editCountry(CountryRepository countryRepository) {
        new EntityEditOperation<Country, String>(
                operationName: EDIT_COUNTRY_OPERATION.name,
                entityRepository: countryRepository
        )
    }

    @Bean
    CoreOperation editState(StateRepository stateRepository) {
        new EntityEditOperation<State, String>(
                operationName: EDIT_STATE_OPERATION.name,
                entityRepository: stateRepository
        )
    }

    @Bean
    CoreOperation editCity(CityRepository cityRepository) {
        new EntityEditOperation<City, String>(
                operationName: EDIT_CITY_OPERATION.name,
                entityRepository: cityRepository
        )
    }

    @Bean
    CoreOperation editDistrict(DistrictRepository districtRepository) {
        new EntityEditOperation<District, String>(
                operationName: EDIT_DISTRICT_OPERATION.name,
                entityRepository: districtRepository
        )
    }

    @Bean
    CoreOperation editStreet(StreetRepository streetRepository) {
        new EntityEditOperation<Street, String>(
                operationName: EDIT_STREET_OPERATION.name,
                entityRepository: streetRepository
        )
    }
}
