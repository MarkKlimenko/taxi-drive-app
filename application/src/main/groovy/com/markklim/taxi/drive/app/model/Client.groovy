package com.markklim.taxi.drive.app.model

import groovy.transform.Canonical
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString
import org.springframework.data.cassandra.mapping.PrimaryKey
import org.springframework.data.cassandra.mapping.Table

@Table('client')
@Canonical
@EqualsAndHashCode(includes = ['clientLogin'])
@ToString(includeNames = true, includeFields = true)
class Client {

    @PrimaryKey
    String clientLogin

    String firstName

    String lastName

    Integer ridesAmount

    String clientType

    Map asMap() {
        this.class.declaredFields.findAll { !it.synthetic }
                .collectEntries { [(it.name): this."$it.name"] }
    }
}
