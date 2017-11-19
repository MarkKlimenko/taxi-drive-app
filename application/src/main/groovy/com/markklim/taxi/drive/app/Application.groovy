package com.markklim.taxi.drive.app

import com.markklim.taxi.drive.app.api.JerseyConfiguration
import org.glassfish.jersey.servlet.ServletContainer
import org.glassfish.jersey.servlet.ServletProperties
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.boot.web.servlet.ServletRegistrationBean
import org.springframework.boot.web.support.SpringBootServletInitializer
import org.springframework.context.annotation.Bean

@SpringBootApplication
class Application extends SpringBootServletInitializer {
    @Override
    SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        application.sources(Application.class)
    }

    static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args)
    }

    @Bean
    ServletRegistrationBean jerseyServlet() {
        ServletRegistrationBean registration = new ServletRegistrationBean(new ServletContainer(), "/*")
        registration.addInitParameter(ServletProperties.JAXRS_APPLICATION_CLASS, JerseyConfiguration.class.getName())
        registration
    }
}
