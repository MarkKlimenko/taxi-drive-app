package systems.vostok.taxi.drive.app.api.adapter

import javax.xml.bind.annotation.adapters.XmlAdapter
import java.time.LocalDate

class LocalDateAdapter extends XmlAdapter<String, LocalDate> {

    @Override
    LocalDate unmarshal(String dateString) throws Exception {
        LocalDate.parse(dateString)
    }

    @Override
    String marshal(LocalDate dateTime) throws Exception {
        dateTime.toString()
    }
}
