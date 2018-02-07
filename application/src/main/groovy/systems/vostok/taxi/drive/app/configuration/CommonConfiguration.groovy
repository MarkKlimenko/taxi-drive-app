package systems.vostok.taxi.drive.app.configuration

import org.springframework.cache.annotation.EnableCaching
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import systems.vostok.tda.service.DistrictMapperService

@Configuration
@EnableCaching
class CommonConfiguration {

    @Bean
    DistrictMapperService districtMapperService() {
        new DistrictMapperService()
    }
}
