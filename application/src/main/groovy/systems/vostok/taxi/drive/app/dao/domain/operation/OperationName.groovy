package systems.vostok.taxi.drive.app.dao.domain.operation

import systems.vostok.taxi.drive.app.util.exception.OperationExecutionException

import java.util.concurrent.ConcurrentHashMap

enum OperationName {
    ADD_CLIENT_OPERATION('ADD_CLIENT'),
    EDIT_CLIENT_OPERATION('EDIT_CLIENT'),
    DELETE_CLIENT_OPERATION('DELETE_CLIENT'),

    ADD_COUNTRY_OPERATION('ADD_COUNTRY'),
    EDIT_COUNTRY_OPERATION('EDIT_COUNTRY'),
    DELETE_COUNTRY_OPERATION('DELETE_COUNTRY'),

    ADD_STATE_OPERATION('ADD_STATE'),
    EDIT_STATE_OPERATION('EDIT_STATE'),
    DELETE_STATE_OPERATION('DELETE_STATE'),

    ADD_CITY_OPERATION('ADD_CITY'),
    EDIT_CITY_OPERATION('EDIT_CITY'),
    DELETE_CITY_OPERATION('DELETE_CITY'),

    ADD_DISTRICT_OPERATION('ADD_DISTRICT'),
    EDIT_DISTRICT_OPERATION('EDIT_DISTRICT'),
    DELETE_DISTRICT_OPERATION('DELETE_DISTRICT'),

    ADD_STREET_OPERATION('ADD_STREET'),
    EDIT_STREET_OPERATION('EDIT_STREET'),
    DELETE_STREET_OPERATION('DELETE_STREET')


    String name

    private static final Map<String,OperationName> ENUM_MAP

    private OperationName(String name) {
        this.name = name
    }

    static {
        Map<String,OperationName> map = new ConcurrentHashMap<String,OperationName>()
        for (OperationName instance : values()) {
            map.put(instance.name, instance)
        }
        ENUM_MAP = Collections.unmodifiableMap(map)
    }

    static OperationName get(String name) {
        if(ENUM_MAP.containsKey(name)) {
            ENUM_MAP.get(name)
        } else {
            throw new OperationExecutionException("No such Operation with name: { ${name} }")
        }
    }

    static Map getOperationNames() {
        ENUM_MAP
    }
}
