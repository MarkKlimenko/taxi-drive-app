package systems.vostok.taxi.drive.app.dao.repository.criteria

import groovy.transform.Canonical

@Canonical
class QueryFilter {
    String parameter
    String operator
    Object value
}
