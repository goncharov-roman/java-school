package org.roman.service;

import org.roman.annotation.H2DB;
import org.roman.annotation.Cachable;

import java.util.List;

public interface Calculator {

    @Cachable(H2DB.class)
    List<Integer> fibonacci(int n);
}
