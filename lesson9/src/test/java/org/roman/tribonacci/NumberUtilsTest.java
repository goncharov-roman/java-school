package org.roman.tribonacci;

import org.junit.jupiter.api.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;

class NumberUtilsTest {

    @Test
    void testGenerateTribonacci() {
        List<Integer> actual = NumberUtils.generateTribonacci(10);
        List<Integer> expected = asList(0, 1, 1, 2, 4, 7, 13, 24, 44, 81);

        assertEquals(expected, actual);
    }
}
