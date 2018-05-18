package com.example.caowj.newtask.designPattern.factory.simplefactory;

import com.example.caowj.newtask.designPattern.factory.simplefactory.abstractPhone.Phone;

/**
 * Created by tb on 2017/5/13.
 * 以小米2为例
 */

public class MI2 extends Phone {
    /**
     * @param type 手机型号
     */
    public MI2(String type) {
        super(type);
    }
}
