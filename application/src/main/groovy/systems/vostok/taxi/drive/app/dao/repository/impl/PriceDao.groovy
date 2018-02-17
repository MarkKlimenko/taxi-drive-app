package systems.vostok.taxi.drive.app.dao.repository.impl

import com.datastax.driver.core.querybuilder.QueryBuilder
import com.datastax.driver.core.querybuilder.Select
import systems.vostok.taxi.drive.app.dao.repository.UniversalDao
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import systems.vostok.taxi.drive.app.dao.entity.PriceCtc
import systems.vostok.taxi.drive.app.dao.entity.PriceDtd

import static systems.vostok.taxi.drive.app.util.CommonUtil.*

@Component
class PriceDao {

    @Autowired
    UniversalDao universalDao

    Integer getDistrictsRidePrice(String districtFrom, String districtTo) {
        Select select = QueryBuilder.select().from('price_dtd')
        select.where(QueryBuilder.eq('id', generateId(districtFrom, districtTo)))

        // TODO: Exception
        PriceDtd priceDtd = universalDao.selectSingle(select, PriceDtd.class)
        if (priceDtd) {
            return priceDtd.price
        } else {
            throw new IllegalArgumentException('no_such_district_in_db')
        }
    }

    Integer getCitiesRidePrice(String cityFrom, String cityTo) {
        Select select = QueryBuilder.select().from('price_ctc')
        select.where(QueryBuilder.eq('id', generateId(cityFrom, cityTo)))

        universalDao.selectSingle(select, PriceCtc.class).price
    }

    void addPriceCtc (PriceCtc priceCtc) {
        universalDao.insertSingle(priceCtc)
    }

    void addPriceDtd (PriceDtd priceDtd) {
        universalDao.insertSingle(priceDtd)
    }

    List<PriceDtd> getAllDtdRates() {
        Select select = QueryBuilder.select().from('price_dtd')
        universalDao.selectAll(select, PriceDtd.class)
    }

    List<PriceCtc> getAllCtcRates() {
        Select select = QueryBuilder.select().from('price_ctc')
        universalDao.selectAll(select, PriceCtc.class)
    }

    void deleteCtcRate(PriceCtc priceCtc) {
        universalDao.deleteSingle(PriceCtc.class, priceCtc.id)
    }

    void deleteDtdRate(PriceDtd priceDtd) {
        universalDao.deleteSingle(PriceDtd.class, priceDtd.id)
    }
}
