package systems.vostok.taxi.drive.app.dao.repository.impl

import org.springframework.stereotype.Repository
import systems.vostok.taxi.drive.app.dao.entity.Client
import systems.vostok.taxi.drive.app.dao.repository.BasicRepository

import static systems.vostok.taxi.drive.app.util.constant.SqlEntity.CLIENT

@Repository
interface ClientRepository extends BasicRepository<Client, String> {
    String entityType = CLIENT
}
