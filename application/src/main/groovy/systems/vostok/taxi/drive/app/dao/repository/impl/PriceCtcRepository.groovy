package systems.vostok.taxi.drive.app.dao.repository.impl

import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import systems.vostok.taxi.drive.app.dao.entity.PriceCtc
import systems.vostok.taxi.drive.app.dao.repository.BasicRepository

import static systems.vostok.taxi.drive.app.util.constant.SqlEntities.PRICE_CTC

@Repository
interface PriceCtcRepository extends BasicRepository<PriceCtc, Integer> {
    String entityType = PRICE_CTC

    @Query('SELECT p.price FROM PriceCtc p WHERE p.id = :id')
    Integer findPrice(@Param('id') Integer id)
}
