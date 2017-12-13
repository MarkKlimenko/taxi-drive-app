package com.markklim.taxi.drive.app.dao.impl

import com.datastax.driver.core.querybuilder.QueryBuilder
import com.datastax.driver.core.querybuilder.Select
import com.markklim.taxi.drive.app.dao.UniversalDao
import com.markklim.taxi.drive.app.dao.entity.Client
import com.markklim.taxi.drive.app.dao.entity.StreetDistrictMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class StreetDistrictDao {
    @Autowired
    @Delegate
    UniversalDao universalDao

    List<StreetDistrictMapper> getAll() {
        Select select = QueryBuilder.select().from('street_district_mapper')
        selectAll(select, StreetDistrictMapper.class)
    }

    List<StreetDistrictMapper> getByStreetId(String streetId) {
        Select select = QueryBuilder.select().from('street_district_mapper')
        select.where(QueryBuilder.eq('streetId', streetId))

        selectAll(select, StreetDistrictMapper.class)
    }

    void add(StreetDistrictMapper streetDistrictMapper) {
        insertSingle(streetDistrictMapper)
    }

    void delete(StreetDistrictMapper streetDistrictMapper) {
        deleteSingle(StreetDistrictMapper.class, streetDistrictMapper.id)
    }
}
