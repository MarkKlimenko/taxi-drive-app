package systems.vostok.taxi.drive.app.dao.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.NoRepositoryBean
import systems.vostok.taxi.drive.app.dao.domain.query.QueryFilter
import systems.vostok.taxi.drive.app.dao.domain.query.QueryPagination
import systems.vostok.taxi.drive.app.dao.domain.query.QuerySorter
import systems.vostok.taxi.drive.app.dao.domain.query.SearchParameters

@NoRepositoryBean
interface BasicRepository<T, ID extends Serializable> extends JpaRepository<T, ID> {
    T convertToEntityType(Object entity)
    ID convertToIdType(String entity)

    T getByEntityId(T entity)
    ID getEntityId(T entity)
    ID getEntityId(Map entity)

    T detach(T entity)

    List<T> findByCriteria(List<QueryFilter> filter, List<QuerySorter> sorter, QueryPagination pagination)
    List<T> search(SearchParameters parameters)
}
