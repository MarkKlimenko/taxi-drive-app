package systems.vostok.taxi.drive.app.dao.repository.impl.geo

import org.springframework.stereotype.Repository
import systems.vostok.taxi.drive.app.dao.entity.geo.Country
import systems.vostok.taxi.drive.app.dao.repository.BasicRepository

import static systems.vostok.taxi.drive.app.util.constant.SqlEntity.COUNTRY

@Repository
interface CountryRepository extends BasicRepository<Country, String> {
    String entityType = COUNTRY
}
