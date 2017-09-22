package com.markklim.taxi.drive.app.configuration

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.springframework.data.cassandra.config.java.AbstractCassandraConfiguration

@Configuration
class DatabaseConfiguration extends AbstractCassandraConfiguration {

    @Value('${cassandra.host}') String host
    @Value('${cassandra.port}') Integer port
    @Value('${cassandra.keyspace}') String keyspace

    @Override
    String getContactPoints() { host }

    @Override
    protected int getPort() { port }

    @Override
    String getKeyspaceName() { keyspace }
}
