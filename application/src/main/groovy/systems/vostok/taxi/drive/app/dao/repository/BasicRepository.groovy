package systems.vostok.taxi.drive.app.dao.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.NoRepositoryBean
import systems.vostok.taxi.drive.app.dao.repository.util.QueryFilter
import systems.vostok.taxi.drive.app.dao.repository.util.QueryPagination
import systems.vostok.taxi.drive.app.dao.repository.util.QuerySorter
import systems.vostok.taxi.drive.app.dao.repository.util.SearchParameters

@NoRepositoryBean
interface BasicRepository<T, ID extends Serializable> extends JpaRepository<T, ID> {
    T convertToEntityType(Object entity)
    ID convertToIdType(String entity)
    List<T> findByCriteria(List<QueryFilter> filter, List<QuerySorter> sorter, QueryPagination pagination)
    List<T> search(SearchParameters parameters)
}
