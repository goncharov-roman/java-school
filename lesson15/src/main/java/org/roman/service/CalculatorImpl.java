package org.roman.service;

import java.util.List;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Stream.iterate;

public class CalculatorImpl implements Calculator {

    public List<Integer> fibonacci(int n) {
        return iterate(asList(0, 1), i -> asList(i.get(1), i.get(0) + i.get(1)))
                .limit(n)
                .map(i -> i.get(0))
                .collect(toList());
    }
}
