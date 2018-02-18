package systems.vostok.taxi.drive.app.api

import org.glassfish.jersey.media.multipart.MultiPartFeature
import org.glassfish.jersey.server.ResourceConfig
import org.springframework.context.annotation.Configuration
import systems.vostok.taxi.drive.app.api.controller.*

@Configuration
class JerseyConfiguration extends ResourceConfig {
    JerseyConfiguration() {
        registerControllers()
        registerComponents()
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
}
