package com.kedacom.module_main.mvp.presenter;

import com.kedacom.module_main.mvp.model.IBaseModel;
import com.kedacom.module_main.mvp.view.IBaseView;

/**
 * Created by Meiji on 2017/5/7.
 */

public interface IBasePresenter<V extends IBaseView, M extends IBaseModel> {

    /**
     * 刷新数据
     */
    void doRefresh();

    /**
     * 显示网络错误
     */
    void doShowNetError();

}
