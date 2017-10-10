package com.example.caowj.newtask.example.mDagger.component;


import com.example.caowj.newtask.example.activity.TestDaggerActivity3;
import com.example.caowj.newtask.example.mDagger.module.ActivityModule;
import com.example.caowj.newtask.example.mDagger.module.TestDaggerModule;
import com.example.caowj.newtask.example.mDagger.scopes.PerActivity;

import dagger.Component;

/**
 * MainComponent继承了ActivityComponent，假如ActivityComponent中定义了创建类实例方法，则MainComponent中必须提供@Inject或@Provides对应的
 * 初始化类实例的方法
 * Created by niuxiaowei on 16/3/20.
 */
@PerActivity
@Component(dependencies = AppComponent.class, modules = {TestDaggerModule.class, ActivityModule.class})
public interface TestDaggerComponent extends ActivityComponent {
    //对TestDaggerActivity3进行依赖注入
    void inject(TestDaggerActivity3 mainActivity);


    TestDaggerFragmentComponent mainFragmentComponent();
}
