package com.markklim.taxi.drive.app.dao.entity

import groovy.transform.Canonical
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString
import org.springframework.data.cassandra.mapping.PrimaryKey
import org.springframework.data.cassandra.mapping.Table

@Table('street_district_mapper')
@Canonical
@EqualsAndHashCode(includes = ['id'])
@ToString(includeNames = true, includeFields = true)
class StreetDistrictMapper {

    @PrimaryKey
    UUID id = UUID.randomUUID()

    String districtId

    String streetId

    String building
}
