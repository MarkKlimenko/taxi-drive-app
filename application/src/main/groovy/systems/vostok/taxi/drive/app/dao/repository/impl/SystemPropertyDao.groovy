package systems.vostok.taxi.drive.app.dao.repository.impl

import com.datastax.driver.core.querybuilder.QueryBuilder
import com.datastax.driver.core.querybuilder.Select
import systems.vostok.taxi.drive.app.dao.repository.UniversalDao
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import systems.vostok.taxi.drive.app.dao.entity.SystemProperty

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
