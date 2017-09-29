package com.example.caowj.newtask.example.mDagger;

import com.example.caowj.newtask.example.activity.TestDaggerActivity;

import dagger.Component;

/**
 * package: com.example.caowj.newtask.example.mInterface
 * author: Administrator
 * date: 2017/9/29 14:58
 */
@Component(modules = {UserModule.class})
public interface UserComponent {

    //当前只能写TestDaggerActivity，不能写Activity，要不然会出现空指针。
    void inject(TestDaggerActivity activity);
}
