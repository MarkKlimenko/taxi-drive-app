package com.markklim.taxi.drive.app.util

class CommonUtil {

    static Integer generateId(String from, String to) {
        [from, to].sort().hashCode()
    }
}
