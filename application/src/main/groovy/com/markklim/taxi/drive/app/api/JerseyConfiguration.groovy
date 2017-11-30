package com.markklim.taxi.drive.app.api

import com.markklim.taxi.drive.app.api.controller.RateController
import com.markklim.taxi.drive.app.api.controller.ClientManagementController
import com.markklim.taxi.drive.app.api.controller.ReadOnlyController
import com.markklim.taxi.drive.app.api.controller.ServiceController
import com.markklim.taxi.drive.app.api.controller.UpdateController
import org.glassfish.jersey.media.multipart.MultiPartFeature
import org.glassfish.jersey.server.ResourceConfig
import org.springframework.context.annotation.Configuration

@Configuration
class JerseyConfiguration extends ResourceConfig  {
    JerseyConfiguration() {
        registerControllers()
        registerComponents()
    }

    void registerControllers() {
        register(ServiceController.class)
        register(UpdateController.class)
        register(ReadOnlyController.class)
        register(ClientManagementController.class)
        register(RateController.class)
    }

    void registerComponents() {
        register(ResponseConfiguration.class)
        register(MultiPartFeature.class)
    }

}
