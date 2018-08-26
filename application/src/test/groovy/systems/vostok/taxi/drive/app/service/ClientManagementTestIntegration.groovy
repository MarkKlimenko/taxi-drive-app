package systems.vostok.taxi.drive.app.service

import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import systems.vostok.taxi.drive.app.dao.entity.Address
import systems.vostok.taxi.drive.app.dao.entity.Ride
import systems.vostok.taxi.drive.app.operation.rate.RateFlowTestUtil

@ExtendWith(SpringExtension.class)
@SpringBootTest
@DisplayName('Client management test')
class ClientManagementTestIntegration {
    @Autowired
    ClientManagementService clientManagementService

    @Autowired
    RateFlowTestUtil rateFlowTestUtil

    @BeforeAll
    void init() {
        rateFlowTestUtil.with {
            removeAllCtcRates()
            removeAllDtdRates()

            uploadCtcRates('long_distance')
            uploadDtdRates('district')
        }
    }

    @Test
    @DisplayName('Evaluate ride for nonexistent client and dtd type')
    void evaluateRideNoClientDtdTest() {
        Ride ride = new Ride(
                rawFromAddress: new Address([city: 'Спасск-Дальний', street: 'Парковая', building: '29']),
                rawToAddress: new Address([city: 'Спасск-Дальний', street: 'Советская', building: '10'])
        )

        clientManagementService.evaluateRide(ride)
    }
}
