package systems.vostok.taxi.drive.app.configuration

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler
import systems.vostok.tda.service.DistrictMapperService

import javax.annotation.PostConstruct

@Configuration
@EnableConfigurationProperties
@EnableCircuitBreaker
class CommonConfiguration {
    @Value('${scheduler.pool.size}')
    Integer schedulerPoolSize

    @PostConstruct
    void init() {
        TimeZone.setDefault(TimeZone.getTimeZone('UTC'));
    }

    @Bean
    ThreadPoolTaskScheduler threadPoolTaskScheduler() {
        new ThreadPoolTaskScheduler().with {
            poolSize = schedulerPoolSize
            it
        }
    }

    @Bean
    DistrictMapperService districtMapperService() {
        new DistrictMapperService()
    }
}
