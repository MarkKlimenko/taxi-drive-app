package systems.vostok.taxi.drive.app.dao.entity

import com.fasterxml.jackson.annotation.JsonInclude
import groovy.transform.Canonical
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table
import javax.persistence.Transient

@Entity
@Table(name = 'clients')
@Canonical
@EqualsAndHashCode(includes = ['clientLogin'])
@ToString(includeNames = true, includeFields = true)
class Client {
    @Id
    String clientLogin

    String firstName
    String lastName
    Integer ridesAmount
    String clientType

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Transient
    Boolean rideFree

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Transient
    List<Ride> previousRides
}
