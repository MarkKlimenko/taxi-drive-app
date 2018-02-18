package systems.vostok.taxi.drive.app.service

import org.springframework.stereotype.Service

@Service
class ClientManagementService {

    /*@Autowired
    ClientDao clientDao

    @Autowired
    RideRepositoryT rideDao

    @Autowired
    PriceFormer priceFormer

    Client checkClient(String id) {
        clientDao.getByLogin(id).with {
            if(it) {
                it.rideFree = priceFormer.isRideFree(clientInfo.ridesAmount)
                it.previousRides = rideDao.getPreviousRides(id, 5,3)
                it
            }
            null
        }
    }

    Integer calculatePrice(Ride ride) {
        if (ride.fromAddress.city == ride.toAddress.city && ride.toAddress.city == 'Спасск-Дальний') {
            priceFormer.calculateDtdPrice(ride)
        } else {
            priceFormer.calculateCtcPrice(ride)
        }
    }

    Map addNewRide(Ride ride) {
        ride.state = 'active'

        // TODO: Calculate price

        rideDao.add(ride)
        [ride: ride, status: 'OK']
    }

    List<Ride> getActiveRides() {
        rideDao.getActiveRides()
    }*/
}
