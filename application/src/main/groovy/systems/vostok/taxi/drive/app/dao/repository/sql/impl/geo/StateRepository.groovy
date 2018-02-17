package systems.vostok.taxi.drive.app.dao.repository.sql.impl.geo

import org.springframework.stereotype.Repository
import systems.vostok.taxi.drive.app.dao.entity.geo.State
import systems.vostok.taxi.drive.app.dao.repository.sql.BasicRepository

import static systems.vostok.taxi.drive.app.util.constant.SqlEntities.STATE

@Repository
interface StateRepository extends BasicRepository<State, String> {
    String entityType = STATE
}
