package systems.vostok.taxi.drive.app.dao.domain.operation

import groovy.transform.Canonical

@Canonical
class OperationRequest {
    UUID id = UUID.randomUUID()
    OperationName operationName
    Boolean async = false
    Object body

    void setId(String id) {
        this.id = UUID.fromString(id)
    }
}
