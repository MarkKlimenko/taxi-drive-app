package systems.vostok.taxi.drive.app.dao.entity.geo

import groovy.transform.Canonical
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString
import systems.vostok.taxi.drive.app.dao.ObjectCreator

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = 'countries')
@Canonical
@EqualsAndHashCode(includes = ['id'])
@ToString(includeNames = true, includeFields = true)
class Country extends GeoEntity {
    String name
}