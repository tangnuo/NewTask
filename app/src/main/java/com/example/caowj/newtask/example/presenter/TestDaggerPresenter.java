package com.example.caowj.newtask.example.presenter;


import com.example.caowj.newtask.example.bean.Person;
import com.example.caowj.newtask.example.mDagger.GetUserData;

import javax.inject.Inject;

/**
 * Created by niuxiaowei on 16/3/20.
 */
public class TestDaggerPresenter {

    public GetUserData mUserData;
    private IUserView mUserView;


    @Inject
    public TestDaggerPresenter(GetUserData userData) {
        this.mUserData = userData;
    }

    public void getUser() {
        Person userData = this.mUserData.getUser();
        this.mUserView.setUserName(userData.getName());
    }

    public void setUserView(IUserView userView) {
        this.mUserView = userView;
    }

    public interface IUserView {
        void setUserName(String name);
    }
}
