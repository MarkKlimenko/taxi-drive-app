package systems.vostok.taxi.drive.app.dao.repository.impl

import org.springframework.stereotype.Repository
import systems.vostok.taxi.drive.app.dao.entity.StreetDistrictMapper
import systems.vostok.taxi.drive.app.dao.repository.BasicRepository

import static systems.vostok.taxi.drive.app.util.constant.SqlEntity.STREET_DISTRICT_MAPPER

@Repository
interface StreetDistrictMapperRepository extends BasicRepository<StreetDistrictMapper, Long> {
    String entityType = STREET_DISTRICT_MAPPER

    List<StreetDistrictMapper> findByStreetId(String streetId)
}
