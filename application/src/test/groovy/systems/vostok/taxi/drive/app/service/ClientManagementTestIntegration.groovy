package systems.vostok.taxi.drive.app.service

import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import systems.vostok.taxi.drive.app.dao.domain.RidePrice
import systems.vostok.taxi.drive.app.dao.entity.Address
import systems.vostok.taxi.drive.app.dao.entity.Client
import systems.vostok.taxi.drive.app.dao.entity.Ride
import systems.vostok.taxi.drive.app.operation.client.ClientFlowTestUtil
import systems.vostok.taxi.drive.app.operation.rate.RateFlowTestUtil

import static org.junit.jupiter.api.Assertions.assertEquals
import static org.junit.jupiter.api.Assertions.assertFalse

@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName('Client management test')
class ClientManagementTestIntegration {
    @Autowired
    ClientManagementService clientManagementService

    @Autowired
    RateFlowTestUtil rateFlowTestUtil

    @Autowired
    ClientFlowTestUtil clientUtil

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
    @DisplayName('Get client information')
    void getClientInfoTest() {
        clientUtil.removeAllClients()
        clientUtil.createClient('simple_client')

        Client client = clientManagementService.getClientInfo('+79147654321')

        client.with {
            assertEquals(5, it.ridesAmount)
            assertFalse(it.rideFree)
        }
    }

    @Test
    @DisplayName('Evaluate ride for nonexistent client and dtd type')
    void evaluateRideNoClientDtdTest() {
        Ride ride = new Ride(
                rawFromAddress: new Address([city: 'Спасск-Дальний', street: 'Парковая', building: '29']),
                rawToAddress: new Address([city: 'Спасск-Дальний', street: 'Советская', building: '10'])
        )

        RidePrice ridePrice = clientManagementService.evaluateRide(ride)
        assertEquals('90', ridePrice.price)
    }
}
