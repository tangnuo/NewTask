package com.example.caowj.newtask.example.activity;

import android.os.Bundle;

import com.example.caowj.newtask.R;
import com.example.caowj.newtask.example.mDagger.component.DaggerTestDaggerComponent;
import com.example.caowj.newtask.example.mDagger.component.TestDaggerComponent;
import com.example.caowj.newtask.example.mDagger.module.ActivityModule;
import com.example.caowj.newtask.example.mDagger.module.TestDaggerModule;

/**
 * MVP+Dagger2
 * <p/>
 * <p>
 * 参考：https://github.com/niuxiaowei/Dagger2Sample
 * <p/>
 */
public class TestDaggerActivity3 extends BaseDaggerActivity {

    private TestDaggerComponent mMainComponent;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_dagger3);

        mMainComponent = DaggerTestDaggerComponent.builder().appComponent(getAppComponent())
                .testDaggerModule(new TestDaggerModule())
                .activityModule(new ActivityModule(this)).build();
        mMainComponent.inject(this);
    }

    public TestDaggerComponent getMainComponent() {
        return mMainComponent;
    }

}
