package systems.vostok.taxi.drive.app.dao.repository.sql.impl

import org.springframework.stereotype.Repository
import systems.vostok.taxi.drive.app.dao.entity.PriceDtd
import systems.vostok.taxi.drive.app.dao.repository.sql.BasicRepository

import static systems.vostok.taxi.drive.app.util.constant.SqlEntities.PRICE_DTD

@Repository
interface PriceDtdRepository extends BasicRepository<PriceDtd, Long> {
    String entityType = PRICE_DTD
}
