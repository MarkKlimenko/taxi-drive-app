package systems.vostok.taxi.drive.app.operation.geo

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import systems.vostok.taxi.drive.app.dao.entity.geo.*
import systems.vostok.taxi.drive.app.dao.repository.impl.geo.*

import static systems.vostok.taxi.drive.app.util.constant.OperationName.*

@Configuration
class DeleteGeoOperationConfig {
    @Bean
    DeleteGeoOperation addCountry(CountryRepository countryRepository) {
        new DeleteGeoOperation<Country>(
                operationName: DELETE_COUNTRY_OPERATION,
                entityRepository: countryRepository
        )
    }

    @Bean
    DeleteGeoOperation addState(StateRepository stateRepository) {
        new DeleteGeoOperation<State>(
                operationName: DELETE_STATE_OPERATION,
                entityRepository: stateRepository
        )
    }

    @Bean
    DeleteGeoOperation addCity(CityRepository cityRepository) {
        new DeleteGeoOperation<City>(
                operationName: DELETE_CITY_OPERATION,
                entityRepository: cityRepository
        )
    }

    @Bean
    DeleteGeoOperation addDistrict(DistrictRepository districtRepository) {
        new DeleteGeoOperation<District>(
                operationName: DELETE_DISTRICT_OPERATION,
                entityRepository: districtRepository
        )
    }

    @Bean
    DeleteGeoOperation addStreet(StreetRepository streetRepository) {
        new DeleteGeoOperation<Street>(
                operationName: DELETE_STREET_OPERATION,
                entityRepository: streetRepository
        )
    }
}
