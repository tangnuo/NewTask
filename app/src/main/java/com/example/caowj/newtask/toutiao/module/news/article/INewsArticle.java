package com.example.caowj.newtask.toutiao.module.news.article;


import com.example.caowj.newtask.toutiao.bean.news.MultiNewsArticleDataBean;
import com.kedacom.base.mvp.IBaseListView;
import com.kedacom.base.mvp.IBasePresenter;

import java.util.List;

/**
 * Created by Meiji on 2017/5/18.
 */

public interface INewsArticle {

    interface View extends IBaseListView<Presenter> {

        /**
         * 请求数据
         */
        void onLoadData();

        /**
         * 刷新
         */
        void onRefresh();
    }

    interface Presenter extends IBasePresenter {

        /**
         * 请求数据
         */
        void doLoadData(String... category);

        /**
         * 再起请求数据
         */
        void doLoadMoreData();

        /**
         * 设置适配器
         */
        void doSetAdapter(List<MultiNewsArticleDataBean> dataBeen);

        /**
         * 加载完毕
         */
        void doShowNoMore();
    }
}
