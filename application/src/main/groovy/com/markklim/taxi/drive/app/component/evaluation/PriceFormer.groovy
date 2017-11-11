package com.markklim.taxi.drive.app.component.evaluation

import com.datastax.driver.core.querybuilder.QueryBuilder
import com.datastax.driver.core.querybuilder.Select
import com.markklim.taxi.drive.app.dao.UniversalDao
import com.markklim.taxi.drive.app.model.PriceDtd
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class PriceFormer {

    @Autowired
    UniversalDao universalDao

    Integer priceDistToDist(String from, String to) {
        Select select = QueryBuilder.select().from('price_dtd')
        select.where(QueryBuilder.eq('id', generateId(from,to)))

        universalDao.selectSingle(select, PriceDtd.class).price
    }

    protected Integer generateId(String distFrom, String distTo) {
        distFrom.hashCode() + distTo.hashCode()
    }
}
