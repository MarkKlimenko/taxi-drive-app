package systems.vostok.taxi.drive.app.util

class WordUtil {
    static String modifyGeoName(String name) {
        name.toUpperCase()
                .replaceAll(/[Ё]/, 'Е')
                .replaceAll(/[(]/, '')
                .replaceAll(/[)]/, '')
                .replaceAll(/[-]/, '')
                .replaceAll(/[ ]/, '')
    }
}
