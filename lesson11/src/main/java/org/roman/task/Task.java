package org.roman.task;

import java.util.concurrent.Callable;

public class Task<T> {

    private Callable<? extends T> callable;
    private volatile T result = null;
    private volatile boolean isException;
    private volatile Exception exception;
    private final Object lock = new Object();

    public Task(Callable<? extends T> callable){
        this.callable = callable;
    }

    public T get() throws TaskException {
        Thread thread = Thread.currentThread();
        if (result != null) {
            System.out.println(thread + " - will return before synchronized");
            return result;
        }
        synchronized (lock) {
            if (isException) {
                System.out.println(thread + " - will throw before computations");
                throw new TaskException(exception);
            }
            if (result == null) {
                System.out.println(thread + " - no result, will compute");
                try {
                    result = callable.call();
                } catch (Exception e) {
                    isException = true;
                    exception = e;
                    System.out.println(thread + " - will throw first time");
                    throw new TaskException(e);
                }
            }
            System.out.println(thread + " - will return from synchronized");
            return result;
        }
    }
}
