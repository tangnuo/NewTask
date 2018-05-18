package com.example.caowj.newtask.designPattern.factory.simplefactory;

import com.example.caowj.newtask.designPattern.factory.simplefactory.abstractPhone.Phone;

/**
 * Created by tb on 2017/5/13.
 * 未知类型
 */

public class UnKnown extends Phone {
    /**
     * @param type 手机型号
     */
    public UnKnown(String type) {
        super(type);
    }
}
