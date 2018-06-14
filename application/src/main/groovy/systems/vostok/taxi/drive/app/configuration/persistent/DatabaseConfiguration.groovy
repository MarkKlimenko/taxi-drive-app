package systems.vostok.taxi.drive.app.configuration.persistent

import com.zaxxer.hikari.HikariDataSource
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import systems.vostok.taxi.drive.app.dao.repository.CustomBasicRepository

import javax.annotation.PostConstruct
import javax.sql.DataSource
import java.sql.SQLException

@Configuration
@Slf4j
@EnableJpaRepositories(basePackages = 'systems.vostok.taxi.drive.app.dao', repositoryBaseClass = CustomBasicRepository.class)
@ConfigurationProperties(prefix = 'db')
class DatabaseConfiguration {
    String url
    String username
    String password

    @Value('${spring.jpa.hibernate.ddl-auto}')
    String ddlAuto

    @Bean
    DataSource dataSource() {
        new HikariDataSource().with {
            it.jdbcUrl = this.url
            it.username = this.username
            it.password = this.password
            it
        }
    }

    @PostConstruct
    void init() {
        if (ddlAuto == 'validate') {
            log.info 'Hibernate ddl-auto status: validate'
        } else {
            throw new SQLException('Hibernate ddl-auto status: ERROR')
        }
    }
}
