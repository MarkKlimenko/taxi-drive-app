package systems.vostok.taxi.drive.app.dao.domain

import groovy.transform.Canonical

@Canonical
class OperationRequest {
    String id
    String operationName
    String direction
    Object body
}
