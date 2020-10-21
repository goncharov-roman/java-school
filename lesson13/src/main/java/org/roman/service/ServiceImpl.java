package org.roman.service;

import java.util.List;

import static java.lang.String.valueOf;
import static java.util.Arrays.asList;

public class ServiceImpl implements Service {

    @Override
    public List<String> run(String item, double value) {
        return asList(valueOf(value + 1), valueOf(value + 2));
    }
}
