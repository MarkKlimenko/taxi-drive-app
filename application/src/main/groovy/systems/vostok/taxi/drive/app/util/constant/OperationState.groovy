package systems.vostok.taxi.drive.app.util.constant

class OperationState {
    private OperationState() { throw new UnsupportedOperationException() }

    static final String IN_PROCESS_OPERATION_STATE = 'in process'
    static final String SUCCESS_OPERATION_STATE = 'success'
    static final String FAIL_OPERATION_STATE = 'fail'
    static final String CANCELED_OPERATION_STATE = 'canceled'
}
