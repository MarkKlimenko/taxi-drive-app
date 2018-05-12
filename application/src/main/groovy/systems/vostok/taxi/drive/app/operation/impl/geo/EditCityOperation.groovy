package systems.vostok.taxi.drive.app.operation.impl.geo

import systems.vostok.taxi.drive.app.operation.Operation

import static systems.vostok.taxi.drive.app.util.constant.OperationName.EDIT_CITY_OPERATION

class EditCityOperation extends Operation {

    EditCityOperation() {
        operationName = EDIT_CITY_OPERATION
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
