package com.example.caowj.newtask.example.mDagger;

import com.example.caowj.newtask.example.bean.Person;

import javax.inject.Inject;

/**
 * package: com.example.caowj.newtask.example.mDagger
 * author: Administrator
 * date: 2017/10/9 11:18
 */
public class GetUserData {
    @Inject
    public GetUserData() {

    }

    public Person getUser() {
        Person data = new Person("曹维健");
        return data;
    }
}
