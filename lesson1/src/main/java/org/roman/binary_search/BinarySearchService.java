package org.roman.binary_search;

import java.util.List;

public class BinarySearchService {

    public static int search(List<Integer> numbers, int numberToSearch) {
        int left = 0;
        int right = numbers.size() - 1;

        while (left <= right) {
            int middle = (left + right) / 2;
            if (numbers.get(middle) < numberToSearch) {
                left = middle + 1;
            } else if (numbers.get(middle) > numberToSearch) {
                right = middle - 1;
            } else {
                return middle;
            }
        }

        return -1;
    }
}
