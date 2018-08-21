package com.kedacom.module_common.base.mvp;

/**
 * Created by Meiji on 2017/5/7.
 */

public interface IBasePresenter {

    /**
     * 刷新数据
     */
    void doRefresh();

    /**
     * 显示网络错误
     */
    void doShowNetError();
}
