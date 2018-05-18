package com.example.caowj.newtask.designPattern.factory.factorymethod.abstractMethod;


import com.example.caowj.newtask.designPattern.factory.simplefactory.abstractPhone.Phone;

/**
 * Created by tb on 2017/5/13.
 */

public abstract class FactoryMethod {
    /**
     * 抽象的工厂方法，具体产生什么让子类去实现
     *
     * @param type
     * @return 具体的产品对象
     */
    public abstract Phone createPhone(String type);

    public abstract void printCountry();
}
