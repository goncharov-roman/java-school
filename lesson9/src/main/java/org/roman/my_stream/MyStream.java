package org.roman.my_stream;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

import static java.util.Arrays.asList;

public class MyStream<T> {

    private final List<T> elements;

    public MyStream(List<T> elements) {
        this.elements = elements;
    }

    public static <T> MyStream<T> of(T... elements) {
        return new MyStream<>(asList(elements));
    }

    public MyStream<T> filter(Predicate<T> predicate) {
        List<T> filtered = new ArrayList<>();
        for (T element : elements) {
            if (predicate.test(element)) {
                filtered.add(element);
            }
        }
        return new MyStream<>(filtered);
    }

    public MyStream<T> map(UnaryOperator<T> function) {
        List<T> mapped = new ArrayList<>();
        for (T element : elements) {
            mapped.add(function.apply(element));
        }
        return new MyStream<>(mapped);
    }

    public MyStream<T> distinct() {
        Set<T> set = new HashSet<>(elements);
        return new MyStream<>(new ArrayList<>(set));
    }

    public void forEach(Consumer<T> consumer) {
        for (T element : elements) {
            consumer.accept(element);
        }
    }
}
