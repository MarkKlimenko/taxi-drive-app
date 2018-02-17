package systems.vostok.taxi.drive.app.api

import org.glassfish.jersey.media.multipart.MultiPartFeature
import org.glassfish.jersey.server.ResourceConfig
import org.springframework.context.annotation.Configuration
import systems.vostok.taxi.drive.app.api.controller.ClientManagementController
import systems.vostok.taxi.drive.app.api.controller.GeoController
import systems.vostok.taxi.drive.app.api.controller.RateController
import systems.vostok.taxi.drive.app.api.controller.UniversalCrudController
import systems.vostok.taxi.drive.app.api.controller.ServiceController
import systems.vostok.taxi.drive.app.api.controller.UpdateController

@Configuration
class JerseyConfiguration extends ResourceConfig  {
    JerseyConfiguration() {
        registerControllers()
        registerComponents()
    }

    void registerControllers() {
        register(ServiceController.class)
        register(UpdateController.class)
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
