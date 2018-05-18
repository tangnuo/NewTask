package com.example.caowj.newtask.designPattern.builder.base;

/**
 * Created by TianBin on 2018/1/13 17:39.
 * Description :抽象Builder类
 */

public abstract class Builder2 {
    public abstract Builder2 setHost();

    public abstract Builder2 setHouseNum(int houseNum);

    public abstract Builder2 setStyle(String style);

    public abstract Builder2 setAddress(String address);

    public abstract House1 create();
}
