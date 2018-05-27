package systems.vostok.taxi.drive.app.dao.entity

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import groovy.transform.Canonical
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString
import systems.vostok.taxi.drive.app.dao.ObjectCreator

import javax.persistence.*

import static systems.vostok.taxi.drive.app.util.CommonUtil.generateId

@Entity
@Table(name = 'prices_dtd')
@Canonical
@EqualsAndHashCode(includes = ['id'])
@ToString(includeNames = true, includeFields = true)
class PriceDtd implements ObjectCreator {
    @Id
    Integer id

    String distFrom
    String distTo
    Integer price

    PriceDtd() {}

    @JsonCreator
    PriceDtd(@JsonProperty('distFrom') String distFrom,
             @JsonProperty('distTo') String distTo,
             @JsonProperty('price') Integer price) {
        this.id = generateId(distFrom, distTo)
        this.distFrom = distFrom
        this.distTo = distTo
        this.price = price
    }
}
