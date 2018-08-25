package systems.vostok.taxi.drive.app.configuration

import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import systems.vostok.tda.service.DistrictMapperService

@Configuration
@EnableConfigurationProperties
@EnableCircuitBreaker
class CommonConfiguration {
    @Bean
    DistrictMapperService districtMapperService() {
        new DistrictMapperService()
    }
}
