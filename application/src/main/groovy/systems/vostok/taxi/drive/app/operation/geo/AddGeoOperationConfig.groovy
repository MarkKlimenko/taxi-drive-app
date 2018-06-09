package systems.vostok.taxi.drive.app.operation.geo

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import systems.vostok.taxi.drive.app.dao.entity.geo.City
import systems.vostok.taxi.drive.app.dao.repository.impl.geo.CityRepository

import static systems.vostok.taxi.drive.app.util.constant.OperationName.ADD_CITY_OPERATION

@Configuration
class AddGeoOperationConfig {

    @Bean
    AddGeoOperation addCity(CityRepository cityRepository) {
        new AddGeoOperation<City>().with {
            it.operationName = ADD_CITY_OPERATION
            it.entityRepository = cityRepository
            it
        }
    }

}
