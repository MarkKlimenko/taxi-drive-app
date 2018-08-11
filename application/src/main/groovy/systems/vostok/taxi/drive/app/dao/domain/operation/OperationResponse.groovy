package systems.vostok.taxi.drive.app.dao.domain.operation

import groovy.transform.Canonical

@Canonical
class OperationResponse {
    UUID id
    String operationName
    String state
    Object body
}
