package org.roman.tractor;

import org.junit.jupiter.api.Test;
import org.roman.tractor.exception.TractorInDitchException;
import org.roman.tractor.model.Orientation;
import org.roman.tractor.model.Tractor;

import static org.junit.jupiter.api.Assertions.*;

class TractorTest {

    @Test
    void testMoveForward() {
        Tractor tractor = new Tractor();
        tractor.move("F");
        tractor.move("F");
        tractor.move("T");
        tractor.move("F");

        assertEquals(2, tractor.getPositionY());
        assertEquals(1, tractor.getPositionX());
        assertEquals(Orientation.EAST, tractor.getOrientation());
    }

    @Test
    void testInDitchException() {
        Tractor tractor = new Tractor();
        tractor.move("F");
        tractor.move("F");
        tractor.move("F");
        tractor.move("F");
        tractor.move("F");

        assertThrows(TractorInDitchException.class, () -> tractor.move("F"));
    }
}