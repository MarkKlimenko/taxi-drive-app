package unit.com.markklim.taxi.drive.app.api.adapter

import com.markklim.taxi.drive.app.api.adapter.LocalDateTimeAdapter
import org.junit.Before
import org.junit.Test

import java.time.LocalDateTime

class LocalDateTimeAdapterTest {

    private String stringTime
    private LocalDateTime time
    private LocalDateTimeAdapter adapter

    @Before
    void setUp() throws Exception {
        time = LocalDateTime.of(2017, 9, 25, 10, 35, 15)
        stringTime = "2017-09-25T10:35:15"
        adapter = new LocalDateTimeAdapter()
    }

    @Test
    void marshalTest() throws Exception {
        assert adapter.marshal(time) == stringTime
    }

    @Test
    void unmarshalTest() throws Exception {
        assert adapter.unmarshal(stringTime) == time
    }
}
