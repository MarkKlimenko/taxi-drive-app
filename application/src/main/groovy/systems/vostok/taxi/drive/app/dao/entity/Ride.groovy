package systems.vostok.taxi.drive.app.dao.entity

import com.fasterxml.jackson.annotation.JsonInclude
import groovy.transform.Canonical
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString
import systems.vostok.taxi.drive.app.api.adapter.LocalDateTimeAdapter
import systems.vostok.taxi.drive.app.util.validator.OneOf

import javax.persistence.*
import javax.validation.constraints.Min
import javax.validation.constraints.NotNull
import javax.validation.constraints.Positive
import javax.validation.constraints.PositiveOrZero
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter
import java.time.LocalDateTime

import static systems.vostok.taxi.drive.app.dao.entity.Ride.Constants.STATE_ACTIVE
import static systems.vostok.taxi.drive.app.dao.entity.Ride.Constants.STATE_PENDING

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

    @NotNull
    String client

    @NotNull
    Integer fromAddress

    @NotNull
    Integer toAddress

    @NotNull
    @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
    LocalDateTime dateIn

    @NotNull
    @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
    LocalDateTime rideIn

    @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
    LocalDateTime rideOut

    Long carId

    @Min(value = 1L)
    Integer adultInCar

    @Positive
    Integer childrenInCar

    Boolean prepaid
    String comment

    @PositiveOrZero
    Integer price

    @OneOf([STATE_ACTIVE, STATE_PENDING])
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
