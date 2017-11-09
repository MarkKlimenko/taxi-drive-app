package com.markklim.taxi.drive.app.component.evaluation

import com.datastax.driver.core.querybuilder.QueryBuilder
import com.datastax.driver.core.querybuilder.Select
import com.markklim.taxi.drive.app.component.database.QueryHelper
import com.markklim.taxi.drive.app.model.PriceCtc
import com.markklim.taxi.drive.app.model.PriceDtd
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class PriceFormer {

    @Autowired
    QueryHelper queryHelper

    Integer priceDistToDist(String from, String to) {
        Select select = QueryBuilder.select().from('price_dtd')
        select.where(QueryBuilder.eq('id', generateId(from,to)))

        queryHelper.getFirst(select, PriceDtd.class).price
    }

    Integer priceCityToCity(String from, String to) {
        Select select = QueryBuilder.select().from('price_ctc')
        select.where(QueryBuilder.eq('id', generateId(from,to)))

        queryHelper.getFirst(select, PriceCtc.class).price
    }

    protected Integer generateId(String distFrom, String distTo) {
        distFrom.hashCode() + distTo.hashCode()
    }
}
