package com.example.caowj.newtask.designPattern.decorator.abstractDecorator.mDecorator;


import com.example.caowj.newtask.designPattern.decorator.abstractComponent.House;
import com.example.caowj.newtask.designPattern.decorator.abstractDecorator.HouseDecorator;

/**
 * Created by tb on 2017/5/10.
 * 门（具体装饰者）
 */

public class DoorDecorator extends HouseDecorator {
    /**
     * 持有一个house类的引用
     */
    private House house;

    public DoorDecorator(House house) {
        this.house = house;
    }

    @Override
    public void decorate() {
        //执行具体的装修逻辑，代码省略。。。
    }

    @Override
    public String getDescription() {
        return house.getDescription() + "——增加了门";
    }
}
