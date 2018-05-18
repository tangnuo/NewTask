package com.example.caowj.newtask.designPattern.decorator.abstractComponent;

/**
 * Created by tb on 2017/5/10.
 * 定义房子抽象基类（抽象组件）
 */

public abstract class House {
    /**
     * 装修情况说明
     */
    protected String description;

    public String getDescription() {
        return description;
    }

    /**
     * 房子有一个装修的抽象方法
     */
    public abstract void decorate();
}
