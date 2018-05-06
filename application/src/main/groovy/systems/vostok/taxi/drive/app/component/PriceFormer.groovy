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

import static systems.vostok.taxi.drive.app.dao.entity.Setting.Constants.*
import static systems.vostok.taxi.drive.app.dao.entity.Client.Constants.*

// TODO: Add tests for all methods
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

    static final Double FREE_RIDE_DISCOUNT = 1
    static final Double FREE_RIDE_PRICE = 0

    Integer calculateDtdPrice(Ride ride) {
        Double discount = calculateDiscount(ride.client)

        if (discount == FREE_RIDE_DISCOUNT) {
            FREE_RIDE_PRICE
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
        String cityTo = entityMatcher.getCityId(ride.rawToAddress.city)

        CommonUtil.generateId(cityFrom, cityTo)
                .with(priceCtcRepository.&findPrice)
    }

    Boolean isRideFree(Integer ridesAmount) {
        settingRepository.findValueBySetting(SETTING_RIDE_FREE)
                .with { it as Integer }
                .with { (ridesAmount + 1) % it == 0 }
    }

    private Double calculateDiscount(String clientLogin) {
        Client client = clientRepository.findOne(clientLogin)
        if (client) {
            if (isRideFree(client.ridesAmount)) {
                return settingRepository.findValueBySetting(SETTING_FREE_DISCOUNT) as Double
            } else if (client.type == CLIENT_TYPE_VIP) {
                return settingRepository.findValueBySetting(SETTING_VIP_DISCOUNT) as Double
            }
        }
        settingRepository.findValueBySetting(SETTING_ZERO_DISCOUNT) as Double
    }
}
