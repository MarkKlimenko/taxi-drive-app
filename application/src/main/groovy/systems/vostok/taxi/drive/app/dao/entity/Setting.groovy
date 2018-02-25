package systems.vostok.taxi.drive.app.dao.entity

import groovy.transform.Canonical
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = 'settings')
@Canonical
@EqualsAndHashCode(includes = ['setting'])
@ToString(includeNames = true, includeFields = true)
class Setting {
    @Id
    String setting
    String value

    static interface Constants {
        String SETTING_RIDE_FREE = 'ride_free'
    }
}
