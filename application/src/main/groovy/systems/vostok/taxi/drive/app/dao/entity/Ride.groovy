package systems.vostok.taxi.drive.app.dao.entity

import com.fasterxml.jackson.annotation.JsonInclude
import systems.vostok.taxi.drive.app.api.adapter.LocalDateTimeAdapter
import groovy.transform.Canonical
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.SequenceGenerator
import javax.persistence.Table
import javax.persistence.Transient
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter
import java.time.LocalDateTime

@Entity
@Table(name = 'rides')
@Canonical
@EqualsAndHashCode(includes = ['id'])
@ToString(includeNames = true, includeFields = true, excludes = 'id')
class Ride {
    @Id
    @GeneratedValue(generator = 'ID_GENERATOR')
    @SequenceGenerator(name = 'ID_GENERATOR', sequenceName = 'seq_global')
    Long id

    String client
    Integer fromAddress
    Integer toAddress

    @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
    LocalDateTime dateIn

    @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
    LocalDateTime rideIn

    @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
    LocalDateTime rideOut

    Long carId
    Integer adultInCar
    Integer childrenInCar
    String prepaid
    String comment
    Integer price
    String state

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Transient
    Address rawFromAddress

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Transient
    Address rawToAddress

    static interface Constants {
        String STATE_ACTIVE = 'active'
        String STATE_PENDING = 'pending'
    }
}
