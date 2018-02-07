package systems.vostok.taxi.drive.app.dao.entity

import groovy.transform.Canonical
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = 'system_property')
@Canonical
@EqualsAndHashCode(includes = ['property'])
@ToString(includeNames = true, includeFields = true)
class SystemProperty {
    @Id
    String property

    String value
}
