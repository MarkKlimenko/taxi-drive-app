package systems.vostok.taxi.drive.app.dao.impl

import com.datastax.driver.core.querybuilder.QueryBuilder
import com.datastax.driver.core.querybuilder.Select
import systems.vostok.taxi.drive.app.dao.UniversalDao
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import systems.vostok.taxi.drive.app.dao.entity.Setting

@Component
class SettingDao {
    @Autowired
    @Delegate
    UniversalDao universalDao

    String getValue(String property) {
        Select select = QueryBuilder.select().from('setting')
        select.where(QueryBuilder.eq("setting", property))
        selectSingle(select, Setting.class).value
    }
}
