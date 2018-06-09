package systems.vostok.taxi.drive.app.dao.domain

import groovy.transform.Canonical

@Canonical
class OperationRequest {
    UUID id
    String operationName
    String direction
    Object body

    void setId(String id) {
        this.id = UUID.fromString(id)
    }
}
