package lesson2.file_stats;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FileStatsTest {

    private FileStats fileStats;

    @BeforeEach
    public void setUp() {
        List<String> lines = Arrays.asList(
                "Individuals and",
                "interactions",
                "over processes",
                "and tools"
        );

        fileStats = new FileStats(lines);
    }

    @Test
    public void testCountWordsFrequencies() {
        Map<String, Integer> expected = new HashMap<>();
        expected.put("and", 2);
        expected.put("individuals", 1);
        expected.put("interactions", 1);
        expected.put("processes", 1);
        expected.put("tools", 1);
        expected.put("over", 1);

        Map<String, Integer> actual = fileStats.countWordsFrequencies();

        assertEquals(expected, actual);
    }

    @Test
    public void testGetWordsSortedByLength() {
        List<String> expected = Arrays.asList("and", "over", "tools", "processes", "individuals", "interactions");

        List<String> actual = fileStats.getWordsSortedByLength();

        assertEquals(expected, actual);
    }

    @Test
    public void testCountDifferentWordsCount() {
        Integer expected = 6;
        Integer actual = fileStats.countDifferentWordsCount();

        assertEquals(expected, actual);
    }

    @Test
    public void testReversedLines() {
        List<String> expected = Arrays.asList(
                "and tools",
                "over processes",
                "interactions",
                "Individuals and"
        );

        List<String> actual = fileStats.getReversedLines();

        assertEquals(expected, actual);
    }

    @Test
    public void testGetLinesByNumbers() {
        List<Integer> numbers = Arrays.asList(3, 0, 2, 1);

        List<String> expected = Arrays.asList(
                "and tools",
                "Individuals and",
                "over processes",
                "interactions"
        );
        List<String> actual = fileStats.getLinesByNumbers(numbers);

        assertEquals(expected, actual);
    }

    @Test
    public void testIterator() {
        List<String> expected = Arrays.asList(
                "and tools",
                "over processes",
                "interactions",
                "Individuals and"
        );

        List<String> actual = new ArrayList<>();
        for (String line : fileStats) {
            actual.add(line);
        }

        assertEquals(expected, actual);
    }
}