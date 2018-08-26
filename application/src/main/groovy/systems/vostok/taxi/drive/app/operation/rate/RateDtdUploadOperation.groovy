package systems.vostok.taxi.drive.app.operation.rate

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import systems.vostok.taxi.drive.app.component.EntityMatcher
import systems.vostok.taxi.drive.app.dao.domain.operation.OperationContext
import systems.vostok.taxi.drive.app.dao.entity.PriceDtd
import systems.vostok.taxi.drive.app.dao.repository.impl.PriceDtdRepository
import systems.vostok.taxi.drive.app.operation.CoreOperation
import systems.vostok.taxi.drive.app.util.JavaConverters
import systems.vostok.tda.domain.ParsedRow
import systems.vostok.tda.service.ExcelParserService

import javax.transaction.Transactional

import static systems.vostok.taxi.drive.app.dao.domain.operation.CoreOperationNames.UPLOAD_DTD_RATES
import static systems.vostok.tda.util.constants.ParserType.MIRROR_DIAGONAL

/*
Enroll supported only per V2 OperationController
enroll
 OperationRequest operationRequest = new OperationRequest(
                operationName: UPLOAD_DTD_RATES.name,
                direction: enroll,
                bytePayload: ByteBuffer.wrap(getFile('rate', "${fileName}.xlsx"))
 )

rollback
 {
    "operationName": "UPLOAD_DTD_RATES",
    "direction": "rollback",
    "bytePayload": {
        "id" : "Json payload({"id" : "51ae64c4-3327-4b73-9498-1fa3347d2a15"})"
    }
 }
*/

@Component
class RateDtdUploadOperation implements CoreOperation {
    String operationName = UPLOAD_DTD_RATES.name
    String operationTimeout

    @Autowired
    EntityMatcher entityMatcher

    @Autowired
    PriceDtdRepository priceDtdRepository

    @Override
    @Transactional
    Object enroll(OperationContext context) {
        ByteArrayInputStream bis = new ByteArrayInputStream(context.operationRequest.bytePayload.array())

        new ExcelParserService().parseDocument(bis, 0, MIRROR_DIAGONAL())
                .with(JavaConverters.&convertIterable)
                .collect(this.&composePriceDtd)
                .unique()
                .with { priceDtdRepository.saveAll(it) }
    }

    @Override
    @Transactional
    Object rollback(OperationContext context) {
        throw new UnsupportedOperationException('Rollback is not supported')
    }

    protected PriceDtd composePriceDtd(ParsedRow parsedRow) {
        Iterable row = JavaConverters.convertIterable(parsedRow.content)
        new PriceDtd(entityMatcher.getDistrictId(row[0]), entityMatcher.getDistrictId(row[1]), row[2] as Integer)
    }
}
