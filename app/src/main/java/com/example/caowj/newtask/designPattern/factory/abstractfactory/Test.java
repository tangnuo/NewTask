package com.example.caowj.newtask.designPattern.factory.abstractfactory;

/**
 * Created by tb on 2017/5/13.
 */

public class Test {
    public void test() {
        Factory1 factory1 = new Factory1();
        factory1.getCpu().printInfo();
        factory1.getMemory().printInfo();

        Factory2 factory2 = new Factory2();
        factory2.getCpu().printInfo();
        factory2.getMemory().printInfo();
    }
}
