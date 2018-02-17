package systems.vostok.taxi.drive.app.dao.repository.impl

import com.datastax.driver.core.querybuilder.QueryBuilder
import com.datastax.driver.core.querybuilder.Select
import systems.vostok.taxi.drive.app.dao.repository.UniversalDao
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import systems.vostok.taxi.drive.app.dao.entity.StreetDistrictMapper

@Component
class StreetDistrictDao {
    @Autowired
    @Delegate
    UniversalDao universalDao

    List<StreetDistrictMapper> getAll() {
        Select select = QueryBuilder.select().from('street_district_mapper')
        selectAll(select, StreetDistrictMapper.class)
    }

    List<StreetDistrictMapper> getByStreetId(String streetId) {
        Select select = QueryBuilder.select().from('street_district_mapper')
        select.where(QueryBuilder.eq('streetId', streetId))

        selectAll(select, StreetDistrictMapper.class)
    }

    void add(StreetDistrictMapper streetDistrictMapper) {
        insertSingle(streetDistrictMapper)
    }

    void delete(StreetDistrictMapper streetDistrictMapper) {
        deleteSingle(StreetDistrictMapper.class, streetDistrictMapper.id)
    }
}
