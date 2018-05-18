package com.example.caowj.newtask.designPattern.singleton;

/**
 * Created by TianBin on 2017/7/8 17:45.
 * Description :饿汉模式
 */

public class Sa {
    private static final Sa instance = new Sa();

    private Sa() {
    }

    public static Sa getInstance() {
        return instance;
    }
}
