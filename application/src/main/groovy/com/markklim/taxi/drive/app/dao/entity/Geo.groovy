package com.markklim.taxi.drive.app.dao.entity

import groovy.transform.Canonical
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString
import org.springframework.data.cassandra.mapping.PrimaryKey
import org.springframework.data.cassandra.mapping.Table

@Table('country')
@Canonical
@EqualsAndHashCode(includes = ['id'])
@ToString(includeNames = true, includeFields = true)
class Country {
    @PrimaryKey
    String id
    String name
}

@Table('state')
@Canonical
@EqualsAndHashCode(includes = ['id'])
@ToString(includeNames = true, includeFields = true)
class State {
    @PrimaryKey
    String id
    String name
    String country
}

@Table('city')
@Canonical
@EqualsAndHashCode(includes = ['id'])
@ToString(includeNames = true, includeFields = true)
class City {
    @PrimaryKey
    String id
    String name
    String state
}

@Table('street')
@Canonical
@EqualsAndHashCode(includes = ['id'])
@ToString(includeNames = true, includeFields = true)
class Street {
    @PrimaryKey
    String id
    String name
    String city
}
