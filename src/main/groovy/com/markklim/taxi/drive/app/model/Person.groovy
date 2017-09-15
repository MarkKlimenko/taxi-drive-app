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
    private final UUID  id
    private final String login
    private final String name

    Person(String login, String name) {
        this.id = UUID.randomUUID()
        this.login = login
        this.name = name
    }
}
