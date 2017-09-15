package com.markklim.taxi.drive.app.service

import com.markklim.taxi.drive.app.model.Person
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.cassandra.core.CassandraTemplate
import org.springframework.stereotype.Service

@Service
class PersonService {
    @Autowired
    private CassandraTemplate cassandraTemplate

    void addPerson() {
        cassandraTemplate.insert(new Person('login','name'))
    }
}
