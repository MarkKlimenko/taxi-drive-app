package systems.vostok.taxi.drive.app.dao.repository.sql

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.NoRepositoryBean

@NoRepositoryBean
interface BasicRepository<T, ID extends Serializable> extends JpaRepository<T, ID> {
    T convertToEntityType(Object entity)
    ID convertToIdType(String entity)
}
