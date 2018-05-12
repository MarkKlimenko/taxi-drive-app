package systems.vostok.taxi.drive.app.dao.repository.util

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Component
import systems.vostok.taxi.drive.app.dao.repository.BasicRepository

import static systems.vostok.taxi.drive.app.util.constant.CacheName.REPOSITORY_RESOLVER

@Component
class RepositoryResolver {
    @Autowired
    List<BasicRepository> repositories

    @Cacheable(REPOSITORY_RESOLVER)
    BasicRepository findRepository(String entityType) {
        repositories.find { entityType == it.entityType }
                .with(this.&checkRepository)
    }

    private checkRepository(BasicRepository repository) {
        if(!repository) {
            throw new IllegalArgumentException('No repository for target entity')
        }
        repository
    }
}
