package systems.vostok.taxi.drive.app.dao.repository.impl.geo

import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import systems.vostok.taxi.drive.app.dao.entity.geo.City
import systems.vostok.taxi.drive.app.dao.repository.BasicRepository

import static systems.vostok.taxi.drive.app.util.constant.SqlEntities.CITY

@Repository
interface CityRepository extends BasicRepository<City, String> {
    String entityType = CITY

    @Query('select c.id from City c where c.name = :name')
    String findIdByName(@Param('name') String name)
}
