package systems.vostok.taxi.drive.app.api.provider

import groovy.json.JsonSlurper
import org.springframework.security.core.context.SecurityContextHolder
import systems.vostok.taxi.drive.app.operation.OperationDirection
import systems.vostok.taxi.drive.app.operation.OperationRequest

import javax.ws.rs.ext.ParamConverter
import javax.ws.rs.ext.ParamConverterProvider
import javax.ws.rs.ext.Provider
import java.lang.annotation.Annotation
import java.lang.reflect.Type
import java.nio.ByteBuffer

import static systems.vostok.taxi.drive.app.util.constant.CommonConstants.ANONYMOUS_USER

@Provider
class OperationRequestProvider implements ParamConverterProvider {
    @Override
    <T> ParamConverter<T> getConverter(Class<T> aClass, Type type, Annotation[] annotations) {
        new ParamConverter<OperationRequest>() {
            @Override
            OperationRequest fromString(String stringOperationRequest) {
                convert(stringOperationRequest)
            }

            @Override
            String toString(OperationRequest operationRequest) {
                throw new UnsupportedOperationException('OperationRequest is not serializable to String by ParamConverterProvider')
            }
        } as ParamConverter<T>
    }

    private static OperationRequest convert(String or) {
        Object parsedOr = new JsonSlurper().parseText(or)

        if (parsedOr instanceof Map) {
            OperationRequest.newBuilder()
                    .setId(UUID.randomUUID().toString())
                    .setOperationName(parsedOr.operationName as String)
                    .setOwner(SecurityContextHolder.getContext().getAuthentication()?.name ?: ANONYMOUS_USER)
                    .setDirection(OperationDirection.valueOf(parsedOr.direction as String))
                    .setAsync(parsedOr.async ?: false)
                    .setBody(ByteBuffer.wrap(parsedOr.body.bytes))
                    .build()
        } else {
            OperationRequest.fromByteBuffer(ByteBuffer.wrap(or.bytes))
        }
    }
}
