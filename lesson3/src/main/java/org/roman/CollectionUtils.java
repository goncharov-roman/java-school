package org.roman;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class CollectionUtils {

    public static <T> void addAll(List<? extends T> source, List<? super T> destination) {
        destination.addAll(source);
    }

    public static <T> List<T> newArrayList() {
        return new ArrayList<>();
    }

    public static <T> int indexOf(List<? extends T> source, T o) {
        return source.indexOf(o);
    }

    public static <T> List<? extends T> limit(List<? extends T> source, int size) {
        return source.subList(0, size);
    }

    public static <T> void add(List<? super T> source, T o) {
        source.add(o);
    }

    public static <T> void removeAll(List<? super T> removeFrom, List<? extends T> c2) {
        removeFrom.removeAll(c2);
    }

    public static <T> boolean containsAll(List<? extends T> c1, List<? extends T> c2) {
        return c1.containsAll(c2);
    }

    public static <T> boolean containsAny(List<? extends T> c1, List<? extends T> c2) {
        for (T element : c2) {
            if (c1.contains(element)) {
                return true;
            }
        }

        return false;
    }

    public static <T extends Comparable<T>> List<? extends T> range(List<? extends T> list, T min, T max) {
        return list.stream()
                .filter(element ->
                        (element.compareTo(min) > 0 || element.compareTo(min) == 0) &&
                        (element.compareTo(max) < 0 || element.compareTo(max) == 0))
                .collect(Collectors.toList());
    }

    public static <T extends Comparable<T>> List<? extends T> range(List<? extends T> list, T min, T max, Comparator<T> comparator) {
        return list.stream()
                .filter(element ->
                        (comparator.compare(element, min) > 0 || comparator.compare(element, min) == 0) &&
                        (comparator.compare(element, max) < 0 || comparator.compare(element, max) == 0))
                .collect(Collectors.toList());
    }
}
