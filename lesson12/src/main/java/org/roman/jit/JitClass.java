package org.roman.jit;

import java.util.HashMap;
import java.util.Map;

public class JitClass {

    Map<Integer, String> map = new HashMap<>();

    public void fillMap() {
        for (int i =0; i < 100_000; i++) {
            map.put(i, "value" + i);
        }
    }
}
