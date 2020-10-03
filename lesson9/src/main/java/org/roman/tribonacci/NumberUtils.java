package org.roman.tribonacci;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Stream.iterate;

public class NumberUtils {

    private NumberUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static List<Integer> generateTribonacci(int series) {
        return iterate(new int[]{0, 1, 1}, i -> new int[]{i[1], i[2], i[0] + i[1] + i[2]})
                .limit(series)
                .map(i -> i[0])
                .collect(toList());
    }
}
