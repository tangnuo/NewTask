package com.example.caowj.newtask.designPattern.singleton;

/**
 * Created by TianBin on 2017/7/8 17:47.
 * Description :懒汉模式
 */

public class Sb {
    private static Sb instance;

    private Sb() {
    }

    public static synchronized Sb getInstance() {
        if (instance == null) {
            instance = new Sb();
        }
        return instance;
    }
}
