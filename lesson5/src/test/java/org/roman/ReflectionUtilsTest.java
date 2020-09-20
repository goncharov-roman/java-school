package org.roman;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ReflectionUtilsTest {

    @Test
    void getAllMethods() {
        Calculator calculator = new CalculatorImpl();

        Set<String> actual = new HashSet<>(ReflectionUtils.getAllMethods(calculator));
        Set<String> expected = new HashSet<>(asList(
                "saveNumber", "calc", "getSavedNumber", "finalize", "wait", "wait", "wait", "equals",
                "toString", "hashCode", "getClass", "clone", "registerNatives", "notify", "notifyAll"));

        assertEquals(expected, actual);
    }

    @Test
    void getAllGetters() {
        Calculator calculator = new CalculatorImpl();

        Set<String> actual = new HashSet<>(ReflectionUtils.getAllGetters(calculator));
        Set<String> expected = new HashSet<>(asList("getSavedNumber", "getClass"));

        assertEquals(expected, actual);
    }

    @Test
    void testAreConstantsValid() throws IllegalAccessException {
        Calculator calculator = new CalculatorImpl();

        assertTrue(ReflectionUtils.areConstantsValid(calculator));
    }
}