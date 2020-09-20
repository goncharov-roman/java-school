package org.roman;

import org.roman.annotation.Metric;

import java.util.ArrayList;
import java.util.List;

public class CalculatorImpl implements Calculator {

    private static final String MR = "MR";
    private static final String MS = "MS";
    private static final String MC = "MC";

    private int number = 0;

    @Override
    @Metric
    public int calc(int number) {
        if (number < 0) {
            throw new IllegalArgumentException("Negative argument!");
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        List<Integer> factorials = new ArrayList<>();
        factorials.add(1);

        for (int i = 1; i <= number; i++) {
            factorials.add(factorials.get(i - 1) * i);
        }

        return factorials.get(number);
    }

    @Override
    public int getSavedNumber() {
        return number;
    }

    @Override
    public void saveNumber(int number) {
        this.number = number;
    }
}
