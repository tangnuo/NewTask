package com.example.caowj.newtask.example.bean;

import com.example.caowj.newtask.utils.PinYinUtils;

/**
 * package: com.example.caowj.newtask.example.bean
 * author: Administrator
 * date: 2017/9/4 17:32
 */
public class Person {
    private String name;

    private String pinyin;

    private String headerWord;

    public Person(String name) {
        this.name = name;
        this.pinyin = PinYinUtils.getPinYin(name);
        this.headerWord = pinyin.substring(0, 1);
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHeaderWord() {
        return headerWord;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", pinyin='" + pinyin + '\'' +
                '}';
    }
}
