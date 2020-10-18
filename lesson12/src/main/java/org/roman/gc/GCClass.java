package org.roman.gc;

import java.util.HashSet;
import java.util.Set;

public class GCClass {

    public void createObjects() {
        Set<Double> set = new HashSet<>();
        for (int i = 0; i < 10000; i++) {
            Double d = Double.valueOf("10.5");
            set.add(d);
        }

        System.out.println(set.size());
    }
}
