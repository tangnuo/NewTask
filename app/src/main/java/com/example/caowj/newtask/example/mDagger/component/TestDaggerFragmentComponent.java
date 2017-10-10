package com.example.caowj.newtask.example.mDagger.component;

import com.example.caowj.newtask.example.fragment.TestDaggerFragment;

import dagger.Subcomponent;

/**
 * Created by niuxiaowei on 16/3/20.
 */
//@PerActivity
@Subcomponent
public interface TestDaggerFragmentComponent {


    void inject(TestDaggerFragment mainFragment);
}
