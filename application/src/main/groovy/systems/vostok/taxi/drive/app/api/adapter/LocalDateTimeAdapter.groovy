package systems.vostok.taxi.drive.app.api.adapter

import javax.xml.bind.annotation.adapters.XmlAdapter
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class LocalDateTimeAdapter extends XmlAdapter<String, LocalDateTime> {
    @Override
    LocalDateTime unmarshal(String dateString) throws Exception {
        LocalDateTime.parse(dateString)
    }

    @Override
    String marshal(LocalDateTime dateTime) throws Exception {
        dateTime.format(DateTimeFormatter.ISO_DATE_TIME)
    }
}
