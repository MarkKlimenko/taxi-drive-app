package systems.vostok.taxi.drive.app.dao

/**
 * Need for proper object creation
 */
trait ObjectCreator {
    def propertyMissing(String name, value) {
        // do nothing
    }
}