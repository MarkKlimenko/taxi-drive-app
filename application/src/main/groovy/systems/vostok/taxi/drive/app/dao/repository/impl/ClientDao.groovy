package systems.vostok.taxi.drive.app.dao.repository.impl

import com.datastax.driver.core.querybuilder.QueryBuilder
import com.datastax.driver.core.querybuilder.Select
import systems.vostok.taxi.drive.app.dao.repository.UniversalDao
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import systems.vostok.taxi.drive.app.dao.entity.Client

@Component
class ClientDao {
    @Autowired
    @Delegate
    UniversalDao universalDao

    List<Client> getAll() {
        Select select = QueryBuilder.select().from('client')
        selectAll(select, Client.class)
    }

    Client getByLogin(String login) {
        Select select = QueryBuilder.select().from('client')
        select.where(QueryBuilder.eq('clientLogin', login))

        selectSingle(select, Client.class)
    }

    void add(Client client) {
        insertSingle(client)
    }

    void delete(Client client) {
        deleteSingle(Client.class, client.clientLogin)
    }
}
