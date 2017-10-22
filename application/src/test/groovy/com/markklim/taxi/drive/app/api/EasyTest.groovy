package com.markklim.taxi.drive.app.api

import org.junit.Assert
import org.junit.Before
import org.junit.Test

import static org.assertj.core.api.Assertions.*

class EasyTest {

    private Easy easy

    @Before
    void setUp() throws Exception {
        easy = new Easy()
    }

    @Test
    void sumTest() throws Exception {
        Assert.assertEquals(2+2, 4)
    }

    @Test
    void stringNotEmptyTest() throws Exception {
        assertThat("Not empty Text").isNotEmpty()
    }

    @Test
    void isListTest() throws Exception {
        List a = new ArrayList(Arrays.asList(1,2,3,4,5,6,7,8,9))
        assertThat(a).isInstanceOf(ArrayList)
    }

    @Test
    void isEmptyListTest() throws Exception {
        List a = new ArrayList()
        assertThat(a).isEmpty()
    }

    @Test
    void easySumTest() throws Exception {
        assertThat(easy.sum(2, 6)).isEqualTo(2 + 6)
    }

    @Test
    void easyConcatTest() throws Exception {
        assertThat(easy.concatX("Text")).isEqualTo("TextX")
    }

    @Test
    void easySizeTest() throws Exception {
        String text = "test TeXt"
        assertThat(easy.size(text)).isEqualTo(text.length())
    }

    @Test
    void easySizeLessThenTest() throws Exception {
        String text = "test TeXt"
        assertThat(easy.size(text)).isLessThan(10)
    }
}

class Easy {

    int sum(int x, int y) {
        return x + y
    }

    String concatX(String text) {
        return text + "X"
    }

    int size(String text) {
        return text.length()
    }
}
