package com.markklim.taxi.drive.app.configuration.authentication

import org.springframework.context.annotation.Conditional
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.ImportResource

@Configuration
@Conditional(SsoEnabledCondition.class)
@ImportResource("classpath*:keycloak-security-config.xml")
class KeycloakAuthConfiguration {  }
