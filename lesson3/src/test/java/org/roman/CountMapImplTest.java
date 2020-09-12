package org.roman;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class CountMapImplTest {

    @Test
    public void testCountMapInteger() {
        CountMap<Integer> countMap = new CountMapImpl<>();

        countMap.add(10);
        countMap.add(10);
        countMap.add(5);
        countMap.add(6);
        countMap.add(5);
        countMap.add(10);

        assertEquals(3, countMap.size());
        assertEquals(2, countMap.getCount(5));
        assertEquals(1, countMap.getCount(6));
        assertEquals(3, countMap.getCount(10));
        assertEquals(0, countMap.getCount(1));

        assertEquals(1, countMap.remove(6));
        assertEquals(0, countMap.getCount(6));
        assertEquals(2, countMap.size());

        Map<Integer, Integer> expected = new HashMap<>();
        expected.put(5, 2);
        expected.put(10, 3);
        assertEquals(expected, countMap.toMap());

        CountMap<Integer> another = new CountMapImpl<>();
        another.add(10);
        another.add(10);
        another.add(-100);
        another.add(0);

        countMap.addAll(another);

        assertEquals(4, countMap.size());

        Map<Integer, Integer> consumingMap = new HashMap<>();
        countMap.toMap(consumingMap);

        assertEquals(4, consumingMap.size());
        assertEquals(5, consumingMap.get(10));
        assertEquals(2, consumingMap.get(5));
        assertEquals(1, consumingMap.get(0));
        assertEquals(1, consumingMap.get(-100));
    }

    @Test
    public void testCountMapString() {
        CountMap<String> countMap = new CountMapImpl<>();

        countMap.add("One");
        countMap.add("One");
        countMap.add("Two");
        countMap.add("Three");

        assertEquals(3, countMap.size());
        assertEquals(2, countMap.getCount("One"));
        assertEquals(1, countMap.getCount("Two"));
        assertEquals(1, countMap.getCount("Three"));

        assertEquals(1, countMap.remove("Three"));
        assertEquals(0, countMap.getCount("Three"));
        assertEquals(2, countMap.size());

        Map<String, Integer> expected = new HashMap<>();
        expected.put("One", 2);
        expected.put("Two", 1);
        assertEquals(expected, countMap.toMap());

        CountMap<String> another = new CountMapImpl<>();
        another.add("One");
        another.add("Two");
        another.add("Four");

        countMap.addAll(another);

        assertEquals(3, countMap.size());

        Map<String, Integer> consumingMap = new HashMap<>();
        countMap.toMap(consumingMap);

        assertEquals(3, consumingMap.size());
        assertEquals(3, consumingMap.get("One"));
        assertEquals(2, consumingMap.get("Two"));
        assertEquals(1, consumingMap.get("Four"));
    }
}