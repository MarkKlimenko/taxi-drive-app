package systems.vostok.taxi.drive.app.dao.repository

import org.hibernate.search.jpa.FullTextEntityManager
import org.hibernate.search.jpa.Search
import org.hibernate.search.query.dsl.QueryBuilder
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.data.jpa.repository.support.JpaEntityInformation
import org.springframework.data.jpa.repository.support.SimpleJpaRepository
import org.springframework.transaction.annotation.Transactional
import org.springframework.util.Assert
import systems.vostok.taxi.drive.app.dao.domain.util.QueryFilter
import systems.vostok.taxi.drive.app.dao.domain.util.QueryPagination
import systems.vostok.taxi.drive.app.dao.domain.util.QuerySorter
import systems.vostok.taxi.drive.app.dao.domain.util.SearchParameters

import javax.persistence.EntityManager
import javax.persistence.Query
import javax.persistence.TypedQuery
import javax.persistence.criteria.CriteriaBuilder
import javax.persistence.criteria.CriteriaQuery
import javax.persistence.criteria.Root

class CustomBasicRepository<T, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements BasicRepository<T, ID> {
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
    T getByEntityId(T entity) {
        findById(entity."${entityInformation.idAttribute.name}" as ID)
                .orElseGet(null)
    }

    @Override
    List<T> findByCriteria(List<QueryFilter> filter, List<QuerySorter> sorter, QueryPagination pagination) {
        createCriteriaQuery(filter, sorter, pagination).getResultList()
    }

    @Override
    @Transactional
    List<T> search(SearchParameters parameters) {
        createSearchQuery(parameters).getResultList()
    }

    // TODO: Create unit tests (check queryString & namedParameters)
    // TODO: Create state/integration tests
    private Query createCriteriaQuery(List<QueryFilter> filter, List<QuerySorter> sorter, QueryPagination pagination) {
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
                'LIKE'       : { builder.like(root.get(it.parameter), it.value) },
                'IN'         : { builder.isTrue(root.get(it.parameter).in(it.value)) },
                'NOT IN'     : { builder.isTrue(root.get(it.parameter).in(it.value).not()) },
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

    // TODO: Create extended search implementation
    private Query createSearchQuery(SearchParameters parameters) {
        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager)

        QueryBuilder queryBuilder = fullTextEntityManager.getSearchFactory()
                .buildQueryBuilder()
                .forEntity(entityInformation.javaType)
                .get()

        org.apache.lucene.search.Query query = queryBuilder.simpleQueryString()
                .onFields(*parameters.fields)
                .withAndAsDefaultOperator()
                .matching(parameters.request)
                .createQuery()

        fullTextEntityManager.createFullTextQuery(query)
    }
}

