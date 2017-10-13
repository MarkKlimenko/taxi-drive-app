package com.markklim.taxi.drive.app.service

import com.datastax.driver.core.querybuilder.QueryBuilder
import com.datastax.driver.core.querybuilder.Select
import com.datastax.driver.core.utils.UUIDs
import com.markklim.taxi.drive.app.model.User
import com.markklim.taxi.drive.app.model.UserFields
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.cassandra.core.CassandraTemplate
import org.springframework.stereotype.Service

import java.time.LocalDateTime

@Service
class UserService {

    @Autowired
    private CassandraTemplate cassandraTemplate

    void add(User userRequest) {
        cassandraTemplate.insert(editUser(userRequest))
    }

    private User editUser(User user) {
        LocalDateTime now = LocalDateTime.now()
        if (user.userId.isEmpty()) {
            user.userId = UUIDs.timeBased().toString()
            user.loginTime = now
        }
        user.dateIn = now
        user
    }

    List<User> getByEmail(String email) {
        Select select = QueryBuilder.select().from(UserFields.TABLE)
        select.where(QueryBuilder.eq(UserFields.EMAIL, email))
        executeQuery(select)
    }

    List<User> getAll() {
        Select select = QueryBuilder.select().from(UserFields.TABLE)
        executeQuery(select)
    }

    protected List<User> executeQuery(Select select) {
        cassandraTemplate.select(select, User.class)
    }
}
