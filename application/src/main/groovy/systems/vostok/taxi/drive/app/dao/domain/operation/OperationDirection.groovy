package systems.vostok.taxi.drive.app.dao.domain.operation

import systems.vostok.taxi.drive.app.util.exception.OperationExecutionException

import java.util.concurrent.ConcurrentHashMap

enum OperationDirection {
    ENROLL('enroll'),
    ROLLBACK('rollback')

    String type

    private static final Map<String,OperationDirection> ENUM_MAP

    OperationDirection(String type) {
        this.type = type
    }

    static {
        Map<String,OperationDirection> map = new ConcurrentHashMap<String,OperationDirection>()
        for (OperationDirection instance : values()) {
            map.put(instance.type, instance)
        }
        ENUM_MAP = Collections.unmodifiableMap(map)
    }

    static OperationDirection get(String type) {
        if(ENUM_MAP.containsKey(type)) {
            ENUM_MAP.get(type)
        } else {
            throw new OperationExecutionException("No such Operation direction type: { ${type} }")
        }
    }
}