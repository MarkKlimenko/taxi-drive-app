package systems.vostok.taxi.drive.app.dao.entity

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import groovy.transform.Canonical
import systems.vostok.taxi.drive.app.util.CommonUtil

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = 'addresses')
@Canonical
class Address {
    @Id
    Integer id
    String country
    String state
    String city
    String street
    String building
    String district

    @JsonCreator
    Address() {}

    @JsonCreator
    Address(@JsonProperty String country,
            @JsonProperty String state,
            @JsonProperty String city,
            @JsonProperty String street,
            @JsonProperty String building,
            @JsonProperty String district) {

        this.id = [country, state, city, street, building, district]
                .with(CommonUtil.&generateId)

        this.country = country
        this.state = state
        this.city = city
        this.street = street
        this.building = building
        this.district = district
    }

    @JsonCreator
    Address(@JsonProperty Map<String, String> address) {
        this(address.country,
                address.state,
                address.city,
                address.street,
                address.building,
                address.district)
    }
}
