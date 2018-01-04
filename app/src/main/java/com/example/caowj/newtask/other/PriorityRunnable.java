package com.example.caowj.newtask.other;

/**
 * package: com.example.caowj.newtask
 * author: Administrator
 * date: 2018/1/3 17:33
 */
public abstract class PriorityRunnable implements Runnable, Comparable<PriorityRunnable> {
    private int priority;

    public PriorityRunnable(int priority) {
        if (priority < 0)
            throw new IllegalArgumentException();
        this.priority = priority;
    }

    @Override
    public int compareTo(PriorityRunnable another) {
        int my = this.getPriority();
        int other = another.getPriority();
        return my < other ? 1 : my > other ? -1 : 0;
    }

    @Override
    public void run() {
        doSth();
    }

    public abstract void doSth();

    public int getPriority() {
        return priority;
    }
}
