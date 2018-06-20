package systems.vostok.taxi.drive.app.configuration.auth

import org.keycloak.adapters.springboot.KeycloakSpringBootConfigResolver
import org.keycloak.adapters.springsecurity.config.KeycloakWebSecurityConfigurerAdapter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.core.authority.mapping.SimpleAuthorityMapper
import org.springframework.security.core.session.SessionRegistryImpl
import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy

@Configuration
@EnableWebSecurity
@ConditionalOnProperty(name = 'security.type', havingValue = 'sso')
class SsoAuthConfiguration extends KeycloakWebSecurityConfigurerAdapter {
    @Autowired
    void configureGlobal(AuthenticationManagerBuilder auth) {
        keycloakAuthenticationProvider().with {
            it.setGrantedAuthoritiesMapper(new SimpleAuthorityMapper())
            auth.authenticationProvider(it)
        }
    }

    @Bean
    KeycloakSpringBootConfigResolver keycloakConfigResolver() {
        new KeycloakSpringBootConfigResolver()
    }

    @Bean
    @Override
    protected SessionAuthenticationStrategy sessionAuthenticationStrategy() {
        new RegisterSessionAuthenticationStrategy(new SessionRegistryImpl())
    }

    @Override
    protected void configure(HttpSecurity http) {
        super.configure(http)
        http.authorizeRequests()
                .antMatchers('/**').hasRole('ADMIN')
                .anyRequest().authenticated()
                .and().csrf().disable()
                .httpBasic()
    }
}
