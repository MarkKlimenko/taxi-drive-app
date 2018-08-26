package systems.vostok.taxi.drive.app.operation.rate

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import systems.vostok.taxi.drive.app.component.EntityMatcher
import systems.vostok.taxi.drive.app.dao.domain.operation.OperationContext
import systems.vostok.taxi.drive.app.dao.entity.PriceCtc
import systems.vostok.taxi.drive.app.dao.repository.impl.PriceCtcRepository
import systems.vostok.taxi.drive.app.operation.CoreOperation
import systems.vostok.taxi.drive.app.util.JavaConverters
import systems.vostok.tda.domain.ParsedRow
import systems.vostok.tda.service.ExcelParserService

import javax.transaction.Transactional

import static systems.vostok.taxi.drive.app.dao.domain.operation.CoreOperationNames.UPLOAD_CTC_RATES
import static systems.vostok.tda.util.constants.ParserType.PLAIN_HEADER
import static systems.vostok.tda.util.constants.RowType.SIMPLE

/*
Enroll supported only per V2 OperationController
enroll
 OperationRequest operationRequest = new OperationRequest(
                operationName: UPLOAD_CTC_RATES.name,
                direction: enroll,
                bytePayload: ByteBuffer.wrap(getFile('rate', "${fileName}.xlsx"))
 )

rollback
 {
    "operationName": "UPLOAD_CTC_RATES",
    "direction": "rollback",
    "bytePayload": {
        "id" : "Json payload({"id" : "51ae64c4-3327-4b73-9498-1fa3347d2a15"})"
    }
 }
*/

@Component
class RateCtcUploadOperation implements CoreOperation {
    String operationName = UPLOAD_CTC_RATES.name
    String operationTimeout

    @Autowired
    EntityMatcher entityMatcher

    @Autowired
    PriceCtcRepository priceCtcRepository

    @Override
    @Transactional
    Object enroll(OperationContext context) {
        ByteArrayInputStream bis = new ByteArrayInputStream(context.operationRequest.bytePayload.array())

        new ExcelParserService().parseDocument(bis, 0, PLAIN_HEADER())
                .with(JavaConverters.&convertIterable)
                .with(this.&removeHeader)
                .collect(this.&composePriceCtc)
                .with { priceCtcRepository.saveAll(it) }
    }

    @Override
    @Transactional
    Object rollback(OperationContext context) {
        throw new UnsupportedOperationException('Rollback is not supported')
    }

    protected Iterable removeHeader(Iterable data) {
        data.findAll { it.rowType == SIMPLE() }
    }

    protected PriceCtc composePriceCtc(ParsedRow parsedRow) {
        Iterable row = JavaConverters.convertIterable(parsedRow.content)

        new PriceCtc(entityMatcher.getCityId(row[0]), entityMatcher.getCityId(row[1]), row[3] as Integer)
    }
}
