package com.example.caowj.newtask.other;

import android.util.Log;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * ThreadPoolExecutor也向外提供了三个接口供我们自己扩展满足我们需求的线程池
 * <p>
 * package: com.example.caowj.newtask.widget
 * author: Administrator
 * date: 2018/1/4 13:33
 */
public class MyThreadPoolExecutor extends ThreadPoolExecutor {
    public MyThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        super.beforeExecute(t, r);
        String threadName = t.getName();
        Log.v("zxy", "线程：" + threadName + "准备执行任务！");
    }

    @Override
    protected void afterExecute(Runnable r, Throwable t) {
        super.afterExecute(r, t);
        String threadName = Thread.currentThread().getName();
        Log.v("zxy", "线程：" + threadName + "任务执行结束！");
    }

    @Override
    protected void terminated() {
        super.terminated();
        Log.v("zxy", "线程池结束！");
    }
}
