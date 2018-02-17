package systems.vostok.taxi.drive.app.dao.repository.sql.impl.geo

import org.springframework.stereotype.Repository
import systems.vostok.taxi.drive.app.dao.entity.geo.District
import systems.vostok.taxi.drive.app.dao.repository.sql.BasicRepository

import static systems.vostok.taxi.drive.app.util.constant.SqlEntities.DISTRICT

@Repository
interface DistrictRepository extends BasicRepository<District, String> {
    String entityType = DISTRICT
}
