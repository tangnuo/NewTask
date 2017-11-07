package com.example.caowj.newtask.example.adapter;

import android.support.annotation.LayoutRes;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.caowj.newtask.R;

/**
 * Created by sunger on 2017/10/15.
 */

public class CommonAdapter2 extends BaseQuickAdapter<String, BaseViewHolder> {
    public CommonAdapter2(@LayoutRes int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.textView, item);
    }


}
