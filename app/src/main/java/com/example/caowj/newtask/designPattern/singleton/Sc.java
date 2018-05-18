package com.example.caowj.newtask.designPattern.singleton;

/**
 * Created by TianBin on 2017/7/8 17:48.
 * Description :Double Check Lock()DCL
 */

public class Sc {
    private static Sc instance = null;

    private Sc() {
    }

    public static Sc getInstance() {
        if (instance == null) {
            synchronized (Sc.class) {
                if (instance == null) {
                    instance = new Sc();
                }
            }
        }
        return instance;
    }
}
