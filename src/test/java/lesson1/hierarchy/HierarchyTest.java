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

        assertTrue(square instanceof Rect);
    }
}
