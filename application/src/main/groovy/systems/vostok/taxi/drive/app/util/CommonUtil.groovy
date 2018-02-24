package systems.vostok.taxi.drive.app.util

class CommonUtil {
    static Integer generateId(String... params) {
        params.sort().hashCode()
    }

    static Integer generateId(List<String> params) {
        params.sort().hashCode()
    }
}
