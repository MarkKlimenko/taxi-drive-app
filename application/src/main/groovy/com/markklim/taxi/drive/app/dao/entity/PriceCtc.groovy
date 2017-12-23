package com.markklim.taxi.drive.app.dao.entity

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import groovy.transform.Canonical
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString
import org.springframework.data.cassandra.mapping.PrimaryKey
import org.springframework.data.cassandra.mapping.Table

import static com.markklim.taxi.drive.app.util.CommonUtil.*

@Table('price_ctc')
@Canonical
@EqualsAndHashCode(includes = ['id'])
@ToString(includeNames = true, includeFields = true)
class PriceCtc {
    @PrimaryKey
    Integer id
    String cityFrom
    String cityTo
    Integer price

    @JsonCreator
    PriceCtc(@JsonProperty("cityFrom") String cityFrom,
             @JsonProperty("cityTo") String cityTo,
             @JsonProperty("price") Integer price) {
        this.id = generateId(cityFrom, cityTo)
        this.cityFrom =  cityFrom
        this.cityTo = cityTo
        this.price = price
    }
}

