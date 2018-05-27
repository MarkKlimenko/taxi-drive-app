package systems.vostok.taxi.drive.app.dao.entity

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import groovy.transform.Canonical
import org.hibernate.search.annotations.Field
import org.hibernate.search.annotations.Indexed
import systems.vostok.taxi.drive.app.dao.ObjectCreator
import systems.vostok.taxi.drive.app.util.CommonUtil

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = 'addresses')
@Indexed
@Canonical
class Address implements ObjectCreator {
    @Id
    Integer id

    String country

    @Field
    String state

    @Field
    String city

    @Field
    String street

    @Field
    String building

    @Field
    String district

    @JsonCreator
    Address() {}

    @JsonCreator
    Address(@JsonProperty('country') String country,
            @JsonProperty('state') String state,
            @JsonProperty('city') String city,
            @JsonProperty('street') String street,
            @JsonProperty('building') String building,
            @JsonProperty('district') String district) {

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
