package systems.vostok.taxi.drive.app.operation.ride

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import systems.vostok.taxi.drive.app.operation.PreconditionTestUtil
import systems.vostok.taxi.drive.app.operation.client.ClientFlowTestUtil

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

    @Test
    @DisplayName('Create simple ride')
    void createSimpleRide() {
        preconditionUtil.ridePreconditions()

        clientUtil.createClient('simple_client')
        rideUtil.createRide('simple_ride')
                .with { rideUtil.checkRide('simple_ride', it) }
    }
}
