package systems.vostok.taxi.drive.app.util

class CommonUtil {
    static Integer generateId(String from, String to) {
        [from, to].sort().hashCode()
    }

    static Integer generateId(String... params) {
        params.sort().hashCode()
    }
}
