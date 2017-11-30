package com.markklim.taxi.drive.app.dao.impl

import com.datastax.driver.core.querybuilder.QueryBuilder
import com.datastax.driver.core.querybuilder.Select
import com.markklim.taxi.drive.app.dao.UniversalDao
import com.markklim.taxi.drive.app.dao.entity.SystemProperty
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class SystemPropertyDao {
    @Autowired
    @Delegate
    UniversalDao universalDao

    SystemProperty getValue(String property) {
        Select select = QueryBuilder.select().from('system_property')
        selectSingle(select, SystemProperty.class)
    }

    void add(SystemProperty systemProperty) {
        insertSingle(systemProperty)
    }
}
