package com.markklim.taxi.drive.app.api

import com.markklim.taxi.drive.app.api.controller.ClientManagementController
import com.markklim.taxi.drive.app.api.controller.ReadOnlyController
import com.markklim.taxi.drive.app.api.controller.RideController
import com.markklim.taxi.drive.app.api.controller.StatusController
import com.markklim.taxi.drive.app.api.controller.UpdateController
import com.markklim.taxi.drive.app.api.controller.UserController
import org.glassfish.jersey.server.ResourceConfig
import org.springframework.context.annotation.Configuration

@Configuration
class JerseyConfiguration extends ResourceConfig  {
    JerseyConfiguration() {
        register(StatusController.class)
        register(UpdateController.class)
        register(ReadOnlyController.class)
        register(UserController.class)
        register(ResponseConfiguration.class)
        register(RideController.class)
        register(ClientManagementController.class)
    }
}
