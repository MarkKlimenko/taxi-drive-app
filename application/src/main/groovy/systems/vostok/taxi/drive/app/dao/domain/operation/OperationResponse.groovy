package systems.vostok.taxi.drive.app.dao.domain.operation

import groovy.transform.Canonical

@Canonical
class OperationResponse {
    UUID id
    OperationName operationName
    String state
    Object body
}
