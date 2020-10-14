package org.roman.execution_manager;

import java.util.concurrent.atomic.AtomicInteger;

public class ContextImpl implements Context {

    private final AtomicInteger completedTaskCount = new AtomicInteger(0);
    private final AtomicInteger failedTaskCount = new AtomicInteger(0);
    private final AtomicInteger interruptedTaskCount = new AtomicInteger(0);
    private volatile boolean isFinished;

    @Override
    public void incrementCompletedTaskCount() {
        completedTaskCount.incrementAndGet();
    }

    @Override
    public int getCompletedTaskCount() {
        return completedTaskCount.get();
    }

    @Override
    public void incrementFailedTaskCount() {
        failedTaskCount.incrementAndGet();
    }

    @Override
    public int getFailedTaskCount() {
        return failedTaskCount.get();
    }

    @Override
    public void incrementInterruptedTaskCount() {
        interruptedTaskCount.incrementAndGet();
    }

    @Override
    public int getInterruptedTaskCount() {
        return interruptedTaskCount.get();
    }

    @Override
    public void interrupt() {

    }

    @Override
    public void setFinished() {
        isFinished = true;
    }

    @Override
    public boolean isFinished() {
        return isFinished;
    }
}
