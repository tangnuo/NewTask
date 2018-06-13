package com.example.caowj.newtask.mvp.listener;

import com.example.caowj.newtask.mvp.entity.User;

/**
 * package: com.example.caowj.newtask.module1.listener
 * author: Administrator
 * date: 2017/9/4 13:42
 */
public interface OnLoginListener {
    void loginSuccess(User user);

    void loginFailed();
}
