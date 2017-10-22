package com.markklim.taxi.drive.app.api.adapter

import org.junit.Before
import org.junit.Test

import java.time.LocalDate

class LocalDateAdapterTest {

    private String stringDate
    private LocalDate date
    private LocalDateAdapter adapter

    @Before
    void setUp() throws Exception {
        stringDate = '2017-09-25'
        date = LocalDate.of(2017, 9, 25)
        adapter = new LocalDateAdapter()
    }

    @Test
    void marshalTest() throws Exception {
        assert adapter.marshal(date) == stringDate
    }

    @Test
    void unmarshalTest() throws Exception {
        assert adapter.unmarshal(stringDate) == date
    }
}
