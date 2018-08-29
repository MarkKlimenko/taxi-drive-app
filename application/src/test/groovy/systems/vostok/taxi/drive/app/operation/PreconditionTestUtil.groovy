package systems.vostok.taxi.drive.app.operation

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import systems.vostok.taxi.drive.app.operation.client.ClientFlowTestUtil
import systems.vostok.taxi.drive.app.operation.rate.RateFlowTestUtil
import systems.vostok.taxi.drive.app.operation.ride.RideFlowTestUtil

@Component
class PreconditionTestUtil {
    @Autowired
    RateFlowTestUtil rateFlowTestUtil

    @Autowired
    ClientFlowTestUtil clientUtil

    @Autowired
    RideFlowTestUtil rideUtil

    void ridePreconditions() {
        rateFlowTestUtil.with {
            removeAllCtcRates()
            removeAllDtdRates()

            uploadCtcRates('long_distance')
            uploadDtdRates('district')
        }

        rideUtil.removeAllRides()
        clientUtil.removeAllClients()
    }

    void clientPreconditions() {
        rideUtil.removeAllRides()
        clientUtil.removeAllClients()
    }
}
