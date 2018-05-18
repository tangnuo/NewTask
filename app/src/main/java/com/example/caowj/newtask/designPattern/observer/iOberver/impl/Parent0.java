package com.example.caowj.newtask.designPattern.observer.iOberver.impl;

import android.util.Log;

import com.example.caowj.newtask.designPattern.observer.iOberver.Observer;
import com.example.caowj.newtask.designPattern.observer.iSubject.Subject;

/**
 * Created by tb on 2017/5/2.
 * 具体的观察者对象(这里就是学生家长0)
 */

public class Parent0 implements Observer {
    private static final String TAG = "Parent0";
    private Subject subject;

    public Parent0(Subject subject) {
        this.subject = subject;
        subject.addObserver(this);
    }

    @Override
    public void update(Object... obj) {
        Log.d(TAG, "update: 收到新的通知：" + obj.toString());
    }
}
