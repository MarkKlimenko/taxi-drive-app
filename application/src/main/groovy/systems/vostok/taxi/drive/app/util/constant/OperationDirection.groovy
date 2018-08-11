package systems.vostok.taxi.drive.app.util.constant

enum OperationDirection {
    ENROLL('enroll'),
    ROLLBACK('rollback')

    String type

    OperationDirection(String type) {
        this.type = type
    }
}