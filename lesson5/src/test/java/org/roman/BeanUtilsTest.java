package org.roman;

import org.junit.jupiter.api.Test;
import org.roman.model.A;
import org.roman.model.B;
import org.roman.utils.BeanUtils;

import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.*;

class BeanUtilsTest {

    @Test
    void testAssign() throws IllegalAccessException, InvocationTargetException {
        A a = new A();
        B b = new B();

        a.setNumberField(100);
        a.setStringField("String");

        b.setNumberField(1);
        b.setStringField("");

        BeanUtils.assign(a, b);

        assertEquals(a.getNumberField(), b.getNumberField());
        assertEquals(a.getStringField(), b.getStringField());
    }

    @Test
    void testIsCompatible() {
        assertTrue(BeanUtils.isCompatible(Integer.class, Number.class));
        assertTrue(BeanUtils.isCompatible(Double.class, Number.class));
        assertTrue(BeanUtils.isCompatible(String.class, Object.class));
        assertFalse(BeanUtils.isCompatible(Integer.class, String.class));
    }
}
