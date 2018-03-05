package systems.vostok.taxi.drive.app.dao.repository.impl

import org.springframework.stereotype.Repository
import systems.vostok.taxi.drive.app.dao.entity.SystemProperty
import systems.vostok.taxi.drive.app.dao.repository.BasicRepository

import static systems.vostok.taxi.drive.app.util.constant.SqlEntities.SYSTEM_PROPERTY

@Repository
interface SystemPropertyRepository extends BasicRepository<SystemProperty, String> {
    String entityType = SYSTEM_PROPERTY
}
