package systems.vostok.taxi.drive.app.operation.rate

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import systems.vostok.taxi.drive.app.dao.entity.PriceCtc
import systems.vostok.taxi.drive.app.dao.entity.PriceDtd

@ExtendWith(SpringExtension.class)
@SpringBootTest
@DisplayName('Rate flow test')
class RateFlowTestIntegration {
    @Autowired
    RateFlowTestUtil rateUtil

    @Test
    @DisplayName('Upload dtd rates')
    void uploadDtdRates() {
        rateUtil.removeAllDtdRates()
        rateUtil.uploadDtdRates('district')
                .with { rateUtil.checkRates(PriceDtd.class, 'district', it) }
    }

    @Test
    @DisplayName('Upload ctc rates')
    void uploadCtcRates() {
        rateUtil.removeAllCtcRates()
        rateUtil.uploadCtcRates('long_distance')
                .with { rateUtil.checkRates(PriceCtc.class, 'long_distance', it) }
    }

    @Test
    @DisplayName('Upload suburb rates')
    void uploadSuburbRates() {
        rateUtil.removeAllCtcRates()
        rateUtil.uploadCtcRates('suburb')
                .with { rateUtil.checkRates(PriceCtc.class, 'suburb', it) }
    }
}
