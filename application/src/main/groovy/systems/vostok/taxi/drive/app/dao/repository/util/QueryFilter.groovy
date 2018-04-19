package systems.vostok.taxi.drive.app.dao.repository.util

import groovy.transform.Canonical

@Canonical
class QueryFilter {
    String parameter
    String operator
    Object value
}
