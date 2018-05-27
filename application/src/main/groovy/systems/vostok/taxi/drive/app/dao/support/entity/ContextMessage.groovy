package systems.vostok.taxi.drive.app.dao.support.entity

import com.fasterxml.jackson.databind.ObjectMapper
import groovy.transform.Canonical

import java.time.LocalDateTime

@Canonical
class ContextMessage {
    Long id
    String operationName
    String owner
    LocalDateTime dateIn
    String state
    String direction
    String requestBody
    String context

    void setContext(Object context) {
        new ObjectMapper().writeValueAsString(context)
    }
}
