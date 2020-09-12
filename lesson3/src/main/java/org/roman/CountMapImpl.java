package org.roman;

import java.util.HashMap;
import java.util.Map;

public class CountMapImpl<T> implements CountMap<T> {

    private final Map<T, Integer> objectToCount;

    public CountMapImpl() {
        this.objectToCount = new HashMap<>();
    }

    @Override
    public void add(T o) {
        Integer value = objectToCount.getOrDefault(o, 0) + 1;
        objectToCount.put(o, value);
    }

    @Override
    public int getCount(T o) {
        return objectToCount.getOrDefault(o, 0);
    }

    @Override
    public int remove(T o) {
        Integer removeCount = objectToCount.remove(o);
        return (removeCount == null) ? 0 : removeCount;
    }

    @Override
    public int size() {
        return objectToCount.size();
    }

    @Override
    public void addAll(CountMap<T> source) {
        source.toMap().keySet().forEach(key -> {
            Integer value = objectToCount.getOrDefault(key, 0) + source.getCount(key);
            objectToCount.put(key, value);
        });
    }

    @Override
    public Map<T, Integer> toMap() {
        return objectToCount;
    }

    @Override
    public void toMap(Map<T, Integer> destination) {
        destination.putAll(objectToCount);
    }
}
