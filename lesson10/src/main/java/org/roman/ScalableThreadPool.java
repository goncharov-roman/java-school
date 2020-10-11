package org.roman;

import java.util.ArrayList;
import java.util.List;

public class ScalableThreadPool implements ThreadPool {

    private BlockingQueue queue;
    private List<PoolThread> threads = new ArrayList<>();
    private boolean isStopped = false;
    private final int minCount;
    private final int maxCount;

    public ScalableThreadPool(int minCount, int maxCount) {
        this.minCount = minCount;
        this.maxCount = maxCount;

        queue = new BlockingQueue(minCount);
        for (int i = 0; i < minCount; i++) {
            threads.add(new PoolThread(queue));
        }
    }

    @Override
    public void start() {
        threads.forEach(Thread::start);
    }

    @Override
    public void execute(Runnable runnable) throws InterruptedException {
        if (queue.size() == 0) {
            for (int i = threads.size() - 1; i > minCount; i--) {
                System.out.println(threads.get(i) + " removed");
                threads.get(i).doStop();
                threads.remove(i);
            }
        }

        if (queue.size() == minCount && threads.size() < maxCount) {
            threads.add(new PoolThread(queue));
            threads.get(threads.size() - 1).start();
            System.out.println(threads.get(threads.size() - 1) + " added");
        }

        this.queue.enqueue(runnable);
    }

    @Override
    public synchronized void stop() {
        this.isStopped = true;
        threads.forEach(PoolThread::doStop);
    }
}
