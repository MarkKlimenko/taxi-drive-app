package systems.vostok.taxi.drive.app.dao.entity

import org.springframework.data.redis.core.RedisHash
import systems.vostok.taxi.drive.app.api.adapter.LocalDateTimeAdapter
import systems.vostok.taxi.drive.app.util.validator.InRideState

import javax.persistence.Id
import javax.validation.constraints.Min
import javax.validation.constraints.NotNull
import javax.validation.constraints.Positive
import javax.validation.constraints.PositiveOrZero
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter
import java.time.LocalDateTime

@RedisHash('rides')
class ActiveRide implements Serializable {
    @Id
    Long id

    @NotNull
    String client

    @NotNull
    @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
    LocalDateTime dateIn

    @NotNull
    LocalDateTime rideIn

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

    @InRideState
    String state

    Address rawFromAddress

    Address rawToAddress

    ActiveRide() {

    }

    ActiveRide(Ride ride) {
        this.id = ride.id
        this.client = ride.client
        this.dateIn = ride.dateIn
        this.rideIn = ride.rideIn
        this.rideOut = ride.rideOut
        this.carId = ride.carId
        this.adultInCar = ride.adultInCar
        this.childrenInCar = ride.childrenInCar
        this.prepaid = ride.prepaid
        this.comment = ride.comment
        this.price = ride.price
        this.state = ride.state
        this.rawFromAddress = ride.rawFromAddress
        this.rawToAddress = ride.rawToAddress
    }
}
