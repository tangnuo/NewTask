package com.example.caowj.newtask.module1.activity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.caowj.newtask.R;
import com.example.caowj.newtask.base.BaseActivity;
import com.example.caowj.newtask.example.bean.ChoiceArticle;
import com.example.caowj.newtask.module1.ItemViewBinder.ADInfoList;
import com.example.caowj.newtask.module1.ItemViewBinder.ADInfoListViewBinder;
import com.example.caowj.newtask.module1.ItemViewBinder.ChoiceArticleListViewBinder;
import com.example.caowj.newtask.module1.ItemViewBinder.ScrollNotificationList;
import com.example.caowj.newtask.module1.ItemViewBinder.ScrollNotificationListViewBinder;
import com.example.caowj.newtask.module1.listener.BroadcastCallback;
import com.example.caowj.newtask.module1.presenter.BasePresenter;
import com.example.caowj.newtask.module1.presenter.impl.IndexPresenterImpl;
import com.example.caowj.newtask.module1.view.BaseView;
import com.example.caowj.newtask.other.OnLoadMoreListener;
import com.example.caowj.newtask.utils.LogUtil;
import com.example.caowj.newtask.utils.business.MyAndroidUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import me.drakeet.multitype.MultiTypeAdapter;

/**
 * 重构启拍首页的功能
 * <p>
 * 特点：<br/>
 * 1、MVP架构<br/>
 * 2、SwipeRefreshLayout+RecyclerView自动加载更多<br/>
 * 3、MultiType3.0简化adapter，实现多Type布局<br/>
 * 4、Retrofit2+Rxjava2实现网络请求和数据重构<br/>
 * </p>
 */
public class QipaiIndexActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener, BaseView.IndexView {

    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerView;
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    BasePresenter.IndexPresenter indexPresenter;

    /**
     * 注意：建议通过Adapter来维护数据List，而是自己在Adapter之外维护一个List，以便于增删查改。Adapter不负责List的数据维护，只负责List与View的绑定。
     * <p>
     * https://github.com/drakeet/MultiType/issues/155
     * </p>
     */
    private MultiTypeAdapter mAdapter;
    private List<Object> listData;

    private int pageIndex = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initWidget() {
        listData = new ArrayList<>();
        indexPresenter = new IndexPresenterImpl(this);


        //设置刷新时动画的颜色，可以设置4个
        if (mSwipeRefreshLayout != null) {
            mSwipeRefreshLayout.setProgressViewOffset(false, 0, 60);
            mSwipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_red_light, android.R.color.holo_orange_light, android.R.color.holo_green_light);
            mSwipeRefreshLayout.setOnRefreshListener(this);
        }

        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);

        mAdapter = new MultiTypeAdapter(listData);
        mAdapter.register(ADInfoList.class, new ADInfoListViewBinder(mActivity));
        mAdapter.register(ScrollNotificationList.class, new ScrollNotificationListViewBinder(mActivity));
        mAdapter.register(ChoiceArticle.class, new ChoiceArticleListViewBinder(mActivity));
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addOnScrollListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                LogUtil.myD(mTag + "加载更多");
//                        index+=1;
                loadMoreDate();
            }
        });

    }


    private void loadMoreDate() {
        pageIndex += 1;
        indexPresenter.getMoreInfoP(pageIndex);
    }

    @Override
    protected void initData() {
        pageIndex = 1;
        listData.clear();

        indexPresenter.getFixedInfoP();
//        indexPresenter.getNotificationP();
//        indexPresenter.getAdInfoP();
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_qipai_index;
    }

    @Override
    protected void memoryRecovery() {

    }

    @Override
    public void onRefresh() {
        initData();
        LogUtil.myD(mTag + "开始刷新");
    }


    @Override
    public void netWorkError() {

    }

    @Override
    public void hideProgress() {
        mSwipeRefreshLayout.setRefreshing(false);
    }


    @Override
    public void showProgress() {
        mSwipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void showMoreInfoV(List<Object> infoList) {
        LogUtil.myD(mTag + "showMore:" + infoList.size());
        listData.addAll(infoList);
        mAdapter.notifyDataSetChanged();
    }

    /************************************************************/

    @Override
    public void showFixedInfoV(List<Object> infoList) {
        listData.addAll(infoList);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void showAdInfoV(final ADInfoList adInfoList) {
        String code = adInfoList.getCode();
        MyAndroidUtils.handleBroadcastReturn(code, new BroadcastCallback() {
            @Override
            public void return1001() {
                listData.add(adInfoList);
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void return1002() {
                LogUtil.myD("获取数据失败1002。。。");
            }

            @Override
            public void returnOther(String code) {
                MyAndroidUtils.returnCodePrompt(mActivity, code, null);
            }
        });
    }

    @Override
    public void showNotificationV(final ScrollNotificationList notificationList) {
        String code = notificationList.getCode();
        MyAndroidUtils.handleBroadcastReturn(code, new BroadcastCallback() {
            @Override
            public void return1001() {
                listData.add(notificationList);
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void return1002() {
                LogUtil.myD("获取数据失败1002。。。");
            }

            @Override
            public void returnOther(String code) {
                MyAndroidUtils.returnCodePrompt(mActivity, code, null);
            }
        });
    }

}
