package systems.vostok.taxi.drive.app.component

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import systems.vostok.taxi.drive.app.dao.entity.Client
import systems.vostok.taxi.drive.app.dao.entity.Ride
import systems.vostok.taxi.drive.app.dao.repository.sql.UniversalCrudRepository
import systems.vostok.taxi.drive.app.dao.repository.sql.impl.PriceCtcRepository
import systems.vostok.taxi.drive.app.dao.repository.sql.impl.PriceDtdRepository

@Component
class PriceFormer {
    @Autowired
    PriceCtcRepository priceCtcRepository

    @Autowired
    PriceDtdRepository priceDtdRepository

    @Autowired
    UniversalCrudRepository universalCrudRepository

    @Autowired
    DistrictMatcher districtMatcher

    @Autowired
    EntityMatcher entityMatcher


    static final Double VIP_DISCOUNT = 0.2
    static final Double FREE_DISCOUNT = 1
    static final Double ZERO_DISCOUNT = 0

    Integer calculateDtdPrice(Ride ride) {
        Double discount = calculateDiscount(ride.client)

        if (discount == FREE_DISCOUNT) {
            0
        } else {
            String districtFrom = districtMatcher.getDistrictId(ride.fromAddress)
            String districtTo = districtMatcher.getDistrictId(ride.toAddress)
            priceDao.getDistrictsRidePrice(districtFrom, districtTo) * (1 - discount)
        }
    }

    Integer calculateCtcPrice(Ride ride) {
        priceCtcRepository.getByCityFromAndCityTo(entityMatcher.getCityId(ride.fromAddress.city),
                entityMatcher.getCityId(ride.toAddress.city))
    }

    Boolean isRideFree(Integer ridesAmount) {
        (settingDao.getValue('ride_free') as Integer)
                .collect { (ridesAmount + 1) % it == 0 }
                .first()
    }

    private Double calculateDiscount(String clientLogin) {
        Client client = clientDao.getByLogin(clientLogin)
        if (client) {
            if (isRideFree(client.ridesAmount)) {
                return FREE_DISCOUNT
            } else if (client.clientType == 'VIP') {
                return VIP_DISCOUNT
            }
        }
        ZERO_DISCOUNT
    }
}
