package com.example.caowj.newtask.toutiao.module.news.content;


import com.example.caowj.newtask.toutiao.module.base.IBasePresenter;
import com.example.caowj.newtask.toutiao.module.base.IBaseView;
import com.example.caowj.newtask.toutiao.bean.news.MultiNewsArticleDataBean;

/**
 * Created by Meiji on 2016/12/17.
 */

interface INewsContent {

    interface View extends IBaseView<Presenter> {

        /**
         * 加载网页
         */
        void onSetWebView(String url, boolean flag);
    }

    interface Presenter extends IBasePresenter {

        /**
         * 请求数据
         */
        void doLoadData(MultiNewsArticleDataBean dataBean);
    }
}
