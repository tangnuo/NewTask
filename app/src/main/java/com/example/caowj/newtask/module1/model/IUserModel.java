package com.example.caowj.newtask.module1.model;

import com.example.caowj.newtask.module1.listener.OnLoginListener;

/**
 * package: com.example.caowj.newtask.module1.model
 * author: Administrator
 * date: 2017/9/4 13:40
 */
public interface IUserModel {
    public void login(String name, String password, OnLoginListener loginListener);
}
