package systems.vostok.taxi.drive.app.dao.domain.operation

// TODO: Change to Enum
class OperationState {
    private OperationState() { throw new UnsupportedOperationException() }

    public static final String IN_PROCESS_OPERATION_STATE = 'in process'
    public static final String SUCCESS_OPERATION_STATE = 'success'
    public static final String FAIL_OPERATION_STATE = 'fail'
    public static final String CANCELED_OPERATION_STATE = 'canceled'
}
