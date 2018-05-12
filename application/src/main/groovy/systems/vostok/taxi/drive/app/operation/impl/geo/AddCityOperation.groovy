package systems.vostok.taxi.drive.app.operation.impl.geo

import systems.vostok.taxi.drive.app.operation.Operation

import static systems.vostok.taxi.drive.app.util.constant.OperationName.ADD_CITY_OPERATION

class AddCityOperation extends Operation {

    AddCityOperation() {
        operationName = ADD_CITY_OPERATION
    }

    @Override
    Object execute() {
        return null
    }

    @Override
    Object rollback() {
        return null
    }
}
