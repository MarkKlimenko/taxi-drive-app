package systems.vostok.taxi.drive.app.dao.repository.impl

import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import systems.vostok.taxi.drive.app.dao.entity.PriceDtd
import systems.vostok.taxi.drive.app.dao.repository.BasicRepository

import static systems.vostok.taxi.drive.app.util.constant.SqlEntities.PRICE_DTD

@Repository
interface PriceDtdRepository extends BasicRepository<PriceDtd, Integer> {
    String entityType = PRICE_DTD

    @Query('SELECT p.price FROM PriceDtd p WHERE p.id = :id')
    Integer findPrice(@Param('id') Integer id)
}
