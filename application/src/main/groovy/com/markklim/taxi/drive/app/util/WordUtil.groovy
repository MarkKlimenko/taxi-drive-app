package com.markklim.taxi.drive.app.util

import org.springframework.stereotype.Component

@Component
class WordUtil {

    String modifyGeoName(String name) {
        name.toUpperCase()
                .replaceAll(/[Ё]/, 'Е')
                .replaceAll(/[(]/, '')
                .replaceAll(/[)]/, '')
                .replaceAll(/[-]/, '')
    }
}
