package systems.vostok.taxi.drive.app.dao.repository.impl

import org.springframework.stereotype.Repository
import systems.vostok.taxi.drive.app.dao.entity.User
import systems.vostok.taxi.drive.app.dao.repository.BasicRepository

import static systems.vostok.taxi.drive.app.util.constant.SqlEntity.USER

@Repository
interface UserRepository extends BasicRepository<User, String> {
    String entityType = USER
}
