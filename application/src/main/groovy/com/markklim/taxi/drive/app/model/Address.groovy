package com.markklim.taxi.drive.app.model

import groovy.transform.Canonical

@Canonical
class Address implements Serializable {
    String country
    String state
    String city
    String street
    String building
}
