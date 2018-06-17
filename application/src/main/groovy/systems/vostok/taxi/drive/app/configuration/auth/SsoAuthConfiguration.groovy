package systems.vostok.taxi.drive.app.configuration.auth

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter

@Configuration
@EnableWebSecurity
@ConditionalOnProperty(name = 'security.type', havingValue = 'sso')
class SsoAuthConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        // TODO: Implement Sso
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // TODO: Implement Sso
    }
}
