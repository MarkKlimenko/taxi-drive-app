package com.markklim.taxi.drive.app.dao.impl

import com.datastax.driver.core.querybuilder.QueryBuilder
import com.datastax.driver.core.querybuilder.Select
import com.markklim.taxi.drive.app.dao.UniversalDao
import com.markklim.taxi.drive.app.dao.entity.Setting
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class SettingDao {
    @Autowired
    @Delegate
    UniversalDao universalDao

    String getValue(String property) {
        Select select = QueryBuilder.select().from('setting')
        selectSingle(select, Setting.class).value
    }
}
