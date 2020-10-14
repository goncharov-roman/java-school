package org.roman.execution_manager;

import java.util.ArrayList;
import java.util.List;

public class ExecutionManagerImpl implements ExecutionManager {

    @Override
    public Context execute(Runnable callback, Runnable... tasks) {
        Context context = new ContextImpl();
        Thread t = new Thread(() -> executeTasks(context, callback, tasks));
        t.start();
        return context;
    }

    private void executeTasks(Context context, Runnable callback, Runnable... tasks) {
        List<Thread> threads = new ArrayList<>();

        Thread.UncaughtExceptionHandler h = (th, ex) -> context.incrementFailedTaskCount();

        for (Runnable task : tasks) {
            Thread t = new Thread(task);
            t.setUncaughtExceptionHandler(h);
            threads.add(t);
            t.start();
            System.out.println(t + " started");
        }

        for (Thread t : threads) {
            try {
                t.join();
                System.out.println(t + " finished");
                context.incrementCompletedTaskCount();
            } catch (InterruptedException e) {
                context.incrementInterruptedTaskCount();
            }
        }

        Thread t = new Thread(callback);
        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        context.setFinished();
    }
}
