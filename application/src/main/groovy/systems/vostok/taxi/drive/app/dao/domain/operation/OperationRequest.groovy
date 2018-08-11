package systems.vostok.taxi.drive.app.dao.domain.operation

import groovy.transform.Canonical

@Canonical
class OperationRequest {
    UUID id = UUID.randomUUID()
    String operationName
    Boolean async = false
    Object body

    void setId(String id) {
        this.id = UUID.fromString(id)
    }
}
