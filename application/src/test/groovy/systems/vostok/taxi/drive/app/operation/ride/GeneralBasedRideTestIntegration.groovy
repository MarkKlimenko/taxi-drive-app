package systems.vostok.taxi.drive.app.operation.ride

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import systems.vostok.taxi.drive.app.dao.domain.operation.OperationResponse
import systems.vostok.taxi.drive.app.dao.entity.ActiveRide
import systems.vostok.taxi.drive.app.dao.repository.impl.ActiveRideRepository
import systems.vostok.taxi.drive.app.operation.PreconditionTestUtil
import systems.vostok.taxi.drive.app.operation.client.ClientFlowTestUtil

import static org.junit.jupiter.api.Assertions.assertEquals

@ExtendWith(SpringExtension.class)
@SpringBootTest
@DisplayName('General based ride test')
class GeneralBasedRideTestIntegration {
    @Autowired
    RideFlowTestUtil rideUtil

    @Autowired
    ClientFlowTestUtil clientUtil

    @Autowired
    PreconditionTestUtil preconditionUtil

    @Autowired
    ActiveRideRepository activeRideRepository

    @Test
    @DisplayName('Create simple ride')
    void createSimpleRide() {
        preconditionUtil.ridePreconditions()
        clientUtil.createClient('simple_client')

        OperationResponse response = rideUtil.createRide('simple_ride')
        rideUtil.checkRide('simple_ride', response)

        ActiveRide activeRide = activeRideRepository.findById(response.body.id).get()
        assertEquals(response.body.client, activeRide.client)
        assertEquals(response.body.state, activeRide.state)
        assertEquals(response.body.price, activeRide.price)
    }
}
