package com.markklim.taxi.drive.app.utils

/**
 * Created by viktor on 25.11.17.
 */
// TODO если возвести каждый член в одну степень, то коммутативность сохранится
// И не будет случая, в котором при равенстве сумм двух hashcode
// результирующий хитровымудренный id будет одинаков
class IdUtil {
    public static int generateId(String from, String to){
        Double res = Math.pow(from.hashCode(), 2)  + Math.pow(to.hashCode(), 2)
        return res.intValue()
    }
}
