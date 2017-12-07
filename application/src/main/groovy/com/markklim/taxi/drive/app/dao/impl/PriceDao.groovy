package com.markklim.taxi.drive.app.dao.impl

import com.datastax.driver.core.querybuilder.QueryBuilder
import com.datastax.driver.core.querybuilder.Select
import com.markklim.taxi.drive.app.dao.UniversalDao
import com.markklim.taxi.drive.app.dao.entity.PriceCtc
import com.markklim.taxi.drive.app.dao.entity.PriceDtd
import com.markklim.taxi.drive.app.utils.IdUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.cassandra.core.CassandraTemplate
import org.springframework.stereotype.Component

@Component
class PriceDao {
    @Autowired
    private CassandraTemplate cassandraTemplate

    @Autowired
    UniversalDao universalDao

    void addPriceDtd(PriceDtd priceDtd) {
        cassandraTemplate.insert(priceDtd)
    }

    void addPriceCtc(PriceCtc priceCtc){
        cassandraTemplate.insert(priceCtc)
    }

    Integer getDistrictsRidePrice(String from, String to) {
        Select select = QueryBuilder.select().from('price_dtd')
        select.where(QueryBuilder.eq('id', IdUtil.generateId(from, to)))

        // TODO: Exception
        universalDao.selectSingle(select, PriceDtd.class).price
    }

    Integer getCitiesRidePrice(String from, String to) {
        Select select = QueryBuilder.select().from('price_ctc')
        select.where(QueryBuilder.eq('id', IdUtil.generateId(from, to)))
        universalDao.selectSingle(select, PriceCtc.class).price
    }
}
