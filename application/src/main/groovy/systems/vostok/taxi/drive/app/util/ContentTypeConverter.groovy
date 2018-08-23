package systems.vostok.taxi.drive.app.util

import groovy.json.JsonSlurper
import org.apache.avro.Schema
import org.apache.avro.io.*
import org.apache.avro.specific.SpecificDatumReader
import org.apache.avro.specific.SpecificDatumWriter
import org.apache.avro.specific.SpecificRecordBase
import systems.vostok.taxi.drive.app.util.constant.ContentTypes

import static systems.vostok.taxi.drive.app.util.exception.ContentTypeException.contentTypeException

class ContentTypeConverter {
    /**
     * @param content resolve String or Map content
     * @return
     */
    static Map toMap(Object content) {
        if (content instanceof Map) {
            content
        } else if (content instanceof String) {
            Object parsedContent = new JsonSlurper().parseText(content)
            if (parsedContent instanceof Map) {
                return parsedContent
            } else {
                throw contentTypeException(ContentTypes.MAP)
            }
        } else {
            throw contentTypeException(ContentTypes.MAP)
        }
    }

    /**
     * @deprecated
     * Replaced by <code>avroEntity.toByteBuffer().array()</code>.
     *
     * @param entity
     * @return
     */
    @Deprecated
    static <T extends SpecificRecordBase> byte[] avroToByte(T entity) {
        ByteArrayOutputStream out = new ByteArrayOutputStream()
        BinaryEncoder encoder = EncoderFactory.get().binaryEncoder(out, null)
        DatumWriter<T> writer = new SpecificDatumWriter(entity.getSchema())

        writer.write(entity, encoder)
        encoder.flush()
        out.close()

        out.toByteArray()
    }

    /**
     * @deprecated
     * Replaced by <code>AvroEntityClass.fromByteBuffer(ByteBuffer.wrap(avroEntityAsByteArray))</code>.
     *
     * @param bytes
     * @param schema
     * @return
     */
    @Deprecated
    static <T extends SpecificRecordBase> T byteToAvro(byte[] bytes, Schema schema) {
        Decoder decoder = DecoderFactory.get().binaryDecoder(bytes, null)
        new SpecificDatumReader<T>(schema).read(null, decoder)
    }
}
