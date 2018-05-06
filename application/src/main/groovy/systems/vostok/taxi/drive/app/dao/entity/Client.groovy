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
import javax.validation.constraints.PositiveOrZero


@Entity
@Table(name = 'clients')
@Canonical
@EqualsAndHashCode(includes = ['clientLogin'])
@ToString(includeNames = true, includeFields = true)
class Client {
    @Id
    @NotNull
    // TODO: Add custom validation for login phone number
    String login

    @NotNull
    String firstName
    String lastName

    @NotNull
    @PositiveOrZero
    Integer ridesAmount

    String type

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Transient
    Boolean rideFree

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Transient
    List<Ride> previousRides

    static interface Constants {
        String CLIENT_TYPE_VIP = 'vip'
    }
}
