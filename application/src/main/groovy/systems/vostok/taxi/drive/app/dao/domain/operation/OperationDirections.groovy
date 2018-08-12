package systems.vostok.taxi.drive.app.dao.domain.operation

import systems.vostok.taxi.drive.app.util.exception.OperationExecutionException

import java.util.concurrent.ConcurrentHashMap

enum OperationDirections {
    ENROLL('enroll'),
    ROLLBACK('rollback')

    String type

    private static final Map<String,OperationDirections> ENUM_MAP

    OperationDirections(String type) {
        this.type = type
    }

    static {
        Map<String,OperationDirections> map = new ConcurrentHashMap<String,OperationDirections>()
        for (OperationDirections instance : values()) {
            map.put(instance.type, instance)
        }
        ENUM_MAP = Collections.unmodifiableMap(map)
    }

    static OperationDirections get(String type) {
        if(ENUM_MAP.containsKey(type)) {
            ENUM_MAP.get(type)
        } else {
            throw new OperationExecutionException("No such Operation direction type: { ${type} }")
        }
    }
}