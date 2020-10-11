package org.roman;

import java.util.concurrent.atomic.AtomicInteger;

public class Main {

    public static void main(String[] args) {
        ThreadPool fixedThreadPool = new FixedThreadPool(4, 10);
        final AtomicInteger counter = new AtomicInteger(20);

        fixedThreadPool.start();
        for (int i = 0; i < 20; i++) {
            try {
                fixedThreadPool.execute(counter::getAndDecrement);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        fixedThreadPool.stop();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(counter.get());

        // Scalable thread pool launch
        ThreadPool scalableThreadPool = new ScalableThreadPool(4, 10);
        final AtomicInteger scalableCounter = new AtomicInteger(100);

        scalableThreadPool.start();
        for (int i = 0; i < 100; i++) {
            try {
                scalableThreadPool.execute(scalableCounter::getAndDecrement);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        scalableThreadPool.stop();

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(scalableCounter.get());
    }
}
