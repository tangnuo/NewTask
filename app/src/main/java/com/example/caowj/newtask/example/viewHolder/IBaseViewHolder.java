package com.example.caowj.newtask.example.viewHolder;

import android.view.View;

import com.chad.library.adapter.base.BaseViewHolder;

import butterknife.ButterKnife;

/**
 * 使用ButterKnife创建viewholder<br/>
 * 参考：https://github.com/CymChad/BaseRecyclerViewAdapterHelper/issues/1880
 *
 * @Author : caowj
 * @Date : 2018/3/13
 */

public abstract class IBaseViewHolder extends BaseViewHolder {
    public IBaseViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }

    public abstract <T> void updateView(T dateItem);
}
