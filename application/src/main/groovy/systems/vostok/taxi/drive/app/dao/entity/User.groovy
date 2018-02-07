package systems.vostok.taxi.drive.app.dao.entity

import systems.vostok.taxi.drive.app.api.adapter.LocalDateTimeAdapter
import groovy.transform.Canonical
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter
import java.time.LocalDateTime

@Entity
@Table(name = 'app_user')
@Canonical
@EqualsAndHashCode(includes = ['userId'])
@ToString(includeNames = true, includeFields = true)
class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long userId

    String firstName
    String lastName
    String email

    @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
    LocalDateTime dateIn

    @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
    LocalDateTime lastLoginTime
}
