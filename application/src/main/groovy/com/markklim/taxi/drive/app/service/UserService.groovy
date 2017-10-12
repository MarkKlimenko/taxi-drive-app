package com.markklim.taxi.drive.app.service

import com.datastax.driver.core.querybuilder.QueryBuilder
import com.datastax.driver.core.querybuilder.Select
import com.datastax.driver.core.utils.UUIDs
import com.markklim.taxi.drive.app.model.User
import com.markklim.taxi.drive.app.model.UserDB
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.cassandra.core.CassandraTemplate
import org.springframework.stereotype.Service

import java.time.LocalDateTime

@Service
class UserService {

    @Autowired
    private CassandraTemplate cassandraTemplate

    void add(User userRequest) {
        User user = createUser(userRequest.firstName, userRequest.lastName, userRequest.email)
        cassandraTemplate.insert(user)
    }

    private User createUser(String firstName, String lastName, String email) {
        LocalDateTime now = LocalDateTime.now()
        new User(UUIDs.timeBased().toString(), firstName, lastName, email, now, now)
    }

    List<User> getByEmail(String email) {
        Select select = QueryBuilder.select().from(UserDB.TABLE)
        select.where(QueryBuilder.eq(UserDB.EMAIL, email))
        executeQuery(select)
    }

    List<User> getAll() {
        Select select = QueryBuilder.select().from(UserDB.TABLE)
        executeQuery(select)
    }

    protected List<User> executeQuery(Select select) {
        cassandraTemplate.select(select, User.class)
    }
}
