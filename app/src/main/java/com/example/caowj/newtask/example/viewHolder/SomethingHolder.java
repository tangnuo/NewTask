package com.example.caowj.newtask.example.viewHolder;

import android.view.View;

import com.chad.library.adapter.base.BaseViewHolder;
import com.example.caowj.newtask.mvp.listener.BroadcastCallback;

/**
 * @Author : caowj
 * @Date : 2018/3/13
 */

public class SomethingHolder extends BaseViewHolder implements BroadcastCallback {

    public SomethingHolder(View view) {
        super(view);
    }

    @Override
    public void return1001() {

    }

    @Override
    public void return1002() {

    }

    @Override
    public void returnOther(String code) {

    }
}
