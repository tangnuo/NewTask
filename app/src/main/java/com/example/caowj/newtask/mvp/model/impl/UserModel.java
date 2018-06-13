package com.example.caowj.newtask.mvp.model.impl;

import com.example.caowj.newtask.mvp.entity.User;
import com.example.caowj.newtask.mvp.listener.OnLoginListener;
import com.example.caowj.newtask.mvp.model.IUserModel;

/**
 * package: com.example.caowj.newtask.module1.model.impl
 * author: Administrator
 * date: 2017/9/4 13:54
 */
public class UserModel implements IUserModel {
    @Override
    public void login(final String name, final String password, final OnLoginListener loginListener) {
        //模拟子线程耗时操作
        new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //模拟登录成功
                if ("cao".equals(name) && "123".equals(password)) {
                    User user = new User();
                    user.setName(name);
                    user.setPassword(password);
                    loginListener.loginSuccess(user);
                } else {
                    loginListener.loginFailed();
                }
            }
        }.start();
    }
}
