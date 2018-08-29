package systems.vostok.taxi.drive.app.operation.ride

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import systems.vostok.taxi.drive.app.dao.domain.operation.OperationResponse
import systems.vostok.taxi.drive.app.dao.entity.Ride
import systems.vostok.taxi.drive.app.dao.repository.impl.RideRepository
import systems.vostok.taxi.drive.app.executor.OperationService
import systems.vostok.taxi.drive.app.operation.OperationRequest

import static org.junit.jupiter.api.Assertions.assertEquals
import static systems.vostok.taxi.drive.app.dao.domain.operation.CoreOperationNames.CREATE_RIDE_OPERATION
import static systems.vostok.taxi.drive.app.operation.OperationDirection.enroll
import static systems.vostok.taxi.drive.app.test.Dataset.getJsonDataset
import static systems.vostok.taxi.drive.app.test.Dataset.getRawJsonDataset

@Component
class RideFlowTestUtil {
    @Autowired
    OperationService operationService

    @Autowired
    RideRepository rideRepository

    void removeAllRides() {
        rideRepository.deleteAll()
    }

    OperationResponse createRide(String datasetName) {
        OperationRequest operationRequest = new OperationRequest(
                operationName: CREATE_RIDE_OPERATION.name,
                direction: enroll,
                stringPayload: getRawJsonDataset('ride', datasetName)
        )

        operationService.execute(operationRequest)
    }

    void checkRide(String datasetName, OperationResponse response) {
        Ride expectedRide = getJsonDataset('ride', datasetName) as Ride
        Ride actualRide = rideRepository.findById(response.body.id).get()

        assertEquals(expectedRide.client, actualRide.client)
        assertEquals(expectedRide.adultInCar, actualRide.adultInCar)
        assertEquals(110, actualRide.price)
    }
}
