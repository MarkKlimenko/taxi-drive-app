package systems.vostok.taxi.drive.app.dao.repository.sql.impl.geo

import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import systems.vostok.taxi.drive.app.dao.entity.geo.District
import systems.vostok.taxi.drive.app.dao.repository.sql.BasicRepository

import static systems.vostok.taxi.drive.app.util.constant.SqlEntities.DISTRICT

@Repository
interface DistrictRepository extends BasicRepository<District, String> {
    String entityType = DISTRICT

    @Query('select d.id from District d where d.name = :name')
    String findIdByName(@Param('name') String name)
}
