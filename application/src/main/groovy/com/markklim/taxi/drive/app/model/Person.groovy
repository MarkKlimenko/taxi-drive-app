package com.markklim.taxi.drive.app.model

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString
import org.springframework.data.cassandra.mapping.PrimaryKey
import org.springframework.data.cassandra.mapping.Table

@Table('person')
@EqualsAndHashCode(includes = ['login'])
@ToString(includeNames = true, includeFields = true, excludes = 'id')
class Person {

    @PrimaryKey
    UUID  id
    String login
    String firstName
    String lastName

    Person(){

    }

    Person(String login, String firstName, String lastName) {
        this.id = UUID.randomUUID()
        this.login = login
        this.firstName = firstName
        this.lastName = lastName
    }
}
