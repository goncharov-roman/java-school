package org.roman.execution_manager;

public interface Context {

    void incrementCompletedTaskCount();
    int getCompletedTaskCount();
    void incrementFailedTaskCount();
    int getFailedTaskCount();
    void incrementInterruptedTaskCount();
    int getInterruptedTaskCount();
    void interrupt();
    void setFinished();
    boolean isFinished();
}
