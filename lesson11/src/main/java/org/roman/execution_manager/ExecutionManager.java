package org.roman.execution_manager;

public interface ExecutionManager {

    Context execute(Runnable callback, Runnable... tasks);
}
