package org.roman;

import java.util.ArrayList;
import java.util.List;

public class FixedThreadPool implements ThreadPool {

    private BlockingQueue queue;
    private final List<PoolThread> threads = new ArrayList<>();
    private boolean isStopped = false;

    public FixedThreadPool(int numOfThreads, int tasksMaxCount) {
        queue = new BlockingQueue(tasksMaxCount);
        for (int i = 0; i < numOfThreads; i++) {
            threads.add(new PoolThread(queue));
        }
    }

    @Override
    public void start() {
        threads.forEach(Thread::start);
    }

    @Override
    public void execute(Runnable runnable) throws InterruptedException {
        this.queue.enqueue(runnable);
    }

    @Override
    public synchronized void stop() {
        this.isStopped = true;
        threads.forEach(PoolThread::doStop);
    }
}
