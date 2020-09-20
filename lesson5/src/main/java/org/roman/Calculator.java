package org.roman;

import org.roman.annotation.Metric;

public interface Calculator {

    /**
     * Calculate factorial function
     * @param number Argument of function
     * @return Factorial
     */
    @Metric
    int calc(int number);

    /**
     * Get saved in memory number
     * @return Saved number
     */
    int getSavedNumber();

    /**
     * Save number in memory
     * @param number Number to save
     */
    void saveNumber(int number);
}
