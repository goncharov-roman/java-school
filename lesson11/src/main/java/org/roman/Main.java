package org.roman;

import org.roman.execution_manager.Context;
import org.roman.execution_manager.ContextImpl;
import org.roman.execution_manager.ExecutionManager;
import org.roman.execution_manager.ExecutionManagerImpl;
import org.roman.task.Task;
import org.roman.task.TaskException;

public class Main {

    public static void main(String[] args) {

        int number = 10;
        Task<Integer> task = new Task<>(() -> number);

        for (int i = 0; i < 5; i++) {
            Thread t = new Thread(() -> {
                try {
                    System.out.println(task.get());
                } catch (TaskException e) {
                    e.printStackTrace();
                }
            });
            t.start();
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Task<String> taskThrowing = new Task<>(() -> {
            throw new Exception("Some exception");
        });

        for (int i = 0; i < 5; i++) {
            Thread t = new Thread(() -> {
                try {
                    System.out.println(taskThrowing.get());
                } catch (TaskException e) {
                    System.out.println(Thread.currentThread() + " has thrown");
                }
            });
            t.start();
        }

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("----- Execution Manager -----");

        ExecutionManager manager = new ExecutionManagerImpl();
        Context context = manager.execute(
                () -> System.out.println("Callback"),
                () -> System.out.println("Task1"),
                () -> System.out.println("Task2"),
                () -> System.out.println("Task3"));

        System.out.println("Completed task = " + context.getCompletedTaskCount());
        System.out.println("Finished = " + context.isFinished());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Completed task = " + context.getCompletedTaskCount());
        System.out.println("Finished = " + context.isFinished());
    }
}
