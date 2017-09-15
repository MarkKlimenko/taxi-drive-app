package com.markklim.taxi.drive.app.configuration

import org.springframework.context.annotation.Configuration
import org.springframework.data.cassandra.config.java.AbstractCassandraConfiguration

@Configuration
class DatabaseConfiguration extends AbstractCassandraConfiguration {


    String getContactPoints() {
        return "localhost"
    }

    @Override
    protected int getPort() {
        9042
    }

    String getKeyspaceName() {
        'taxidrive'
    }
}
