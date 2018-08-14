package systems.vostok.taxi.drive.app.dao.entity.geo

import groovy.transform.AutoClone
import groovy.transform.Canonical
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = 'cities')
@Canonical
@EqualsAndHashCode(includes = ['id'])
@ToString(includeNames = true, includeFields = true)
@AutoClone
class City extends GeoEntity {
    String name
    String state
}