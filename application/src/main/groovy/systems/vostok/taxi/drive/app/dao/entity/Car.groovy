package systems.vostok.taxi.drive.app.dao.entity

import groovy.transform.Canonical
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity
@Table(name = 'cars')
@Canonical
@EqualsAndHashCode(includes = ['id'])
@ToString(includeNames = true, includeFields = true)
class Car {
    @Id
    @GeneratedValue(generator = 'ID_GENERATOR')
    // TODO: Get rid of deprecated SequenceGenerator
    @SequenceGenerator(name = 'ID_GENERATOR', sequenceName = 'seq_global')
    Long id

    @NotNull
    String call

    @NotNull
    String number

    @NotNull
    String model
}
