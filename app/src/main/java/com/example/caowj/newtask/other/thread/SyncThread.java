package com.example.caowj.newtask.other.thread;

/**
 * synchronized修饰代码块
 *
 * @Author : caowj
 * @Date : 2018/3/28
 */

public class SyncThread implements Runnable {
    private static int count;

    public SyncThread() {
        count = 0;
    }


    //    同步代码块
    @Override
    public void run() {
        synchronized (this) {
            for (int i = 0; i < 5; i++) {
                try {
                    System.out.println(Thread.currentThread().getName() + ":" + (count++));
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public int getCount() {
        return count;
    }
}
