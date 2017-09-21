package com.markklim.taxi.drive.app.model

import groovy.transform.Canonical
import org.springframework.data.cassandra.mapping.UserDefinedType

@Canonical
@UserDefinedType("address")
class Address {
    String country
    String state
    String city
    String street
    String building
}
