package systems.vostok.taxi.drive.app.dao.repository.impl

import org.springframework.data.cassandra.repository.CassandraRepository
import systems.vostok.taxi.drive.app.dao.entity.ContextMessage

interface ContextMessageToEntityRepository extends CassandraRepository<ContextMessage, UUID> {
}
