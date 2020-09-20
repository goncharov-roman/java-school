package org.roman;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CalculatorImplTest {

    @Test
    public void testCalc() {
        Calculator calculator = new CalculatorImpl();

        assertThrows(IllegalArgumentException.class, () -> calculator.calc(-1));

        assertEquals(6, calculator.calc(3));
        assertEquals(24, calculator.calc(4));
        assertEquals(120, calculator.calc(5));
    }
}