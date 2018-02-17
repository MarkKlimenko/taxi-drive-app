package systems.vostok.taxi.drive.app.dao.entity

import groovy.transform.Canonical
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.SequenceGenerator
import javax.persistence.Table

@Entity
@Table(name = 'cars')
@Canonical
@EqualsAndHashCode(includes = ['id'])
@ToString(includeNames = true, includeFields = true)
class Car {
    @Id
    @GeneratedValue(generator = 'ID_GENERATOR')
    @SequenceGenerator(name = 'ID_GENERATOR', sequenceName = 'seq_global')
    Long id

    String number
    String model
}
