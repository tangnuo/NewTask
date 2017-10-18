package com.example.caowj.newtask.module1.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.caowj.newtask.R;
import com.example.caowj.newtask.base.BaseFragment;
import com.example.caowj.newtask.module1.adapter.CollectionAdapter;
import com.example.caowj.newtask.module1.constants.TypeConstants;
import com.example.caowj.newtask.module1.entity.bean.NavigationBean;
import com.example.caowj.newtask.module1.entity.bean.PaiPinBean;
import com.example.caowj.newtask.module1.presenter.BasePresenter;
import com.example.caowj.newtask.module1.presenter.impl.TabNamePresenterImpl;
import com.example.caowj.newtask.module1.view.BaseView;
import com.example.caowj.newtask.utils.LogUtil;
import com.example.caowj.newtask.utils.business.CommonTools;
import com.example.caowj.newtask.utils.business.MyAndroidUtils;
import com.example.caowj.newtask.widget.SlideRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Pan S.Q
 * on 2017/5/4.
 * 雅集
 * <p>
 * http://admin.qipaiapp.com/QiPaiAPI/PaipinInfo.asmx?op=GetListYaWu 筛选条件
 */

public class CollectionFragment extends BaseFragment implements TabLayout.OnTabSelectedListener, BaseView.TabNameView {
    @BindView(R.id.tl_nav)
    TabLayout tlNav;
    @BindView(R.id.rv_list)
    SlideRecyclerView rvList;
    Unbinder unbinder;
    @BindView(R.id.loading_progress)
    ProgressBar loadingProgress;
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;


    //分类下标的Id 默认选择全部0
    private int typeId;
    //选中下标位置
    private int index;
    private CollectionAdapter adapter;
    //获取分类集合
    private List<NavigationBean> navigationInfoList = new ArrayList<>();
    //获取标题内容
    private List<String> titles = new ArrayList<>();
    BasePresenter.TabNamePresenter tabNamePresenter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);

        adapter = new CollectionAdapter(mActivity, null);
        final GridLayoutManager layoutManager = new GridLayoutManager(mActivity, 2);
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return adapter.getItemViewType(position) == TypeConstants.TYPE_ITEM_NONE ? layoutManager.getSpanCount() : 1;
            }
        });
        rvList.setLayoutManager(layoutManager);
        rvList.setAdapter(adapter);
        rvList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) //向下滚动
                {
                    int visibleItemCount = layoutManager.getChildCount();
                    int totalItemCount = layoutManager.getItemCount();
                    int pastVisiblesItems = layoutManager.findFirstVisibleItemPosition();
//                    LogUtil.myD(mTag+"visibleItemCount:"+visibleItemCount+",totalItemCount:"+totalItemCount+",pastVisiblesItems:"+pastVisiblesItems);

                    //TODO 可能判断的不完善。
                    if ((visibleItemCount + pastVisiblesItems) >= totalItemCount) {
                        tabNamePresenter.onLoadMoreBegin();
                    }
                }
            }
        });

        // 设置下拉进度的背景颜色，默认就是白色的
        swipeRefreshLayout.setProgressBackgroundColorSchemeResource(android.R.color.white);
        // 设置下拉进度的主题颜色
        swipeRefreshLayout.setColorSchemeResources(R.color.colorAccent, R.color.colorPrimary, R.color.colorPrimaryDark);

        // 下拉时触发SwipeRefreshLayout的下拉动画，动画完毕之后就会回调这个方法
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                LogUtil.myD(mTag + "开始刷新。。");
                tabNamePresenter.onLoadMoreBegin();
            }
        });


        tlNav.addOnTabSelectedListener(this);
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        tabNamePresenter = new TabNamePresenterImpl(mActivity, this);
        tabNamePresenter.getNavigationP();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    @Override
    protected int getContentView() {
        return R.layout.collection_fragment_layout;
    }


    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        //2.当前Tab选中的位置
        index = tab.getPosition();
        //3.当前下标的ID
        typeId = navigationInfoList.get(index).getId();
        LogUtil.d(mTag, "onTabSelected-->当前下标位置：" + index + "\ttypeId:" + typeId);
        //4.获取当前类别Id的数据
//        setInitTypeData();
        tabNamePresenter.getDataByTypeP(typeId);
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    /***************************************************/

    @Override
    public void netWorkError() {
        hideProgress();
        MyAndroidUtils.showShortToast(mActivity, "网络请求失败22");
    }

    @Override
    public void hideProgress() {
        loadingProgress.setVisibility(View.GONE);
    }

    @Override
    public void showProgress() {
        loadingProgress.setVisibility(View.VISIBLE);
    }


    @Override
    public void showNavigationV(List<NavigationBean> titleList) {
        navigationInfoList = titleList;

        titles = new ArrayList<>();
        //将分类集合添加到Tab中
        boolean isSelected;//下标是否选中
        String title;//下标标题名称
        int tempID;//临时下标Id
        for (int i = 0; i < titleList.size(); i++) {
            tempID = titleList.get(i).getId();
            title = titleList.get(i).getCateName();
            titles.add(title);
            //表示当前下标Id被选中
            if (typeId == tempID) {
                typeId = tempID;
                index = i;
                isSelected = true;
            } else {
                isSelected = false;
            }
            tlNav.addTab(tlNav.newTab().setText(title), i, isSelected);
        }

        //当下标移动到指定到分类则会调用onTabSelected()方法
        CommonTools.recomputeOffset(mActivity, tlNav, index, titles);

    }

    @Override
    public void showPaipinInfoV(List<PaiPinBean> paiPinBeanList) {
        swipeRefreshLayout.setRefreshing(false);
        adapter.setPaiPinInfoList(paiPinBeanList);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    /***************************************************/
}
