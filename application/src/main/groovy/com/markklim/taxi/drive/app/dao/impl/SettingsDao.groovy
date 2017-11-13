package com.markklim.taxi.drive.app.dao.impl

import org.springframework.stereotype.Service

@Service
class SettingsDao {

    String getValueById(String id) {
        [ [id:'freeRideAmount', setting:'10'],
          [id:'someSetting', setting:'100'] ].find { it.id == id }.setting
    }
}
