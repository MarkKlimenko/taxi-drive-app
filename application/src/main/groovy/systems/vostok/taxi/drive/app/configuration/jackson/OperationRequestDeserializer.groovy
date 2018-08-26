package systems.vostok.taxi.drive.app.configuration.jackson

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import com.fasterxml.jackson.databind.node.JsonNodeType
import groovy.json.JsonSlurper
import systems.vostok.taxi.drive.app.operation.OperationDirection
import systems.vostok.taxi.drive.app.operation.OperationRequest

class OperationRequestDeserializer extends StdDeserializer<OperationRequest> {
    OperationRequestDeserializer() {
        this(null)
    }

    OperationRequestDeserializer(Class<?> vc) {
        super(vc)
    }

    @Override
    OperationRequest deserialize(JsonParser jp, DeserializationContext ctxt) {
        JsonNode node = jp.getCodec().readTree(jp)
        JsonNodeType nodeType = node.getNodeType()

        if (nodeType == JsonNodeType.OBJECT) {
            deserializeFromObject(node)
        } else {
            throw new RuntimeException("Cannot deserialize JSON to OperationRequest. " +
                    "Unsupported type { ${nodeType.name()} }")
        }
    }

    static OperationRequest deserializeFromObject(JsonNode node) {
        Map operationRequest = new JsonSlurper().parseText(node.toString()) as Map

        OperationRequest.newBuilder()
                .setId(operationRequest.id)
                .setOperationName(operationRequest.operationName)
                .setDirection(operationRequest.direction as OperationDirection)
                .setAsync(operationRequest.async)
                .setStringPayload(operationRequest.stringPayload)
                .setBytePayload(null)
                .build()
    }
}

