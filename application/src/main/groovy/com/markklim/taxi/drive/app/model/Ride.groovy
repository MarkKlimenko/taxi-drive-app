package com.markklim.taxi.drive.app.model

import groovy.transform.Canonical
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString
import org.springframework.data.cassandra.mapping.PrimaryKey
import org.springframework.data.cassandra.mapping.Table

@Table('ride')
@Canonical
@EqualsAndHashCode(includes = ['id'])
@ToString(includeNames = true, includeFields = true, excludes = 'id')
class Ride {

    @PrimaryKey
    UUID  id = UUID.randomUUID()
    String personLogin
    Address fromAddress
    Address toAddress
    Date time
    Integer rideDuration
    Integer carId
    Integer menInCar
}
