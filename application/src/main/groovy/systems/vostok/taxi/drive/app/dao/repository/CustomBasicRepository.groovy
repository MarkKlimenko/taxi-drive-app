package systems.vostok.taxi.drive.app.dao.repository

import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.data.jpa.repository.support.JpaEntityInformation
import org.springframework.data.jpa.repository.support.SimpleJpaRepository
import org.springframework.transaction.annotation.Transactional
import org.springframework.util.Assert
import systems.vostok.taxi.drive.app.dao.repository.criteria.QueryFilter
import systems.vostok.taxi.drive.app.dao.repository.criteria.QueryPagination
import systems.vostok.taxi.drive.app.dao.repository.criteria.QuerySorter

import javax.persistence.EntityManager
import javax.persistence.TypedQuery
import javax.persistence.criteria.CriteriaBuilder
import javax.persistence.criteria.CriteriaQuery
import javax.persistence.criteria.Root

class CustomBasicRepository<T, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements BasicRepository<T, ID> {

    private static final String ID_MUST_NOT_BE_NULL = 'The given id must not be null!'

    private final EntityManager entityManager
    private final JpaEntityInformation entityInformation

    CustomBasicRepository(JpaEntityInformation entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager)

        this.entityManager = entityManager
        this.entityInformation = entityInformation
    }

    @Override
    T convertToEntityType(Object entity) {
        Class targetClass = entityInformation.javaType

        if (!targetClass.isInstance(entity)) {
            return entityInformation.javaType
                    .newInstance(entity) as T
        }
        entity as T
    }

    @Override
    ID convertToIdType(String entityId) {
        entityInformation.idAttribute
                .javaType
                .newInstance(entityId)
    }

    @Override
    List<T> findByCriteria(List<QueryFilter> filter, List<QuerySorter> sorter, QueryPagination pagination) {
        createCriteriaQuery(filter, sorter, pagination).getResultList()
    }

    @Override
    @Transactional
    void delete(ID id) {
        Assert.notNull(id, ID_MUST_NOT_BE_NULL)

        findOne(id).with(this.&checkEntityExistence)
                .with(this.&delete)
    }

    private T checkEntityExistence(T entity) {
        if (!entity) {
            throw new EmptyResultDataAccessException(
                    String.format("No %s entity with such id exists!", entityInformation.javaType), 1)
        }
        entity
    }

    private TypedQuery<T> createCriteriaQuery(List<QueryFilter> filter, List<QuerySorter> sorter, QueryPagination pagination) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder()
        CriteriaQuery<T> query = builder.createQuery(entityInformation.javaType)
        Root<T> root = query.from(entityInformation.javaType)

        Map<String, Closure> filterMapper = [
                '='          : { builder.equal(root.get(it.parameter), it.value) },
                '!='         : { builder.notEqual(root.get(it.parameter), it.value) },
                '>'          : { builder.greaterThan(root.get(it.parameter), it.value) },
                '>='         : { builder.greaterThanOrEqualTo(root.get(it.parameter), it.value) },
                '<'          : { builder.lessThan(root.get(it.parameter), it.value) },
                '<='         : { builder.lessThanOrEqualTo(root.get(it.parameter), it.value) },
                'IS NULL'    : { builder.isNull(root.get(it.parameter)) },
                'IS NOT NULL': { builder.isNotNull(root.get(it.parameter)) },
                'LIKE'       : { builder.like(root.get(it.parameter), it.value) }
        ]

        Map<String, Closure> sortMapper = [
                'ASC' : { builder.asc(root.get(it.parameter)) },
                'DESC': { builder.desc(root.get(it.parameter)) }
        ]

        def addFilter = { CriteriaQuery filteredQuery ->
            filter.collect { filterMapper[it.operator].call(it) }
                    .with { add(builder.conjunction()); it }
                    .with { filteredQuery.where(*it) }
        }

        def addSorter = { CriteriaQuery sortedQuery ->
            sorter.collect { sortMapper[it.order].call(it) }
                    .with { sortedQuery.orderBy(*it) }
        }

        def addPageable = { TypedQuery pageableQuery ->
            if (pagination) {
                pageableQuery.setFirstResult(pagination.firstResult)
                pageableQuery.setMaxResults(pagination.maxResults)
            }
            pageableQuery
        }

        query.with(addFilter)
                .with(addSorter)
                .with(entityManager.&createQuery)
                .with(addPageable)
    }
}

