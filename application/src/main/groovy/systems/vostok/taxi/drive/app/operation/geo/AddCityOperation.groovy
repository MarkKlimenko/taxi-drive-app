package systems.vostok.taxi.drive.app.operation.geo

import groovy.json.JsonSlurper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import systems.vostok.taxi.drive.app.dao.domain.OperationRequest
import systems.vostok.taxi.drive.app.dao.entity.ContextMessage
import systems.vostok.taxi.drive.app.dao.entity.geo.City
import systems.vostok.taxi.drive.app.dao.repository.impl.ContextMessageRepository
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
 {
    "id": "51ae64c4-3327-4b73-9498-1fa3347d2a15",
    "operationName": "ADD_CITY",
    "direction": "rollback"
 }
*/

@Component
class AddCityOperation implements Operation {
    String operationName = ADD_CITY_OPERATION

    @Autowired
    CityRepository cityRepository

    @Autowired
    ContextMessageRepository contextMessageRepository

    @Override
    Object enroll(OperationRequest request) {
        City city = request.body as City

        City checkedCity = cityRepository.findOne(city.id)
        assert !checkedCity : 'City with target ID already exists'

        city.with(cityRepository.&save)
    }

    @Override
    Object rollback(OperationRequest request) {
        ContextMessage contextMessage = contextMessageRepository.findById(request.id)
        City contextBody = new JsonSlurper().parseText(contextMessage.context) as City

        City checkedCity = cityRepository.findOne(contextBody.id)

        assert contextBody == checkedCity : 'Rollback rejected: city was modified'

        cityRepository.delete(contextBody.id)

        //TODO: Add canceled for rollbacked operation

        checkedCity
    }
}
