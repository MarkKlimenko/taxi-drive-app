package systems.vostok.taxi.drive.app.configuration

import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.cache.annotation.EnableCaching
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import systems.vostok.tda.service.DistrictMapperService

@Configuration
@EnableCaching
@EnableConfigurationProperties
class CommonConfiguration {
    @Bean
    DistrictMapperService districtMapperService() {
        new DistrictMapperService()
    }
}
