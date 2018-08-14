package systems.vostok.taxi.drive.app.dao.domain.query

import groovy.transform.Canonical

@Canonical
class QueryFilter {
    String parameter
    String operator
    Object value
}
