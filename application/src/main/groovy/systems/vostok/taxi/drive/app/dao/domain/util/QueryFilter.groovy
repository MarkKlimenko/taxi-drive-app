package systems.vostok.taxi.drive.app.dao.domain.util

import groovy.transform.Canonical

@Canonical
class QueryFilter {
    String parameter
    String operator
    Object value
}
