package com.markklim.taxi.drive.app.service

import com.datastax.driver.core.querybuilder.QueryBuilder
import com.datastax.driver.core.querybuilder.Select
import com.markklim.taxi.drive.app.model.Person
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.cassandra.core.CassandraTemplate
import org.springframework.stereotype.Service

@Service
class PersonService {
    @Autowired
    private CassandraTemplate cassandraTemplate

    List<Person> getAll() {
        Select select = QueryBuilder.select().from('person')
        executeQuery(select)
    }

    List<Person> getByLogin(String login) {
        Select select = QueryBuilder.select().from('person')
        select.where(QueryBuilder.eq('personLogin', login))

        executeQuery(select)
    }

    void add(Person person) {
        cassandraTemplate.insert(person)
    }

    protected List<Person> executeQuery(Select select) {
        cassandraTemplate.select(select, Person.class)
    }


}
