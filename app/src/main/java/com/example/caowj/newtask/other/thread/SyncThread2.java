package com.example.caowj.newtask.other.thread;

/**
 * 静态方法
 *
 * @Author : caowj
 * @Date : 2018/3/28
 */

public class SyncThread2 implements Runnable {
    private static int count;

    public SyncThread2() {
        count = 0;
    }

    public synchronized static void method() {
        for (int i = 0; i < 5; i++) {
            try {
                System.out.println(Thread.currentThread().getName() + ":" + (count++));
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public synchronized void run() {
        method();
    }
}
