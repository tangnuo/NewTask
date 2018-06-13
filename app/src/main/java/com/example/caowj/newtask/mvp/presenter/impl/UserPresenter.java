package com.example.caowj.newtask.mvp.presenter.impl;

import android.os.Handler;
import android.text.TextUtils;

import com.example.caowj.newtask.mvp.entity.User;
import com.example.caowj.newtask.mvp.listener.OnLoginListener;
import com.example.caowj.newtask.mvp.model.IUserModel;
import com.example.caowj.newtask.mvp.model.impl.UserModel;
import com.example.caowj.newtask.mvp.view.IUserView;

/**
 * Presenter层<br/>
 * 可以在这里放一些逻辑判断，标签验证等。
 * package: com.example.caowj.newtask.module1.presenter.impl
 * author: Administrator
 * date: 2017/9/4 13:40
 */
public class UserPresenter {
    private IUserView view;
    private IUserModel model;
    private Handler mHandler = new Handler();

    public UserPresenter(IUserView view) {
        this.view = view;
        this.model = new UserModel();
    }

    public void login() {
        view.showProgress();
        String name = view.getName();
        String password = view.getPassword();
        if (TextUtils.isEmpty(name)) {
            view.errorToast("用户名不能为空");
            view.hideProgress();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            view.errorToast("密码不能为空");
            view.hideProgress();
            return;
        }

        model.login(view.getName(), view.getPassword(), new OnLoginListener() {
            @Override
            public void loginSuccess(User user) {
//需要在UI线程执行
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        view.toastSuccess();
                        view.hideProgress();
                    }
                });
            }

            @Override
            public void loginFailed() {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        view.toastFailed();
                        view.hideProgress();
                    }
                });
            }
        });
    }
}
