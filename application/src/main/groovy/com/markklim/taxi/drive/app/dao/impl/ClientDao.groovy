package com.markklim.taxi.drive.app.dao.impl

import com.datastax.driver.core.querybuilder.QueryBuilder
import com.datastax.driver.core.querybuilder.Select
import com.markklim.taxi.drive.app.dao.UniversalDao
import com.markklim.taxi.drive.app.dao.entity.Client
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

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
        universalDao.insertSingle(client)
    }
}
