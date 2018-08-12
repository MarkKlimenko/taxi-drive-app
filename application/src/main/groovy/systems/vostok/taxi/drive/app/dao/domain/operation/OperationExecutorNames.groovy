package systems.vostok.taxi.drive.app.dao.domain.operation

enum OperationExecutorNames {
    CORE_OPERATION_EXECUTOR('CORE_OPERATION_EXECUTOR')

    String name

    OperationExecutorNames(String name) {
        this.name = name
    }
}
