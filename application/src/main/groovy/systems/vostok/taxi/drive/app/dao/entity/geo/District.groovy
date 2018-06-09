package systems.vostok.taxi.drive.app.dao.entity.geo

import groovy.transform.Canonical
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString
import systems.vostok.taxi.drive.app.dao.ObjectCreator

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = 'districts')
@Canonical
@EqualsAndHashCode(includes = ['id'])
@ToString(includeNames = true, includeFields = true)
class District extends GeoEntity {
    String name
    String city
}