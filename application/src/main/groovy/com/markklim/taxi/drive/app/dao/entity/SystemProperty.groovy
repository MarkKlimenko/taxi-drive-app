package com.markklim.taxi.drive.app.dao.entity

import groovy.transform.Canonical
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString
import org.springframework.data.cassandra.mapping.PrimaryKey
import org.springframework.data.cassandra.mapping.Table

@Table('system_property')
@Canonical
@EqualsAndHashCode(includes = ['property'])
@ToString(includeNames = true, includeFields = true)
class SystemProperty {

    @PrimaryKey
    String property
    String value
}
