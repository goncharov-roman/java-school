package org.roman;

public interface ThreadPool {

    void start();

    void execute(Runnable runnable) throws Exception;

    void stop();
}
