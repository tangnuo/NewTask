package com.example.caowj.newtask.designPattern.builder.base;

/**
 * Created by TianBin on 2018/1/13 17:35.
 * Description :抽象产品类
 */

public abstract class House1 {
    protected String host;//业主
    protected int houseNum;//房间数
    protected String style;//装修风格
    protected String address;//房屋地址

    public abstract void setHost();

    public void setHouseNum(int houseNum) {
        this.houseNum = houseNum;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "House{" +
                "host='" + host + '\'' +
                ", houseNum=" + houseNum +
                ", style='" + style + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
