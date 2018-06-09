package systems.vostok.taxi.drive.app.dao.entity.geo

import systems.vostok.taxi.drive.app.dao.ObjectCreator

import javax.persistence.Id
import javax.persistence.MappedSuperclass

@MappedSuperclass
abstract class GeoEntity implements ObjectCreator {
    @Id
    String id
}
