package systems.vostok.taxi.drive.app.dao.repository.sql.impl.geo

import org.springframework.stereotype.Repository
import systems.vostok.taxi.drive.app.dao.entity.geo.Street
import systems.vostok.taxi.drive.app.dao.repository.sql.BasicRepository

import static systems.vostok.taxi.drive.app.util.constant.SqlEntities.STREET

@Repository
interface StreetRepository extends BasicRepository<Street, String> {
    String entityType = STREET

    // Street getStreetByNameAndCity(String streetName, String city) {
}
