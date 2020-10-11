package org.roman;

public class PoolThread extends Thread {

    private BlockingQueue queue;
    private boolean isStopped = false;

    public PoolThread(BlockingQueue queue) {
        this.queue = queue;
    }

    public void run() {
        while (!isStopped()) {
            try {
                Runnable runnable = (Runnable) queue.dequeue();
                System.out.println(Thread.currentThread() + " is running now");
                runnable.run();
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread() + " stopped");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void doStop() {
        isStopped = true;
        this.interrupt();
    }

    public synchronized boolean isStopped() {
        return isStopped;
    }
}
