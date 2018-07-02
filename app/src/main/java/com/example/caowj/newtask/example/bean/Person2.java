package com.example.caowj.newtask.example.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * http://www.cherylgood.cn/articles/2017/06/01/1496286638662.html<p/>
 * <p>
 * 实体类必须实现MultiItemEntity，在设置数据的时候，需要给每一个数据设置itemType<br/>
 * package: com.example.caowj.newtask.example.bean
 * author: Administrator
 * date: 2017/9/14 15:24
 */
public class Person2 implements MultiItemEntity {

    public static final int TEXT = 1;
    public static final int IMG = 2;
    private String name;
    private String pinyin;
    private int itemType;

    public Person2(int itemType, String name) {
        this.name = name;
        this.itemType = itemType;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    @Override
    public int getItemType() {
        return itemType;
    }
}
