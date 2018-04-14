package systems.vostok.taxi.drive.app.dao.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.NoRepositoryBean
import systems.vostok.taxi.drive.app.dao.repository.criteria.QueryFilter
import systems.vostok.taxi.drive.app.dao.repository.criteria.QueryPagination
import systems.vostok.taxi.drive.app.dao.repository.criteria.QuerySorter

@NoRepositoryBean
interface BasicRepository<T, ID extends Serializable> extends JpaRepository<T, ID> {
    T convertToEntityType(Object entity)
    ID convertToIdType(String entity)
    List<T> findByCriteria(List<QueryFilter> filter, List<QuerySorter> sorter, QueryPagination pagination)
}
