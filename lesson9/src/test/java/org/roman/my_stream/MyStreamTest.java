package org.roman.my_stream;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MyStreamTest {

    @Test
    void testFilter() {
        List<Integer> actual = new ArrayList<>();
        MyStream.of(1, 2, 3, 4, 5)
                .filter(i -> i % 2 == 1)
                .forEach(actual::add);

        List<Integer> expected = asList(1, 3, 5);

        assertEquals(expected, actual);
    }

    @Test
    void testMap() {
        List<Integer> actual = new ArrayList<>();
        MyStream.of(1, 2, 3, 4, 5)
                .map(i -> i * i)
                .forEach(actual::add);

        List<Integer> expected = asList(1, 4, 9, 16, 25);

        assertEquals(expected, actual);
    }

    @Test
    void testDistinct() {
        List<Integer> actual = new ArrayList<>();
        MyStream.of(1, 2, 2, 4, 4)
                .distinct()
                .forEach(actual::add);

        List<Integer> expected = asList(1, 2, 4);

        assertEquals(expected, actual);
    }
}
