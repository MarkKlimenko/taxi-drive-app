package systems.vostok.taxi.drive.app.operation.impl.geo

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import systems.vostok.taxi.drive.app.dao.domain.OperationRequest
import systems.vostok.taxi.drive.app.dao.entity.geo.City
import systems.vostok.taxi.drive.app.dao.repository.impl.geo.CityRepository
import systems.vostok.taxi.drive.app.operation.Operation

import static systems.vostok.taxi.drive.app.util.constant.OperationName.ADD_CITY_OPERATION

/*
enroll
 {
    "operationName": "ADD_CITY",
    "direction": "enroll",
    "body": {
        "id": "spdTest",
        "name": "Спасск-Дальний-Тест",
        "state": "pk"
    }
 }

rollback


*/

@Component
class AddCityOperation implements Operation {
    String operationName = ADD_CITY_OPERATION

    @Autowired
    CityRepository cityRepository

    @Override
    Object enroll(OperationRequest request) {
        City city = request.body as City

        City checkedCity = cityRepository.findOne(city.id)
        assert !checkedCity : 'City with target ID already exists'

        city.with(cityRepository.&save)
    }

    @Override
    Object rollback(OperationRequest request) {
        return null
    }
}
