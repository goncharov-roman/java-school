package org.roman;

import java.util.List;

public interface Calculator {

    @Cachable(H2DB.class)
    List<Integer> fibonacci(int n);
}
