package systems.vostok.taxi.drive.app.component

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import systems.vostok.taxi.drive.app.dao.entity.Client
import systems.vostok.taxi.drive.app.dao.entity.Ride
import systems.vostok.taxi.drive.app.dao.repository.impl.ClientRepository
import systems.vostok.taxi.drive.app.dao.repository.impl.PriceCtcRepository
import systems.vostok.taxi.drive.app.dao.repository.impl.PriceDtdRepository
import systems.vostok.taxi.drive.app.dao.repository.impl.SettingRepository
import systems.vostok.taxi.drive.app.util.CommonUtil

import static systems.vostok.taxi.drive.app.dao.entity.Setting.Constants.SETTING_RIDE_FREE

@Component
class PriceFormer {
    @Autowired
    PriceCtcRepository priceCtcRepository

    @Autowired
    PriceDtdRepository priceDtdRepository

    @Autowired
    SettingRepository settingRepository

    @Autowired
    ClientRepository clientRepository


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
            String distFrom = districtMatcher.getDistrictId(ride.rawFromAddress)
            String distTo = districtMatcher.getDistrictId(ride.rawToAddress)

            CommonUtil.generateId(distFrom, distTo)
                    .with(priceDtdRepository.&findPrice)
                    ?.multiply(1 - discount)
        }
    }

    Integer calculateCtcPrice(Ride ride) {
        String cityFrom = entityMatcher.getCityId(ride.rawFromAddress.city)
        String cityTo =  entityMatcher.getCityId(ride.rawToAddress.city)

        CommonUtil.generateId(cityFrom, cityTo)
                .with(priceCtcRepository.&findPrice)
    }

    Boolean isRideFree(Integer ridesAmount) {
        (settingRepository.findValueBySetting(SETTING_RIDE_FREE) as Integer)
                .with { (ridesAmount + 1) % it == 0 }
    }

    private Double calculateDiscount(String clientLogin) {
        Client client = clientRepository.findOne(clientLogin)
        if (client) {
            if (isRideFree(client.ridesAmount)) {
                return FREE_DISCOUNT
            } else if (client.type == 'VIP') {
                return VIP_DISCOUNT
            }
        }
        ZERO_DISCOUNT
    }
}
