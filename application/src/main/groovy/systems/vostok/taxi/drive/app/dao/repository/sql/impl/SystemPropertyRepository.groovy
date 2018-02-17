package systems.vostok.taxi.drive.app.dao.repository.sql.impl

import org.springframework.stereotype.Repository
import systems.vostok.taxi.drive.app.dao.entity.SystemProperty
import systems.vostok.taxi.drive.app.dao.entity.geo.City
import systems.vostok.taxi.drive.app.dao.repository.sql.BasicRepository

import static systems.vostok.taxi.drive.app.util.constant.SqlEntities.SYSTEM_PROPERTY

@Repository
interface SystemPropertyRepository extends BasicRepository<SystemProperty, String> {
    String entityType = SYSTEM_PROPERTY
}
