package com.markklim.taxi.drive.app.dao.impl

import com.datastax.driver.core.querybuilder.QueryBuilder
import com.datastax.driver.core.querybuilder.Select
import com.markklim.taxi.drive.app.dao.UniversalDao
import com.markklim.taxi.drive.app.dao.entity.City
import com.markklim.taxi.drive.app.dao.entity.Client
import com.markklim.taxi.drive.app.dao.entity.District
import com.markklim.taxi.drive.app.dao.entity.State
import com.markklim.taxi.drive.app.dao.entity.Street
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class GeoDao {
    @Autowired
    @Delegate
    UniversalDao universalDao

    List<State> getAllStates() {
        Select select = QueryBuilder.select().from('state')
        selectAll(select, State.class)
    }

    List<City> getAllCities() {
        Select select = QueryBuilder.select().from('city')
        selectAll(select, City.class)
    }

    List<Street> getAllStreets() {
        Select select = QueryBuilder.select().from('street')
        selectAll(select, Street.class)
    }

    List<District> getDistrictsByStreet(String street) {
        Select select = QueryBuilder.select().from('district')
        select.where(QueryBuilder.eq("street", street))
        selectAll(select, District.class)
    }

    Street getStreetIdByName(String name) {
        Select select = QueryBuilder.select().from('street')
        select.where(QueryBuilder.eq("name", name))
        selectSingle(select, Street.class)
    }

    void addStreet(Street street) {
        insertSingle(street)
    }
}
