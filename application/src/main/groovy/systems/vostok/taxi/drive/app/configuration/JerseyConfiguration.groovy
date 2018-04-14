package systems.vostok.taxi.drive.app.configuration

import org.glassfish.jersey.media.multipart.MultiPartFeature
import org.glassfish.jersey.server.ResourceConfig
import org.springframework.context.annotation.Configuration
import systems.vostok.taxi.drive.app.api.controller.*
import systems.vostok.taxi.drive.app.api.provider.FilterProvider
import systems.vostok.taxi.drive.app.api.provider.PaginationProvider
import systems.vostok.taxi.drive.app.api.provider.SortProvider

@Configuration
class JerseyConfiguration extends ResourceConfig {
    JerseyConfiguration() {
        registerControllers()
        registerComponents()
        registerProviders()
    }

    void registerControllers() {
        register(ServiceController.class)
        register(UniversalCrudController.class)
        register(ClientManagementController.class)
        register(RateController.class)
        register(GeoController.class)
    }

    void registerComponents() {
        register(ResponseConfiguration.class)
        register(MultiPartFeature.class)
    }

    void registerProviders() {
        register(FilterProvider.class)
        register(SortProvider.class)
        register(PaginationProvider.class)
    }
}
