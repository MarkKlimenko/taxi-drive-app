package com.markklim.taxi.drive.app.model

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import groovy.transform.Canonical
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString
import org.springframework.data.cassandra.mapping.PrimaryKey
import org.springframework.data.cassandra.mapping.Table

@Table('price_dtd')
@Canonical
@EqualsAndHashCode(includes = ['id'])
@ToString(includeNames = true, includeFields = true)
class PriceDtd {
    @PrimaryKey
    Integer id
    String distFrom
    String distTo
    Integer price

    @JsonCreator
    PriceDtd(@JsonProperty("distFrom") String distFrom,
             @JsonProperty("distTo") String distTo,
             @JsonProperty("price") Integer price) {
        this.distFrom =  distFrom
        this.distTo = distTo
        this.price = price
        this.id = generateId()
    }

    PriceDtd(){

    }

    void generateId(){
        if(distFrom == null || distTo == null)
            throw new IllegalStateException("distFrom and distTo fields" +
                    " must be initialize")
        id = distFrom.hashCode() + distTo.hashCode()
    }

    @Override
    String toString() {
        return "\nFrom: " + distFrom + "\n" +
                "To: " + distTo + "\n" +
                "Price: " + price + "\n"
    }
}
