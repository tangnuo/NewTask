package com.example.caowj.newtask.designPattern.singleton;

/**
 * Created by TianBin on 2017/7/8 17:50.
 * Description :静态内部类（推荐使用）
 */

public class Sd {
    private Sd() {
    }

    public static Sd getInstance() {
        return SingletonHolder.instance;
    }

    /**
     * 静态内部类
     */
    private static class SingletonHolder {
        private static final Sd instance = new Sd();
    }
}
