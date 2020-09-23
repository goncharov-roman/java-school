package org.roman;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ObjectStoreTest {

    @Test
    public void testObjectStore() {
        Integer[] objects = new Integer[]{1, 2, 3};

        List<Integer> expected = asList(1, 2, 3);
        List<Integer> result = new ArrayList<>();

        for (Integer object : objects) {
            result.add(object);
        }

        assertEquals(expected, result);
    }
}
