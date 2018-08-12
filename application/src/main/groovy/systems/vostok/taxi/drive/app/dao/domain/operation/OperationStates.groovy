package systems.vostok.taxi.drive.app.dao.domain.operation

enum OperationStates {
    IN_PROCESS('in process'),
    SUCCESS('success'),
    FAILED('failed'),
    CANCELED('canceled'),

    ROLLBACK_SUCCESS('rollback success'),
    ROLLBACK_FAILED('rollback failed')

    String state

    OperationStates(String state) {
        this.state = state
    }
}
