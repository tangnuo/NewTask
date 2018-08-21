package com.kedacom.module_common.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程池，管理线程的工具类
 *
 * @author caowj
 */
public class ThreadPoolManager {
    private static ThreadPoolManager manager;
    private ExecutorService service;

    private ThreadPoolManager() {
//        获取CPU数量
        int num = Runtime.getRuntime().availableProcessors();
//        通常核心线程数可以设为CPU数量+1，而最大线程数可以设为CPU的数量*2+1。
        service = Executors.newFixedThreadPool(num * 2);
    }

    public static ThreadPoolManager getInstance() {
        if (manager == null) {
            manager = new ThreadPoolManager();
        }
        return manager;
    }

    public void addTask(Runnable runnable) {
        service.submit(runnable);
    }
}
