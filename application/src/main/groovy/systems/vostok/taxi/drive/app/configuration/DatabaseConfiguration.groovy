package systems.vostok.taxi.drive.app.configuration

import com.zaxxer.hikari.HikariDataSource
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

import javax.annotation.PostConstruct
import javax.sql.DataSource
import java.sql.SQLException

@Configuration
@Slf4j
class DatabaseConfiguration {
    // TODO: Get rid of properties
    @Value('${db.url}')
    String dbUrl

    @Value('${db.username}')
    String dbUsername

    @Value('${db.password}')
    String dbPassword

    @Value('${spring.jpa.hibernate.ddl-auto}')
    String ddlAuto

    @Bean
    DataSource dataSource() {
        new HikariDataSource().with {
            jdbcUrl = dbUrl
            username = dbUsername
            password = dbPassword
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
