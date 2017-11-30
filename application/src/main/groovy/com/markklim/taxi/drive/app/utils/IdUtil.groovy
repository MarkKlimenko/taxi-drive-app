package com.markklim.taxi.drive.app.utils

/**
 * Created by viktor on 25.11.17.
 */
class IdUtil {
    public static int generateId(String from, String to){
        if(!(from && to)){
            throw new IllegalArgumentException("From and To fields" +
                    " must be initialize")
        }
        def list = [from, to]
        list.sort()
        return list.hashCode()
    }
}
