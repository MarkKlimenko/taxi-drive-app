package systems.vostok.taxi.drive.app.configuration.api

import org.glassfish.jersey.media.multipart.MultiPartFeature
import org.glassfish.jersey.server.ResourceConfig
import org.glassfish.jersey.servlet.ServletContainer
import org.springframework.boot.web.servlet.ServletRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import systems.vostok.taxi.drive.app.api.controller.*
import systems.vostok.taxi.drive.app.api.provider.FilterProvider
import systems.vostok.taxi.drive.app.api.provider.PaginationProvider
import systems.vostok.taxi.drive.app.api.provider.SearchParametersProvider
import systems.vostok.taxi.drive.app.api.provider.SortProvider

import static org.glassfish.jersey.servlet.ServletProperties.JAXRS_APPLICATION_CLASS

@Configuration
class JerseyConfiguration extends ResourceConfig {
    JerseyConfiguration() {
        registerControllers()
        registerComponents()
        registerProviders()
    }

    @Bean
    ServletRegistrationBean jerseyServlet() {
        new ServletRegistrationBean(new ServletContainer(), '/*').with {
            addInitParameter(JAXRS_APPLICATION_CLASS, JerseyConfiguration.class.getName())
            it
        }
    }

    private void registerControllers() {
        register(ServiceController.class)
        register(UniversalCrudController.class)
        register(ClientManagementController.class)
        register(RateController.class)
        register(GeoController.class)
        register(OperationController.class)
    }

    private void registerComponents() {
        register(ResponseConfiguration.class)
        register(MultiPartFeature.class)
    }

    private void registerProviders() {
        register(FilterProvider.class)
        register(SortProvider.class)
        register(PaginationProvider.class)
        register(SearchParametersProvider.class)
    }
}
