package com.example.caowj.newtask.designPattern.builder.subclass;


import com.example.caowj.newtask.designPattern.builder.base.Builder2;
import com.example.caowj.newtask.designPattern.builder.base.House1;

/**
 * Created by TianBin on 2018/1/13 17:42.
 * Description :构造我的房子的Builder
 * 注意：每个set方法都返回自身。
 */

public class TbBuilder2 extends Builder2 {
    private TbHouse1 tbHouse = new TbHouse1();

    @Override
    public TbBuilder2 setHost() {
        tbHouse.setHost();
        return this;
    }

    @Override
    public TbBuilder2 setHouseNum(int houseNum) {
        tbHouse.setHouseNum(houseNum);
        return this;
    }

    @Override
    public TbBuilder2 setStyle(String style) {
        tbHouse.setStyle(style);
        return this;
    }

    @Override
    public TbBuilder2 setAddress(String address) {
        tbHouse.setAddress(address);
        return this;
    }

    @Override
    public House1 create() {
        return tbHouse;
    }
}
