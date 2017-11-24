package com.markklim.taxi.drive.app.dao.entity

import com.markklim.taxi.drive.app.api.adapter.LocalDateTimeAdapter
import groovy.transform.Canonical
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString
import org.springframework.data.cassandra.mapping.PrimaryKey
import org.springframework.data.cassandra.mapping.Table

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter
import java.time.LocalDateTime

@Table(UserFields.TABLE)
@Canonical
@EqualsAndHashCode(includes = [UserFields.ID])
@ToString(includeNames = true, includeFields = true)
class User {

    @PrimaryKey
    String userId

    String firstName

    String lastName

    String email

    @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
    LocalDateTime dateIn

    @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
    LocalDateTime loginTime

}

class UserFields {
    public static final TABLE = 'user'
    public static final ID = 'userId'
    public static final FIRST_NAME = 'firstName'
    public static final LAST_NAME = 'lastName'
    public static final EMAIL = 'email'
}