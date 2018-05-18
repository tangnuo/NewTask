package com.example.caowj.newtask.designPattern.factory.simplefactory;

import com.example.caowj.newtask.designPattern.factory.simplefactory.abstractPhone.Phone;

/**
 * Created by tb on 2017/5/13.
 * 以小米3为例
 */

public class MI3 extends Phone {
    /**
     * @param type 手机型号
     */
    public MI3(String type) {
        super(type);
    }
}
