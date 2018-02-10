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
@Table(name = 'prices_ctc')
@Canonical
@EqualsAndHashCode(includes = ['id'])
@ToString(includeNames = true, includeFields = true)
class PriceCtc {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id

    String cityFrom
    String cityTo
    Integer price

    @JsonCreator
    PriceCtc(@JsonProperty('cityFrom') String cityFrom,
             @JsonProperty('cityTo') String cityTo,
             @JsonProperty('price') Integer price) {
        this.id = generateId(cityFrom, cityTo)
        this.cityFrom =  cityFrom
        this.cityTo = cityTo
        this.price = price
    }
}

