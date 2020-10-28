package org.roman;

import java.util.List;

import static java.util.Arrays.asList;

public class CalculatorImpl implements Calculator {

    public List<Integer> fibonacci(int n) {
        if (n == 1) {
            return asList(0);
        }

        List<Integer> result = asList(0, 1);
        for (int i = 2; i < n; i++) {
            result.add(result.get(i - 1) + result.get(i - 2));
        }

        return result;
    }
}
