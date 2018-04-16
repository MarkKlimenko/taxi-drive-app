package systems.vostok.taxi.drive.app.dao.entity

import com.fasterxml.jackson.annotation.JsonInclude
import groovy.transform.Canonical
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table
import javax.persistence.Transient
import javax.validation.constraints.NotNull


@Entity
@Table(name = 'clients')
@Canonical
@EqualsAndHashCode(includes = ['clientLogin'])
@ToString(includeNames = true, includeFields = true)
class Client {
    @Id
    @NotNull
    String login

    @NotNull
    String firstName
    String lastName

    @NotNull
    Integer ridesAmount

    String type

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Transient
    Boolean rideFree

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Transient
    List<Ride> previousRides
}
