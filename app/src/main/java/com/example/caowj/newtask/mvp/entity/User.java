package com.example.caowj.newtask.mvp.entity;

/**
 * package: com.example.caowj.newtask.module1.bean
 * author: Administrator
 * date: 2017/9/4 13:40
 */
public class User {
    private String name;
    private String password;

    public User() {
        super();
    }

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
