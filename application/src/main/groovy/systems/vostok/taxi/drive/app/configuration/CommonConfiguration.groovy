package systems.vostok.taxi.drive.app.configuration

import org.hibernate.search.jpa.FullTextEntityManager
import org.hibernate.search.jpa.Search
import org.springframework.cache.annotation.EnableCaching
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import systems.vostok.taxi.drive.app.dao.repository.CustomBasicRepository
import systems.vostok.tda.service.DistrictMapperService

import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@Configuration
@EnableCaching
@EnableJpaRepositories(basePackages = 'systems.vostok.taxi.drive.app.dao', repositoryBaseClass = CustomBasicRepository.class)
class CommonConfiguration {
    @PersistenceContext
    private EntityManager entityManager

    @Bean
    DistrictMapperService districtMapperService() {
        new DistrictMapperService()
    }

    @Bean
    FullTextEntityManager fullTextEntityManager() {
        entityManager.getEntityManagerFactory()
                .createEntityManager()
                .with(Search.&getFullTextEntityManager)
                .with { createIndexer().startAndWait(); it }
    }
}
