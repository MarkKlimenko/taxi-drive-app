package systems.vostok.taxi.drive.app.operation

abstract class Operation {
    String operationName
    
    abstract Object execute()
    abstract Object rollback()

    String getOperationName() {
        assert operationName : 'Operation name is not defined'
        operationName
    }
}