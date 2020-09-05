package lesson1.hierarchy;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class HierarchyTest {

    @Test
    public void testHierarchy() {
        Circle circle = new Circle(10.0);
        Rect rect = new Rect(4.0, 5.0);
        Triangle triangle = new Triangle(Arrays.asList(1, 2, 3));
        Square square = new Square(5.0);

        assertTrue(circle instanceof PlanarFigure);

        assertTrue(square instanceof Rect);
        assertTrue(square instanceof Polygon);
        assertTrue(square instanceof PlanarFigure);
        assertEquals(4, square.getNumberOfSides());

        assertTrue(rect instanceof Polygon);
        assertTrue(rect instanceof PlanarFigure);
        assertEquals(4, rect.getNumberOfSides());

        assertTrue(triangle instanceof Polygon);
        assertTrue(triangle instanceof PlanarFigure);
        assertEquals(3, triangle.getNumberOfSides());
    }
}
