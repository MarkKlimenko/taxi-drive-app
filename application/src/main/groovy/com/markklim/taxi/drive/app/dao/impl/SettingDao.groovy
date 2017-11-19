package com.markklim.taxi.drive.app.dao.impl

import org.springframework.stereotype.Component

@Component
class SettingDao {

    String getValueById(String id) {
        [ [id:'freeRideAmount', setting:'10'],
          [id:'someSetting', setting:'100'] ].find { it.id == id }.setting
    }
}
