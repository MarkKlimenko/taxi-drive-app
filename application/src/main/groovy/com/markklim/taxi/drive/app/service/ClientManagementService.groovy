package com.markklim.taxi.drive.app.service

import com.markklim.taxi.drive.app.service.entity.ClientService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ClientManagementService {
    @Autowired
    ClientService clientService

    @Autowired
    SettingsService settingsService

    Map checkUserById(String id) {
        clientService.getByLogin(id).with {
            if(it) {
                Map clientInfo = it.asMap()
                clientInfo << [nextRideFree: settingsService.getValueById('freeRideAmount')]
                clientInfo
            } else { [:] }
        }
    }
}
