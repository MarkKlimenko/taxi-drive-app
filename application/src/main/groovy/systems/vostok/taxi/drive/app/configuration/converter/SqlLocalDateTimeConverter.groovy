package systems.vostok.taxi.drive.app.configuration.converter

import javax.persistence.AttributeConverter
import javax.persistence.Converter
import java.sql.Timestamp
import java.time.LocalDateTime

@Converter(autoApply = true)
class SqlLocalDateTimeConverter implements AttributeConverter<LocalDateTime, Timestamp> {

    @Override
    Timestamp convertToDatabaseColumn(LocalDateTime localDateTime) {
        localDateTime == null ? null : Timestamp.valueOf(localDateTime)
    }

    @Override
    LocalDateTime convertToEntityAttribute(Timestamp sqlTimestamp) {
        sqlTimestamp == null ? null : sqlTimestamp.toLocalDateTime()
    }
}
