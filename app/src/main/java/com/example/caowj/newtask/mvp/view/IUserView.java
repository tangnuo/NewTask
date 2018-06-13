package com.example.caowj.newtask.mvp.view;

/**
 * package: com.example.caowj.newtask.module1.activity
 * author: Administrator
 * date: 2017/9/4 13:39
 */
public interface IUserView {
    public String getName();

    public String getPassword();

    public void showProgress();

    public void hideProgress();

    public void toastSuccess();

    public void toastFailed();

    void errorToast(String toastMessage);
}
