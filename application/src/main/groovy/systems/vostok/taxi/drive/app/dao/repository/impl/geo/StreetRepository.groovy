package systems.vostok.taxi.drive.app.dao.repository.impl.geo

import org.springframework.stereotype.Repository
import systems.vostok.taxi.drive.app.dao.entity.geo.Street
import systems.vostok.taxi.drive.app.dao.repository.BasicRepository

import static systems.vostok.taxi.drive.app.util.constant.SqlEntity.STREET

@Repository
interface StreetRepository extends BasicRepository<Street, String> {
    String entityType = STREET

    Street findByNameAndCity(String streetName, String city)
}
