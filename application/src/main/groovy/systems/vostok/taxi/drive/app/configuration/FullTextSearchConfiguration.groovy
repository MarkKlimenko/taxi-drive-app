package systems.vostok.taxi.drive.app.configuration

import org.hibernate.search.jpa.FullTextEntityManager
import org.hibernate.search.jpa.Search
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@Configuration
class FullTextSearchConfiguration {
    @PersistenceContext
    private EntityManager entityManager

    @Bean
    FullTextEntityManager fullTextEntityManager() {
        entityManager.getEntityManagerFactory()
                .createEntityManager()
                .with(Search.&getFullTextEntityManager)
                .with { createIndexer().startAndWait(); it }
    }
}
