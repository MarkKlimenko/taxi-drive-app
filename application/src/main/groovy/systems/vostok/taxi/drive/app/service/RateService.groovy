package systems.vostok.taxi.drive.app.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import systems.vostok.taxi.drive.app.component.EntityMatcher
import systems.vostok.taxi.drive.app.dao.entity.PriceCtc
import systems.vostok.taxi.drive.app.dao.entity.PriceDtd
import systems.vostok.taxi.drive.app.dao.repository.sql.BasicRepository
import systems.vostok.taxi.drive.app.dao.repository.sql.UniversalCrudRepository
import systems.vostok.taxi.drive.app.dao.repository.sql.impl.geo.CityRepository
import systems.vostok.taxi.drive.app.dao.repository.sql.impl.geo.DistrictRepository
import systems.vostok.taxi.drive.app.util.JavaConverters
import systems.vostok.tda.domain.ParsedRow
import systems.vostok.tda.service.ExcelParserService

import static systems.vostok.taxi.drive.app.util.constant.SqlEntities.PRICE_CTC
import static systems.vostok.taxi.drive.app.util.constant.SqlEntities.PRICE_DTD
import static systems.vostok.tda.util.constants.ParserType.MIRROR_DIAGONAL
import static systems.vostok.tda.util.constants.ParserType.PLAIN_HEADER
import static systems.vostok.tda.util.constants.RowType.SIMPLE

@Service
class RateService {
    @Autowired
    EntityMatcher entityMatcher

    @Autowired
    UniversalCrudRepository crudRepository

    @Autowired
    DistrictRepository districtRepository

    @Autowired
    CityRepository cityRepository

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
        def findDistrict = { findIdByName(it, districtRepository) }

        new PriceDtd(findDistrict(row[0]), findDistrict(row[1]), row[2] as Integer)
    }

    protected PriceCtc composePriceCtc(ParsedRow parsedRow) {
        Iterable row = JavaConverters.convertIterable(parsedRow.content)
        def findDistrict = { findIdByName(it, cityRepository) }

        new PriceCtc(findDistrict(row[0]), findDistrict(row[1]), row[3] as Integer)
    }

    protected String findIdByName(String name, BasicRepository repository) {
        def checkExistence = {
            if (!it) {
                throw new NullPointerException("No such entity name '$name'")
            }
            it
        }

        repository.findIdByName(name)
                .with(checkExistence)
    }
}
