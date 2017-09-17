package com.markklim.taxi.drive.app.model

import groovy.transform.Canonical
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString
import org.springframework.data.cassandra.mapping.PrimaryKey
import org.springframework.data.cassandra.mapping.Table

import java.time.LocalDate

@Table('person')
@Canonical
@EqualsAndHashCode(includes = ['personLogin'])
@ToString(includeNames = true, includeFields = true)
class Person {

    @PrimaryKey
    String personLogin
    String firstName
    String lastName
    LocalDate dateOfBirth
    Integer ridesAmount
}
