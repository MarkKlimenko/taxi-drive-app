package com.markklim.taxi.drive.app.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import systems.vostok.tda.service.DistrictMapperService

@Configuration
class CommonConfiguration {

    @Bean
    DistrictMapperService districtMapperService() {
        new DistrictMapperService()
    }
}
