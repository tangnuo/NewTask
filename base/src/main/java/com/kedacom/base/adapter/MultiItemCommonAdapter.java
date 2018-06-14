package com.kedacom.base.adapter;

import android.content.Context;
import android.view.ViewGroup;

import java.util.List;

/**
 * package: com.example.caowj.newtask.base
 * author: Administrator
 * date: 2017/9/14 10:01
 */
public abstract class MultiItemCommonAdapter<T> extends CommonAdapter<T> {
    protected MultiItemTypeSupport<T> mMultiItemTypeSupport;

    public MultiItemCommonAdapter(Context context, List<T> datas,
                                  MultiItemTypeSupport<T> multiItemTypeSupport) {
        super(context, -1, datas);
        mMultiItemTypeSupport = multiItemTypeSupport;
    }

    @Override
    public int getItemViewType(int position) {
        return mMultiItemTypeSupport.getItemViewType(position, mDatas.get(position));
    }

    @Override
    public CommonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int layoutId = mMultiItemTypeSupport.getLayoutId(viewType);
        CommonViewHolder holder = CommonViewHolder.get(mContext, parent, layoutId);
        return holder;
    }

}
