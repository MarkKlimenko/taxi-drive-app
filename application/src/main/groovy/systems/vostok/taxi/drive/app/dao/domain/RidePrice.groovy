package systems.vostok.taxi.drive.app.dao.domain

import groovy.transform.Canonical

@Canonical
class RidePrice {
    String price

    RidePrice(Integer price) {
        this.price = price as String
    }
}
