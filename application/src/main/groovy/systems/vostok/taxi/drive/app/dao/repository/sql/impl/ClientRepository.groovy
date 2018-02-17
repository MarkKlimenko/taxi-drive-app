package systems.vostok.taxi.drive.app.dao.repository.sql.impl

import org.springframework.stereotype.Repository
import systems.vostok.taxi.drive.app.dao.entity.Client
import systems.vostok.taxi.drive.app.dao.repository.sql.BasicRepository

import static systems.vostok.taxi.drive.app.util.constant.SqlEntities.CLIENT

@Repository
interface ClientRepository extends BasicRepository<Client, String> {
    String entityType = CLIENT
}
