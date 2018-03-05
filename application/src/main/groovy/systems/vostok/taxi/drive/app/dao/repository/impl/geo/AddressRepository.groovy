package systems.vostok.taxi.drive.app.dao.repository.impl.geo

import org.springframework.stereotype.Repository
import systems.vostok.taxi.drive.app.dao.entity.Address
import systems.vostok.taxi.drive.app.dao.repository.BasicRepository

import static systems.vostok.taxi.drive.app.util.constant.SqlEntities.ADDRESS

@Repository
interface AddressRepository extends BasicRepository<Address, String> {
    String entityType = ADDRESS
}
