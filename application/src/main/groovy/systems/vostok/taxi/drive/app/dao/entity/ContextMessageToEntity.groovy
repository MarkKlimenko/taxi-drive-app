package systems.vostok.taxi.drive.app.dao.entity

import com.fasterxml.jackson.databind.ObjectMapper
import groovy.transform.Canonical
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString
import org.springframework.data.cassandra.core.cql.PrimaryKeyType
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn
import org.springframework.data.cassandra.core.mapping.Table
import systems.vostok.taxi.drive.app.api.adapter.LocalDateTimeAdapter

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter
import java.time.LocalDateTime

@Table('contextMessages')
@Canonical
@EqualsAndHashCode(includes = ['id'])
@ToString(includeNames = true, includeFields = true)
class ContextMessageToEntity {
    @PrimaryKeyColumn(name = 'id', ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    UUID id = UUID.randomUUID()

    @PrimaryKeyColumn(name = 'entityId', ordinal = 1, type = PrimaryKeyType.CLUSTERED)
    String entityId

    @PrimaryKeyColumn(name = 'contextMessageId', ordinal = 2, type = PrimaryKeyType.CLUSTERED)
    String contextMessageId

    String state
    String direction
    String requestBody
    String context

    void setContext(Object context) {
        this.context = new ObjectMapper().writeValueAsString(context)
    }
}
