package systems.vostok.taxi.drive.app.dao.entity

import groovy.transform.Canonical
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = 'setting')
@Canonical
@EqualsAndHashCode(includes = ['setting'])
@ToString(includeNames = true, includeFields = true)
class Setting {
    @Id
    String setting

    String value
}
