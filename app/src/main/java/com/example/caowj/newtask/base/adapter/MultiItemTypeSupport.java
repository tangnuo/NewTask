package com.example.caowj.newtask.base.adapter;

/**
 * package: com.example.caowj.newtask.base.adapter
 * author: Administrator
 * date: 2017/9/14 10:02
 */
public interface MultiItemTypeSupport<T> {
    int getLayoutId(int itemType);

    int getItemViewType(int position, T t);
}
