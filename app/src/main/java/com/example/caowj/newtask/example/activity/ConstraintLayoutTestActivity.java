package com.example.caowj.newtask.example.activity;

import android.os.Bundle;

import com.example.caowj.newtask.R;
import com.kedacom.base.mvc.BaseButterKnifeActivity;

/**
 * 约束布局
 */
public class ConstraintLayoutTestActivity extends BaseButterKnifeActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_constraint_layout_test1;
    }

    @Override
    protected void memoryRecovery() {

    }

}
