package com.example.caowj.newtask.toutiao.module.news.comment;

import com.example.caowj.newtask.toutiao.module.base.IBaseListView;
import com.example.caowj.newtask.toutiao.module.base.IBasePresenter;
import com.example.caowj.newtask.toutiao.bean.news.NewsCommentBean;

import java.util.List;

/**
 * Created by Meiji on 2016/12/20.
 */

public interface INewsComment {

    interface View extends IBaseListView<Presenter> {

        /**
         * 请求数据
         */
        void onLoadData();
    }

    interface Presenter extends IBasePresenter {

        /**
         * 请求数据
         */
        void doLoadData(String... groupId_ItemId);

        /**
         * 再起请求数据
         */
        void doLoadMoreData();

        /**
         * 设置适配器
         */
        void doSetAdapter(List<NewsCommentBean.DataBean.CommentBean> list);

        /**
         * 加载完毕
         */
        void doShowNoMore();
    }
}
