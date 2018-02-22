package systems.vostok.taxi.drive.app.util

class JavaConverters {
    static Iterable convertIterable(scala.collection.Iterable iterable) {
        scala.collection.JavaConverters.asJavaCollection(iterable)
    }
}
