package systems.vostok.taxi.drive.app.dao.entity

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import groovy.transform.Canonical
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

import static systems.vostok.taxi.drive.app.util.CommonUtil.*

@Entity
@Table(name = 'prices_dtd')
@Canonical
@EqualsAndHashCode(includes = ['id'])
@ToString(includeNames = true, includeFields = true)
class PriceDtd {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id
    
    String distFrom
    String distTo
    Integer price

    @JsonCreator
    PriceDtd(@JsonProperty('distFrom') String distFrom,
             @JsonProperty('distTo') String distTo,
             @JsonProperty('price') Integer price) {
        this.id = generateId(distFrom, distTo)
        this.distFrom =  distFrom
        this.distTo = distTo
        this.price = price
    }
}
