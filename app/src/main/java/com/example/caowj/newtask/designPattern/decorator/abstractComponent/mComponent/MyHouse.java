package com.example.caowj.newtask.designPattern.decorator.abstractComponent.mComponent;


import com.example.caowj.newtask.designPattern.decorator.abstractComponent.House;

/**
 * Created by tb on 2017/5/10.
 * 具体的需要装修的我的房子（具体组件）
 */

public class MyHouse extends House {
    private static final String TAG = "MyHouse";

    public MyHouse() {
        description = TAG + "：还未开始任何装修的毛坯房";
    }

    @Override
    public void decorate() {
        //具体装修逻辑，省略。。。
    }
}
