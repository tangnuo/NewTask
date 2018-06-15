package com.example.caowj.newtask.example.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Transient;

/**
 * package: com.example.caowj.newtask.example.bean
 * author: Administrator
 * date: 2017/11/7 14:50
 */
@Entity
public class User {
    @Id
    private Long id;
    @Property(nameInDb = "cName")
    private String name;

    private String stuName; // 学员姓名
    private String stuSex;  // 性别
    @Transient
    private String stuScore;    // 成绩


    @Generated(hash = 768864439)
    public User(Long id, String name, String stuName, String stuSex) {
        this.id = id;
        this.name = name;
        this.stuName = stuName;
        this.stuSex = stuSex;
    }

    @Generated(hash = 586692638)
    public User() {
    }
    

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStuName() {
        return this.stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public String getStuSex() {
        return this.stuSex;
    }

    public void setStuSex(String stuSex) {
        this.stuSex = stuSex;
    }

    public String getStuScore() {
        return this.stuScore;
    }

    public void setStuScore(String stuScore) {
        this.stuScore = stuScore;
    }

}
