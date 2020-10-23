package com.kedacom.module_main.mvvm.base;

import android.app.ProgressDialog;
import android.databinding.DataBindingUtil;
import android.databinding.Observable;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kedacom.module_main.mvvm.listener.DebouncingOnClickListener;


/**
 * https://github.com/ruzhan123/awaker/blob/master/app/src/main/java/com/future/awaker/base
 * Copyright ©2017 by ruzhan
 */

public abstract class BaseMvvmFragment<VB extends ViewDataBinding> extends Fragment {

    protected ProgressDialog progressDialog;

    protected VB binding;
    protected BaseViewModel viewModel;
    private RunCallBack runCallBack;

    @SuppressWarnings("unchecked")
    protected <T> T findViewById(View view, int id) {
        return (T) view.findViewById(id);
    }

    protected abstract int getLayout();

    protected void onCreateBindView() {

    }

    public void showProgressDialog(String message) {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(this.getActivity());
        }
        progressDialog.setMessage(message);
        progressDialog.show();
    }

    public void dismissProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, getLayout(), container, false);
        onCreateBindView();
        return binding.getRoot();
    }

    @Override
    public void onDestroy() {
        if (viewModel != null) {
            viewModel.isRunning.removeOnPropertyChangedCallback(runCallBack);
        }
        super.onDestroy();
    }

    protected void setToolbar(Toolbar toolbar) {
        if (toolbar == null) {
            return;
        }
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new DebouncingOnClickListener() {
            @Override
            public void doClick(View v) {
                getActivity().onBackPressed();
            }
        });

        ActionBar actionBar = activity.getSupportActionBar();
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

    protected void runStatusChange(boolean isRunning) {
        if (isRunning) {
            showProgressDialog("请稍等");
        } else {
            dismissProgressDialog();
        }
    }

    private class RunCallBack extends Observable.OnPropertyChangedCallback {

        @Override
        public void onPropertyChanged(Observable sender, int propertyId) {
            runStatusChange(viewModel.isRunning.get());
        }
    }
}

