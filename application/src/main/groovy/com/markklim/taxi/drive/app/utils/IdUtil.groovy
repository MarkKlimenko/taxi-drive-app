package com.markklim.taxi.drive.app.utils

class IdUtil {
    static Integer generateId(String from, String to){
        if(!(from && to)){
            throw new IllegalArgumentException("From and To fields" +
                    " must be initialize")
        }
        [from, to].sort().hashCode()
    }
}
