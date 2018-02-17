package systems.vostok.taxi.drive.app.dao.repository.sql.impl

import org.springframework.stereotype.Repository
import systems.vostok.taxi.drive.app.dao.entity.PriceCtc
import systems.vostok.taxi.drive.app.dao.repository.sql.BasicRepository

import static systems.vostok.taxi.drive.app.util.constant.SqlEntities.PRICE_CTC

@Repository
interface PriceCtcRepository extends BasicRepository<PriceCtc, Long> {
    String entityType = PRICE_CTC
}
