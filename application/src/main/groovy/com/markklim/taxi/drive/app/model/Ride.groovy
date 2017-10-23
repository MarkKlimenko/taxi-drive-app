package com.markklim.taxi.drive.app.model

import com.datastax.driver.core.DataType
import com.markklim.taxi.drive.app.api.adapter.LocalDateTimeAdapter
import groovy.transform.Canonical
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString
import org.springframework.data.cassandra.mapping.CassandraType
import org.springframework.data.cassandra.mapping.PrimaryKey
import org.springframework.data.cassandra.mapping.Table

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter
import java.time.LocalDateTime

@Table('ride')
@Canonical
@EqualsAndHashCode(includes = ['id'])
@ToString(includeNames = true, includeFields = true, excludes = 'id')
class Ride {

    @PrimaryKey
    UUID id = UUID.randomUUID()

    String clientLogin

    @CassandraType(type = DataType.Name.UDT, userTypeName = "address")
    Address fromAddress

    @CassandraType(type = DataType.Name.UDT, userTypeName = "address")
    Address toAddress

    @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
    LocalDateTime dateIn

    @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
    LocalDateTime rideIn

    @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
    LocalDateTime rideOut

    String carId

    Integer adultInCar

    Integer childrenInCar

    Integer price

    String state
}
