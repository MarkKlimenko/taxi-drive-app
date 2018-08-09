package systems.vostok.taxi.drive.app.api.adapter

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

import java.time.LocalDate
import java.util.stream.Stream

import static org.junit.jupiter.api.Assertions.assertEquals
import static systems.vostok.taxi.drive.app.test.JUnitUtil.getToStreamArguments

class LocalDateAdapterTest {
    LocalDateAdapter localDateAdapter = new LocalDateAdapter()

    static Stream<Arguments> testSource() {
        [['2017-09-25', LocalDate.of(2017, 9, 25)],
         ['2000-01-25', LocalDate.of(2000, 1, 25)],
         ['2056-09-25', LocalDate.of(2056, 9, 25)],
         ['2017-12-05', LocalDate.of(2017, 12, 5)]]
                .with(toStreamArguments)
    }

    @ParameterizedTest
    @MethodSource('testSource')
    void marshalTest(String stringDate, LocalDate date) {
        localDateAdapter.marshal(date)
                .with { assertEquals(it, stringDate) }
    }

    @ParameterizedTest
    @MethodSource('testSource')
    void unmarshalTest(String stringDate, LocalDate date) {
        localDateAdapter.unmarshal(stringDate)
                .with { assertEquals(it, date) }
    }
}
