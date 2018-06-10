package systems.vostok.taxi.drive.app.operation.geo

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import systems.vostok.taxi.drive.app.dao.entity.geo.*
import systems.vostok.taxi.drive.app.dao.repository.impl.geo.*

import static systems.vostok.taxi.drive.app.util.constant.OperationName.*

@Configuration
class AddGeoOperationConfig {
    @Bean
    AddGeoOperation addCountry(CountryRepository countryRepository) {
        new AddGeoOperation<Country>(
                operationName: ADD_COUNTRY_OPERATION,
                entityRepository: countryRepository
        )
    }

    @Bean
    AddGeoOperation addState(StateRepository stateRepository) {
        new AddGeoOperation<State>(
                operationName: ADD_STATE_OPERATION,
                entityRepository: stateRepository
        )
    }

    @Bean
    AddGeoOperation addCity(CityRepository cityRepository) {
        new AddGeoOperation<City>(
                operationName: ADD_CITY_OPERATION,
                entityRepository: cityRepository
        )
    }

    @Bean
    AddGeoOperation addDistrict(DistrictRepository districtRepository) {
        new AddGeoOperation<District>(
                operationName: ADD_DISTRICT_OPERATION,
                entityRepository: districtRepository
        )
    }

    @Bean
    AddGeoOperation addStreet(StreetRepository streetRepository) {
        new AddGeoOperation<Street>(
                operationName: ADD_STREET_OPERATION,
                entityRepository: streetRepository
        )
    }
}
