package unit.com.markklim.taxi.drive.app.api.adapter

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import systems.vostok.taxi.drive.app.api.adapter.LocalDateTimeAdapter

import java.time.LocalDateTime
import java.util.stream.Stream

import static org.junit.jupiter.api.Assertions.assertEquals
import static unit.com.markklim.taxi.drive.app.test.JUnitUtil.toStreamArguments

class LocalDateTimeAdapterTest {
    LocalDateTimeAdapter localDateTimeAdapter = new LocalDateTimeAdapter()

    static Stream<Arguments> testSource() {
        [['2017-09-25T10:35:15', LocalDateTime.of(2017, 9, 25, 10, 35, 15)],
         ['2017-09-25T10:00:00', LocalDateTime.of(2017, 9, 25, 10, 0, 0)],
         ['2017-09-25T00:00:00', LocalDateTime.of(2017, 9, 25, 0, 0, 0)],
         ['2017-09-25T00:03:00', LocalDateTime.of(2017, 9, 25, 0, 3, 0)]]
                .with(toStreamArguments)
    }

    @ParameterizedTest
    @MethodSource('testSource')
    void marshalTest(String stringTime, LocalDateTime time) {
        localDateTimeAdapter.marshal(time)
                .with { assertEquals(it, stringTime) }
    }

    @ParameterizedTest
    @MethodSource('testSource')
    void unmarshalTest(String stringTime, LocalDateTime time) {
        localDateTimeAdapter.unmarshal(stringTime)
                .with { assertEquals(it, time) }
    }
}
