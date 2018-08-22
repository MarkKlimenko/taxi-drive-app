package systems.vostok.taxi.drive.app.dao.domain.operation

import groovy.transform.Canonical

/**
 * Used only in operation controller
 */

@Canonical
@Deprecated
class OperationRequest {
    UUID id = UUID.randomUUID()
    String operationName
    String owner
    OperationDirections direction
    Boolean async = false
    Object body

    void setId(String id) {
        this.id = UUID.fromString(id)
    }

    void setDirection(String direction) {
        this.direction = OperationDirections.get(direction)
    }

    void setDirection(OperationDirections direction) {
        this.direction = direction
    }
}
