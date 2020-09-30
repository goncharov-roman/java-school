package org.roman.service;

import org.roman.annotation.Cache;

import java.util.List;

import static org.roman.proxy.CacheType.FILE;
import static org.roman.proxy.CacheType.MEMORY;

public interface Service {

    @Cache(cacheType = MEMORY)
    List<String> run(String item, double value);

    @Cache(cacheType = FILE)
    List<String> work(String item);
}
