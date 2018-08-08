package systems.vostok.taxi.drive.app.configuration.api

import org.glassfish.jersey.media.multipart.MultiPartFeature
import org.glassfish.jersey.server.ResourceConfig
import org.glassfish.jersey.servlet.ServletProperties
import org.springframework.context.annotation.Configuration
import systems.vostok.taxi.drive.app.api.controller.*
import systems.vostok.taxi.drive.app.api.provider.FilterProvider
import systems.vostok.taxi.drive.app.api.provider.PaginationProvider
import systems.vostok.taxi.drive.app.api.provider.SearchParametersProvider
import systems.vostok.taxi.drive.app.api.provider.SortProvider

@Configuration
class JerseyConfiguration extends ResourceConfig {
    JerseyConfiguration() {
        registerControllers()
        registerComponents()
        registerProviders()

        property(ServletProperties.FILTER_FORWARD_ON_404, true)
    }

    private void registerControllers() {
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
