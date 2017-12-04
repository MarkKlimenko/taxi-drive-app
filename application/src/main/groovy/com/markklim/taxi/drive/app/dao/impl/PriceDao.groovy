package com.markklim.taxi.drive.app.dao.impl

import com.datastax.driver.core.querybuilder.QueryBuilder
import com.datastax.driver.core.querybuilder.Select
import com.markklim.taxi.drive.app.dao.UniversalDao
import com.markklim.taxi.drive.app.dao.entity.PriceCtc
import com.markklim.taxi.drive.app.dao.entity.PriceDtd
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class PriceDao {

    @Autowired
    UniversalDao universalDao

    Integer getDistrictsRidePrice(String from, String to) {
        Select select = QueryBuilder.select().from('price_dtd')
        select.where(QueryBuilder.eq('id', generateId(from,to)))

        // TODO: Exception
        PriceDtd priceDtd =  universalDao.selectSingle(select, PriceDtd.class)
        if(priceDtd) {
            return priceDtd.price
        } else {
            throw new IllegalArgumentException('no_such_district_in_db')
        }
    }

    Integer getCitiesRidePrice(String from, String to) {
        Select select = QueryBuilder.select().from('price_ctc')
        select.where(QueryBuilder.eq('id', generateId(from,to)))
        universalDao.selectSingle(select, PriceCtc.class).price
    }

    private Integer generateId(String from, String to) {
        [from, to].sort().hashCode()
    }
}
