package systems.vostok.taxi.drive.app.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import systems.vostok.taxi.drive.app.component.EntityMatcher
import systems.vostok.taxi.drive.app.dao.entity.PriceCtc
import systems.vostok.taxi.drive.app.dao.entity.PriceDtd
import systems.vostok.taxi.drive.app.dao.repository.UniversalCrudRepository
import systems.vostok.taxi.drive.app.util.JavaConverters
import systems.vostok.tda.domain.ParsedRow
import systems.vostok.tda.service.ExcelParserService

import static systems.vostok.taxi.drive.app.util.constant.SqlEntity.PRICE_CTC
import static systems.vostok.taxi.drive.app.util.constant.SqlEntity.PRICE_DTD
import static systems.vostok.tda.util.constants.ParserType.MIRROR_DIAGONAL
import static systems.vostok.tda.util.constants.ParserType.PLAIN_HEADER
import static systems.vostok.tda.util.constants.RowType.SIMPLE

@Service
class RateService {
    @Autowired
    EntityMatcher entityMatcher

    @Autowired
    UniversalCrudRepository crudRepository

    void uploadDtdConfig(InputStream file) {
        new ExcelParserService().parseDocument(file, 0, MIRROR_DIAGONAL())
                .with(JavaConverters.&convertIterable)
                .collect(this.&composePriceDtd)
                .toSet()
                .with { crudRepository.putAll(PRICE_DTD, it) }
    }

    void uploadCtcConfig(InputStream file) {
        new ExcelParserService().parseDocument(file, 0, PLAIN_HEADER())
                .with(JavaConverters.&convertIterable)
                .with(this.&removeHeader)
                .collect(this.&composePriceCtc)
                .with { crudRepository.putAll(PRICE_CTC, it) }
    }

    protected Iterable removeHeader(Iterable data) {
        data.findAll { it.rowType == SIMPLE() }
    }

    protected PriceDtd composePriceDtd(ParsedRow parsedRow) {
        Iterable row = JavaConverters.convertIterable(parsedRow.content)

        new PriceDtd(entityMatcher.getDistrictId(row[0]), entityMatcher.getDistrictId(row[1]), row[2] as Integer)
    }

    protected PriceCtc composePriceCtc(ParsedRow parsedRow) {
        Iterable row = JavaConverters.convertIterable(parsedRow.content)

        new PriceCtc(entityMatcher.getCityId(row[0]), entityMatcher.getCityId(row[1]), row[3] as Integer)
    }
}
