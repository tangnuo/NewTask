package com.kedacom.module_lib.base.mvp;

import java.util.List;

/**
 * Created by Meiji on 2017/7/5.
 */

public interface IBaseListView<T> extends IBaseView<T> {

    /**
     * 显示加载动画
     */
    @Override
    void onShowLoading();

    /**
     * 隐藏加载
     */
    @Override
    void onHideLoading();

    /**
     * 显示网络错误
     */
    @Override
    void onShowNetError();

    /**
     * 设置 presenter
     */
    @Override
    void setPresenter(T presenter);

    /**
     * 设置适配器
     */
    void onSetAdapter(List<?> list);

    /**
     * 加载完毕
     */
    void onShowNoMore();
}
