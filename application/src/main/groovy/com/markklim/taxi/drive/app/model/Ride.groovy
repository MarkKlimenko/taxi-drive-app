package com.markklim.taxi.drive.app.model

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString
import org.springframework.data.cassandra.mapping.PrimaryKey
import org.springframework.data.cassandra.mapping.Table

@Table('ride')
@EqualsAndHashCode(includes = ['login'])
@ToString(includeNames = true, includeFields = true, excludes = 'id')
class Ride {

    @PrimaryKey
    UUID  id
    Address fromAddress
    Address toAddress

}
