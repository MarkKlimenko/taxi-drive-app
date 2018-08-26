package systems.vostok.taxi.drive.app.operation.rate

import groovy.json.JsonSlurper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import systems.vostok.taxi.drive.app.dao.domain.operation.OperationResponse
import systems.vostok.taxi.drive.app.dao.repository.impl.PriceCtcRepository
import systems.vostok.taxi.drive.app.dao.repository.impl.PriceDtdRepository
import systems.vostok.taxi.drive.app.executor.OperationService
import systems.vostok.taxi.drive.app.operation.OperationRequest

import java.nio.ByteBuffer

import static org.junit.jupiter.api.Assertions.assertEquals
import static org.junit.jupiter.api.Assertions.assertTrue
import static systems.vostok.taxi.drive.app.dao.domain.operation.CoreOperationNames.UPLOAD_CTC_RATES
import static systems.vostok.taxi.drive.app.dao.domain.operation.CoreOperationNames.UPLOAD_DTD_RATES
import static systems.vostok.taxi.drive.app.operation.OperationDirection.enroll
import static systems.vostok.taxi.drive.app.test.Dataset.getFile
import static systems.vostok.taxi.drive.app.test.Dataset.getRawJsonDataset

@Component
class RateFlowTestUtil {
    @Autowired
    PriceDtdRepository priceDtdRepository

    @Autowired
    PriceCtcRepository priceCtcRepository

    @Autowired
    OperationService operationService

    void removeAllDtdRates() {
        priceDtdRepository.deleteAll()
    }

    void removeAllCtcRates() {
        priceCtcRepository.deleteAll()
    }

    OperationResponse uploadDtdRates(String fileName) {
        uploadRates(UPLOAD_DTD_RATES.name, fileName)

    }

    OperationResponse uploadCtcRates(String fileName) {
        uploadRates(UPLOAD_CTC_RATES.name, fileName)
    }

    private OperationResponse uploadRates(String operationName, String fileName) {
        OperationRequest operationRequest = new OperationRequest(
                operationName: operationName,
                direction: enroll,
                bytePayload: ByteBuffer.wrap(getFile('rate', "${fileName}.xlsx"))
        )

        operationService.execute(operationRequest)
    }

    void checkRates(Class clazz, String datasetName, OperationResponse response) {
        List expectedPrices = new JsonSlurper().parseText(getRawJsonDataset('rate', datasetName))
                .collect { clazz.newInstance(it) }
        List actualPrices = response.body as List

        assertEquals(expectedPrices.size(), actualPrices.size())

        expectedPrices.each {
            assertTrue(actualPrices.contains(it))
        }
    }
}
