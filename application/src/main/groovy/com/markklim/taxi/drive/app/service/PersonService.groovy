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
        Select select = QueryBuilder.select().from("person")
        executeQuery(select)
        //cassandraTemplate.select('select * from person', Person.class)
    }

    List<Person> getByLogin(String login) {
        Select select = QueryBuilder.select().from("person")
                        select.where(QueryBuilder.eq("login", login))

        executeQuery(select)
    }

    void add() {
        cassandraTemplate.insert(new Person('login', 'firstName', 'lastName'))
    }

    protected List<Person> executeQuery (Select select){
        cassandraTemplate.select(select, Person.class)
    }


}
