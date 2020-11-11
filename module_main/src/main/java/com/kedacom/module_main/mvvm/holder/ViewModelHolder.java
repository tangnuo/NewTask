package com.kedacom.module_main.mvvm.holder;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * Copyright ©2017 by ruzhan
 */

public class ViewModelHolder<VM> extends Fragment {

    private VM viewModel;

    public ViewModelHolder() {
    }

    public static <M> ViewModelHolder createContainer(M viewModel) {
        ViewModelHolder<M> viewModelContainer = new ViewModelHolder<M>();
        viewModelContainer.setViewModel(viewModel);
        return viewModelContainer;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    public VM getViewModel() {
        return viewModel;
    }

    public void setViewModel(VM viewModel) {
        this.viewModel = viewModel;
    }
}
