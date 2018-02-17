package systems.vostok.taxi.drive.app.dao.repository.sql.impl.geo

import org.springframework.stereotype.Repository
import systems.vostok.taxi.drive.app.dao.entity.geo.Country
import systems.vostok.taxi.drive.app.dao.repository.sql.BasicRepository

import static systems.vostok.taxi.drive.app.util.constant.SqlEntities.COUNTRY

@Repository
interface CountryRepository extends BasicRepository<Country, String> {
    String entityType = COUNTRY
}
