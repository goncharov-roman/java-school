package lesson1.binary_search;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BinarySearchServiceTest {

    @Test
    public void testSearch() {
        final List<Integer> list = Arrays.asList(-1000, -3, 0, 2, 4, 5, 10000);

        assertEquals(1, BinarySearchService.search(list, -3));
        assertEquals(4, BinarySearchService.search(list, 4));
        assertEquals(6, BinarySearchService.search(list, 10000));
    }
}
