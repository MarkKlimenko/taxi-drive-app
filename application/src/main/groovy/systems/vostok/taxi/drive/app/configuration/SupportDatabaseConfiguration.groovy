package systems.vostok.taxi.drive.app.configuration

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.cassandra.config.CassandraClusterFactoryBean
import org.springframework.data.cassandra.config.java.AbstractCassandraConfiguration
import org.springframework.data.cassandra.mapping.BasicCassandraMappingContext
import org.springframework.data.cassandra.mapping.CassandraMappingContext

@Configuration
@ConfigurationProperties(prefix = 'db.support')
class SupportDatabaseConfiguration extends AbstractCassandraConfiguration {
    String keyspaceName
    String contactPoints
    int port

    @Bean
    @Override
    CassandraClusterFactoryBean cluster() {
        new CassandraClusterFactoryBean().with {
            setContactPoints(contactPoints)
            setPort(port)
            it
        }
    }

    @Bean
    @Override
    CassandraMappingContext cassandraMapping() {
        new BasicCassandraMappingContext()
    }
}
