package systems.vostok.taxi.drive.app.dao.impl

import com.datastax.driver.core.querybuilder.Select
import systems.vostok.taxi.drive.app.dao.entity.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.cassandra.core.CassandraTemplate
import org.springframework.stereotype.Component

import java.time.LocalDateTime

@Component
class UserDao {

    @Autowired
    private CassandraTemplate cassandraTemplate

    void add(User userRequest) {
        cassandraTemplate.insert(editUser(userRequest))
    }

    private User editUser(User user) {
        if (!user.userId) {
            user.dateIn = LocalDateTime.now()
        }
        user
    }

    List<User> getByEmail(String email) {
        null
    }

    List<User> getAll() {
        null
    }

    protected List<User> executeQuery(Select select) {
        cassandraTemplate.select(select, User.class)
    }
}
