package org.roman.service;

import org.roman.annotation.Cache;

import java.util.List;

public interface Service {

    @Cache
    List<String> run(String item, double value);
}
