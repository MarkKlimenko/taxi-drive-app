package systems.vostok.taxi.drive.app.service

import systems.vostok.taxi.drive.app.component.EntityMatcher
import systems.vostok.taxi.drive.app.dao.entity.PriceCtc
import systems.vostok.taxi.drive.app.dao.entity.PriceDtd
import systems.vostok.taxi.drive.app.dao.impl.PriceDao
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import system.vostok.tda.service.ExcelParserService

@Service
class RateService {

    @Autowired
    @Delegate
    EntityMatcher entityMatcher

    @Autowired
    PriceDao priceDao

    void uploadDtdConfig(InputStream file) {
        new ExcelParserService().parseDocument(file, 0, 'MIRROR_DIAGONAL')
                .collect { new PriceDtd(getDistrictId(it[0]), getDistrictId(it[1]), it[2] as Integer) }
                .toSet()
                .each { priceDao.addPriceDtd(it) }
    }

    void uploadCtcConfig(InputStream file) {
        new ExcelParserService().parseDocument(file, 0, 'PLAIN_HEADER')
                .with { removeHeader(it) }
                .collect { new PriceCtc(getCityId(it[0]), getCityId(it[1]), it[3] as Integer) }
                .each { priceDao.addPriceCtc(it) }
    }

    protected List<String> removeHeader(List data) {
        data.remove(0)
        data
    }
}
