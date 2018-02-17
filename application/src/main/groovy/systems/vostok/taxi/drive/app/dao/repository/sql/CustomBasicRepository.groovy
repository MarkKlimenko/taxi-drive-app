package systems.vostok.taxi.drive.app.dao.repository.sql

import org.springframework.data.jpa.repository.support.JpaEntityInformation
import org.springframework.data.jpa.repository.support.SimpleJpaRepository

import javax.persistence.EntityManager

class CustomBasicRepository<T, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements BasicRepository<T, ID> {

    private final EntityManager entityManager
    private final Class<T> entityClass

    CustomBasicRepository(JpaEntityInformation entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager)

        this.entityManager = entityManager
        this.entityClass = entityInformation.javaType
    }

    T convertToEntityType(Map entity) {
        entityClass.newInstance(entity)
    }
}
