package com.example.caowj.newtask.designPattern.builder.subclass;


import com.example.caowj.newtask.designPattern.builder.base.House1;

/**
 * Created by TianBin on 2018/1/13 17:42.
 * Description :我的房子
 */

public class TbHouse1 extends House1 {
    @Override
    public void setHost() {
        host = "tb's house";
    }
}
