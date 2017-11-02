package com.markklim.taxi.drive.app.util

/**
 * Created by viktor on 02.11.17.
 */
class UtilHash {
    //TODO будет использоваться в нескольких местах. Заменить реализацию на адекватную
    static int getPriceDtdHashCode(String from, String to){
        return from.hashCode() + to.hashCode()
    }
}
