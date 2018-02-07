package unit.com.markklim.taxi.drive.app.api.adapter

import systems.vostok.taxi.drive.app.api.adapter.LocalDateAdapter

import org.testng.annotations.DataProvider
import org.testng.annotations.Test
import java.time.LocalDate

import static org.testng.Assert.*

class LocalDateAdapterTest {

    @DataProvider(name = "marshal_test")
    Object[][] marshalParam() {
        [[LocalDate.of(2017, 5, 14), '2017-05-14'],
         [LocalDate.of(2014, 10, 5), '2014-10-05']]
    }

    @Test(dataProvider = "marshal_test")
    void marshalTest(LocalDate value, String expected) {
        new LocalDateAdapter().marshal(value)
                .with { assertEquals(it, expected) }
    }

    @DataProvider(name = "unmarshal_test")
    Object[][] unmarshalParam() {
        [['2017-05-14', LocalDate.of(2017, 5, 14)],
         ['2014-10-05', LocalDate.of(2014, 10, 5)]]
    }

    @Test(dataProvider = "unmarshal_test")
    void unmarshalTest(String value, LocalDate expected) {
        new LocalDateAdapter().unmarshal(value)
                .with{ assertEquals(it, expected) }
    }
}
