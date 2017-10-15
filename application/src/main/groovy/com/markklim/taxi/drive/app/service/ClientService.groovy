package com.markklim.taxi.drive.app.service

import com.datastax.driver.core.querybuilder.QueryBuilder
import com.datastax.driver.core.querybuilder.Select
import com.markklim.taxi.drive.app.model.Client
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.cassandra.core.CassandraTemplate
import org.springframework.stereotype.Service

@Service
class ClientService {
    @Autowired
    private CassandraTemplate cassandraTemplate

    List<Client> getAll() {
        Select select = QueryBuilder.select().from('client')
        executeQuery(select)
    }

    List<Client> getByLogin(String login) {
        Select select = QueryBuilder.select().from('client')
        select.where(QueryBuilder.eq('clientLogin', login))

        executeQuery(select)
    }

    void add(Client client) {
        cassandraTemplate.insert(client)
    }

    protected List<Client> executeQuery(Select select) {
        cassandraTemplate.select(select, Client.class)
    }


}
