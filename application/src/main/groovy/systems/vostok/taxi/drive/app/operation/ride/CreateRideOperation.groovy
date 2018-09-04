package systems.vostok.taxi.drive.app.operation.ride

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import systems.vostok.taxi.drive.app.component.ride.RideComponent
import systems.vostok.taxi.drive.app.dao.domain.RideState
import systems.vostok.taxi.drive.app.dao.domain.operation.OperationContext
import systems.vostok.taxi.drive.app.dao.entity.Client
import systems.vostok.taxi.drive.app.dao.entity.Ride
import systems.vostok.taxi.drive.app.dao.repository.impl.ClientRepository
import systems.vostok.taxi.drive.app.dao.repository.impl.RideRepository
import systems.vostok.taxi.drive.app.dao.repository.impl.geo.AddressRepository
import systems.vostok.taxi.drive.app.operation.CoreOperation
import systems.vostok.taxi.drive.app.service.ClientManagementService

import javax.transaction.Transactional
import java.time.LocalDateTime

import static systems.vostok.taxi.drive.app.dao.domain.operation.CoreOperationNames.CREATE_RIDE_OPERATION
import static systems.vostok.taxi.drive.app.util.ContentTypeConverter.toMap

@Component
class CreateRideOperation implements CoreOperation {
    String operationName = CREATE_RIDE_OPERATION.name
    String operationTimeout

    @Autowired
    RideRepository rideRepository

    @Autowired
    AddressRepository addressRepository

    @Autowired
    ClientManagementService clientManagementService

    @Autowired
    ClientRepository clientRepository

    @Autowired
    RideComponent rideComponent

    @Override
    @Transactional
    Object enroll(OperationContext context) {
        Ride ride = toMap(context.operationRequest.stringPayload) as Ride

        Client client = clientRepository.findById(ride.client)
                .orElseThrow({ new NullPointerException("No client with id { ${ride.client} }") })

        client.ridesAmount.next()
        clientRepository.save(client)

        ride.fromAddress = addressRepository.save(ride.rawFromAddress).id
        ride.toAddress = addressRepository.save(ride.rawToAddress).id
        ride.dateIn = LocalDateTime.now()
        ride.rideIn = ride.rideIn ?: ride.dateIn
        ride.price = clientManagementService.evaluateRide(ride).price as Integer
        ride.state = RideState.STATE_NEW.state

        Ride savedRide = rideRepository.save(ride)
        context.contextHelper.setContext(context, savedRide)
        context.contextHelper.setEntityId(context, rideRepository.getEntityId(savedRide))

        rideComponent.addNewRideToActiveList(savedRide)

        savedRide
    }

    @Override
    @Transactional
    Object rollback(OperationContext context) {
        throw new UnsupportedOperationException('Rollback is not supported')
    }
}
