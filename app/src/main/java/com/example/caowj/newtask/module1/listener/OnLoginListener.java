package com.example.caowj.newtask.module1.listener;

import com.example.caowj.newtask.module1.entity.User;

/**
 * package: com.example.caowj.newtask.module1.listener
 * author: Administrator
 * date: 2017/9/4 13:42
 */
public interface OnLoginListener {
    void loginSuccess(User user);

    void loginFailed();
}
