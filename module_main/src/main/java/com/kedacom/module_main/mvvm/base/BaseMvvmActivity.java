package com.kedacom.module_main.mvvm.base;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.Observable;
import androidx.databinding.ViewDataBinding;

import com.kedacom.module_common.common.BaseActivity;
import com.kedacom.module_main.mvvm.listener.DebouncingOnClickListener;


/**
 * https://github.com/ruzhan123/awaker/blob/master/app/src/main/java/com/future/awaker/base/BaseActivity.java
 * Created by ruzhan on 2017/7/15.
 */

public abstract class BaseMvvmActivity<VB extends ViewDataBinding>
        extends BaseActivity {

    protected VB binding;
    protected BaseViewModel viewModel;
    private RunCallBack runCallBack;

    @SuppressWarnings("unchecked")
    protected <T> T findViewById(View view, int id) {
        return (T) view.findViewById(id);
    }

    protected abstract int getLayout();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, getLayout());
    }

    @Override
    protected void onDestroy() {
        if (viewModel != null) {
            viewModel.isRunning.removeOnPropertyChangedCallback(runCallBack);
        }
        super.onDestroy();
    }

    protected void setToolbar(Toolbar toolbar) {
        if (toolbar == null) {
            return;
        }
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new DebouncingOnClickListener() {
            @Override
            public void doClick(View v) {
                finish();
            }
        });

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    public void addRunStatusChangeCallBack(BaseViewModel viewModel) {
        if (viewModel == null) {
            return;
        }
        this.viewModel = viewModel;
        if (runCallBack == null) {
            runCallBack = new RunCallBack();
        }
        this.viewModel.isRunning.addOnPropertyChangedCallback(runCallBack);
    }

    protected void runStatusChange() {

    }

    private class RunCallBack extends Observable.OnPropertyChangedCallback {

        @Override
        public void onPropertyChanged(Observable sender, int propertyId) {
            runStatusChange();
        }
    }
}
