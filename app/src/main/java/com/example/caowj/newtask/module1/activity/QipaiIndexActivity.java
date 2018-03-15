package com.example.caowj.newtask.module1.activity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.caowj.newtask.R;
import com.example.caowj.newtask.base.BaseActivity;
import com.example.caowj.newtask.module1.ItemViewBinder.ADInfoList;
import com.example.caowj.newtask.module1.ItemViewBinder.ADInfoListViewBinder;
import com.example.caowj.newtask.module1.listener.BroadcastCallback;
import com.example.caowj.newtask.module1.presenter.BasePresenter;
import com.example.caowj.newtask.module1.presenter.impl.IndexPresenterImpl;
import com.example.caowj.newtask.module1.view.BaseView;
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
 * 4、Retrofit+Rxjava实现网络请求和数据重构<br/>
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
    private int refreshTime = 0;
    private int times = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initWidget() {
        listData = new ArrayList<>();
        indexPresenter = new IndexPresenterImpl(this);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);

//        mRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
//        mRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);
//        mRecyclerView.setArrowImageView(R.drawable.iconfont_downgrey);

//        View header = LayoutInflater.from(this).inflate(R.layout.item_recyclerview_header, (ViewGroup) findViewById(android.R.id.content), false);
//        mRecyclerView.addHeaderView(header);

//        mRecyclerView.setPullRefreshEnabled(false);

        //设置刷新时动画的颜色，可以设置4个
        if (mSwipeRefreshLayout != null) {
            mSwipeRefreshLayout.setProgressViewOffset(false, 0, 60);
            mSwipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_red_light, android.R.color.holo_orange_light, android.R.color.holo_green_light);
            mSwipeRefreshLayout.setOnRefreshListener(this);
        }


//        mRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
//            @Override
//            public void onRefresh() {
//                initData();        //refresh data here
//            }
//
//            @Override
//            public void onLoadMore() {
////                if (times < 2) {
////                    new Handler().postDelayed(new Runnable() {
////                        public void run() {
////                            for (int i = 0; i < 15; i++) {
////                                listData.add("item" + (1 + listData.size()));
////                            }
////                            mRecyclerView.loadMoreComplete();
////                            mAdapter.notifyDataSetChanged();
////                        }
////                    }, 1000);
////                } else {
////                    new Handler().postDelayed(new Runnable() {
////                        public void run() {
////                            for (int i = 0; i < 9; i++) {
////                                listData.add("item" + (1 + listData.size()));
////                            }
////                            mRecyclerView.setNoMore(true);
////                            mAdapter.notifyDataSetChanged();
////                        }
////                    }, 1000);
////                }
////                times++;
//            }
//        });

        mAdapter = new MultiTypeAdapter(listData);
        mAdapter.register(ADInfoList.class, new ADInfoListViewBinder(mActivity));
//        mAdapter.register(ADInfoList.class, new ADInfoListViewBinder(mActivity));
        mRecyclerView.setAdapter(mAdapter);


//        mAdapter.setItems();
//        mAdapter.notifyDataSetChanged();
    }

    @Override
    protected void initData() {

        indexPresenter.getAdInfoP();
//        refreshTime++;
//        times = 0;
//        new Handler().postDelayed(new Runnable() {
//            public void run() {
//                listData.clear();
//                for (int i = 0; i < 10; i++) {
//                    listData.add("item" + i + "after " + refreshTime + " times of refresh");
//                }
//                mAdapter.notifyDataSetChanged();
//                mRecyclerView.refreshComplete();
//                mSwipeRefreshLayout.setRefreshing(false);
//            }
//
//        }, 1000);            //refresh data here
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
        mSwipeRefreshLayout.setRefreshing(true);
        initData();
        Log.d("caowj", "mSwipeRefreshLayout is ok");
    }


    @Override
    public void netWorkError() {

    }

    @Override
    public void hideProgress() {

    }


    /************************************************************/

    @Override
    public void showProgress() {

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

}
