package com.markklim.taxi.drive.app.dao.entity

import groovy.transform.Canonical
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString
import org.springframework.data.cassandra.mapping.PrimaryKey
import org.springframework.data.cassandra.mapping.Table

@Table('setting')
@Canonical
@EqualsAndHashCode(includes = ['setting'])
@ToString(includeNames = true, includeFields = true)
class Setting {

    @PrimaryKey
    String setting
    String value
}
