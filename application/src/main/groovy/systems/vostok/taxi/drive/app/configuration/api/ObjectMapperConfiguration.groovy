package systems.vostok.taxi.drive.app.configuration.api

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.module.SimpleModule
import systems.vostok.taxi.drive.app.configuration.jackson.OperationRequestDeserializer
import systems.vostok.taxi.drive.app.operation.OperationRequest

import javax.ws.rs.ext.ContextResolver
import javax.ws.rs.ext.Provider

@Provider
class ObjectMapperConfiguration implements ContextResolver<ObjectMapper> {
    private final ObjectMapper mapper

    ObjectMapperConfiguration() {
        this.mapper = createObjectMapper()
    }

    @Override
    ObjectMapper getContext(Class<?> type) {
        return mapper
    }

    private static ObjectMapper createObjectMapper() {
        SimpleModule module = new SimpleModule()
        module.addDeserializer(OperationRequest.class, new OperationRequestDeserializer())

        new ObjectMapper().registerModule(module)
    }
}
