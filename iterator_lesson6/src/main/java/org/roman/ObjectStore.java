package org.roman;

import java.util.Iterator;

public class ObjectStore<T> implements Iterable<T> {

    private T[] objects;

    public ObjectStore(T[] objects) {
        this.objects = objects;
    }

    @Override
    public Iterator<T> iterator() {

        return new Iterator<T>() {

            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < objects.length;
            }

            @Override
            public T next() {
                return objects[currentIndex++];
            }
        };
    }
}
