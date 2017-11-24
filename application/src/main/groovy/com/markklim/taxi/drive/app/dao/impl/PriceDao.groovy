package com.markklim.taxi.drive.app.dao.impl

import com.datastax.driver.core.querybuilder.QueryBuilder
import com.datastax.driver.core.querybuilder.Select
import com.markklim.taxi.drive.app.dao.UniversalDao
import com.markklim.taxi.drive.app.model.PriceCtc
import com.markklim.taxi.drive.app.model.PriceDtd
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
        select.where(QueryBuilder.eq('id', generateId(from,to)))

        universalDao.selectSingle(select, PriceDtd.class).price
    }

    Integer getCitiesRidePrice(String from, String to) {
        Select select = QueryBuilder.select().from('price_ctc')
        select.where(QueryBuilder.eq('id', generateId(from,to)))

        universalDao.selectSingle(select, PriceCtc.class).price
    }

    // TODO: Change to proper implementation
    protected Integer generateId(String distFrom, String distTo) {
        distFrom.hashCode() + distTo.hashCode()
    }
}
