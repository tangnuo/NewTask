package com.example.caowj.newtask.example.mDagger.component;

import com.example.caowj.newtask.example.activity.TestDaggerActivity;
import com.example.caowj.newtask.example.mDagger.module.UserModule;

import dagger.Component;

/**
 * package: com.example.caowj.newtask.example.mDagger.component
 * author: Administrator
 * date: 2017/9/29 17:28
 */
@Component(modules = {UserModule.class})
public interface UserComponent {

    //当前只能写TestDaggerActivity，不能写Activity，要不然会出现空指针。
    void inject(TestDaggerActivity activity);
}
