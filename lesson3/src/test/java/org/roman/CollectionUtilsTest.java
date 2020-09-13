package org.roman;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CollectionUtilsTest {

    @Test
    public void testCollectionUtilsInteger() {
        List<Integer> list = CollectionUtils.newArrayList();

        List<Integer> source = Arrays.asList(1, 2, 3, 4, 5);
        CollectionUtils.addAll(source, list);

        assertEquals(5, list.size());
        assertEquals(2, CollectionUtils.indexOf(list, 3));

        List<Integer> expectedLimited = Arrays.asList(1, 2, 3);
        assertEquals(expectedLimited, CollectionUtils.limit(list, 3));

        CollectionUtils.add(list, 6);
        assertEquals(6, list.size());

        CollectionUtils.removeAll(list, Arrays.asList(3, 3, 4));
        assertEquals(4, list.size());
        assertEquals(-1, CollectionUtils.indexOf(list, 3));

        assertTrue(CollectionUtils.containsAll(list, Arrays.asList(1, 6, 5, 1)));
        assertFalse(CollectionUtils.containsAll(list, Arrays.asList(1, 3, 5, 1)));

        assertTrue(CollectionUtils.containsAny(list, Arrays.asList(1, 10, 100, 1000)));
        assertFalse(CollectionUtils.containsAny(list, Arrays.asList(-1, -100)));

        List<Integer> expectedRange = Arrays.asList(1, 2, 5);
        assertEquals(expectedRange, CollectionUtils.range(list, -1, 5));

        assertEquals(expectedRange, CollectionUtils.range(list, 1, 5, Integer::compareTo));
    }

    @Test
    public void testCollectionUtilsDouble() {
        List<Double> list = CollectionUtils.newArrayList();

        List<Double> source = Arrays.asList(1.0, 2.5, 3.2, 4.1, 5.5);
        CollectionUtils.addAll(source, list);

        assertEquals(5, list.size());
        assertEquals(2, CollectionUtils.indexOf(list, 3.2));

        List<Double> expectedLimited = Arrays.asList(1.0, 2.5, 3.2);
        assertEquals(expectedLimited, CollectionUtils.limit(list, 3));

        CollectionUtils.add(list, 6.9);
        assertEquals(6, list.size());

        CollectionUtils.removeAll(list, Arrays.asList(3.2, 3.2, 4.1));
        assertEquals(4, list.size());
        assertEquals(-1, CollectionUtils.indexOf(list, 3.2));

        assertTrue(CollectionUtils.containsAll(list, Arrays.asList(1.0, 6.9, 5.5, 1.0)));
        assertFalse(CollectionUtils.containsAll(list, Arrays.asList(1.0, 3.2, 5.5, 1.0)));

        assertTrue(CollectionUtils.containsAny(list, Arrays.asList(1.0, 10.10, 100.100, 1000.1000)));
        assertFalse(CollectionUtils.containsAny(list, Arrays.asList(-1.0, -100.001)));

        List<Double> expectedRange = Arrays.asList(1.0, 2.5, 5.5);
        assertEquals(expectedRange, CollectionUtils.range(list, -1.0, 5.9));

        assertEquals(expectedRange, CollectionUtils.range(list, 1.0, 5.8, Double::compareTo));
    }
}