package systems.vostok.taxi.drive.app.api.adapter

import javax.xml.bind.annotation.adapters.XmlAdapter
import java.time.LocalDateTime

class LocalDateTimeAdapter extends XmlAdapter<String, LocalDateTime> {

    @Override
    LocalDateTime unmarshal(String dateString) throws Exception {
        LocalDateTime.parse(dateString)
    }

    @Override
    String marshal(LocalDateTime dateTime) throws Exception {
        dateTime.toString()
    }
}
