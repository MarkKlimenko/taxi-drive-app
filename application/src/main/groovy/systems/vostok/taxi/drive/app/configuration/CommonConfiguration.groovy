package systems.vostok.taxi.drive.app.configuration

import org.springframework.cache.annotation.EnableCaching
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import systems.vostok.taxi.drive.app.dao.repository.sql.BasicRepositoryImpl
import systems.vostok.tda.service.DistrictMapperService

@Configuration
@EnableCaching
@EnableJpaRepositories(basePackages = 'systems.vostok.taxi.drive.app.dao', repositoryBaseClass = BasicRepositoryImpl.class)
class CommonConfiguration {

    @Bean
    DistrictMapperService districtMapperService() {
        new DistrictMapperService()
    }
}
