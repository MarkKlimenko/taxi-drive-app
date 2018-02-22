package systems.vostok.taxi.drive.app.dao.repository.sql

import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.data.jpa.repository.support.JpaEntityInformation
import org.springframework.data.jpa.repository.support.SimpleJpaRepository
import org.springframework.transaction.annotation.Transactional
import org.springframework.util.Assert

import javax.persistence.EntityManager

class CustomBasicRepository<T, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements BasicRepository<T, ID> {

    private static final String ID_MUST_NOT_BE_NULL = 'The given id must not be null!'

    private final EntityManager entityManager
    private final JpaEntityInformation entityInformation

    CustomBasicRepository(JpaEntityInformation entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager)

        this.entityManager = entityManager
        this.entityInformation = entityInformation
    }

    T convertToEntityType(Object entity) {
        Class targetClass = entityInformation.javaType

        if(!targetClass.isInstance(entity)) {
            return entityInformation.javaType
                    .newInstance(entity) as T
        }
        entity as T
    }

    ID convertToIdType(String entityId) {
        entityInformation.idAttribute
                .javaType
                .newInstance(entityId)
    }

    @Transactional
    void delete(ID id) {
        Assert.notNull(id, ID_MUST_NOT_BE_NULL)

        findOne(id).with(this.&checkEntityExistence)
                .with(this.&delete)
    }

    T checkEntityExistence(T entity) {
        if (!entity) {
            throw new EmptyResultDataAccessException(
                    String.format("No %s entity with such id exists!", entityInformation.javaType), 1)
        }
        entity
    }
}

