package systems.vostok.taxi.drive.app.dao.entity

import com.fasterxml.jackson.databind.ObjectMapper
import groovy.transform.Canonical
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString
import org.springframework.data.cassandra.core.cql.PrimaryKeyType
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn
import org.springframework.data.cassandra.core.mapping.Table
import systems.vostok.taxi.drive.app.api.adapter.LocalDateTimeAdapter
import systems.vostok.taxi.drive.app.dao.domain.operation.OperationDirections
import systems.vostok.taxi.drive.app.dao.domain.operation.OperationStates

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter
import java.time.LocalDateTime

@Table('contextMessages')
@Canonical
@EqualsAndHashCode(includes = ['id'])
@ToString(includeNames = true, includeFields = true)
class ContextMessage {
    @PrimaryKeyColumn(name = 'id', ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    UUID id

    @PrimaryKeyColumn(name = 'operationName', ordinal = 1, type = PrimaryKeyType.CLUSTERED)
    String operationName

    @PrimaryKeyColumn(name = 'owner', ordinal = 2, type = PrimaryKeyType.CLUSTERED)
    String owner

    @PrimaryKeyColumn(name = 'dateIn', ordinal = 3, type = PrimaryKeyType.CLUSTERED)
    @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
    LocalDateTime dateIn

    String entityId = 'null'
    String state
    String direction
    String requestBody
    String context

    void setContext(Object context) {
        this.context = new ObjectMapper().writeValueAsString(context)
    }

    void setState(OperationStates operationState) {
        this.state = operationState.state
    }

    void setDirection(OperationDirections operationDirection) {
        this.direction = operationDirection.type
    }
}
