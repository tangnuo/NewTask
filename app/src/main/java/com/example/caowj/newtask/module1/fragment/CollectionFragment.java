package com.example.caowj.newtask.module1.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.caowj.newtask.R;
import com.example.caowj.newtask.base.BaseFragment;
import com.example.caowj.newtask.module1.adapter.CollectionAdapter;
import com.example.caowj.newtask.module1.entity.PaiPinInfo;
import com.example.caowj.newtask.module1.entity.bean.NavigationBean;
import com.example.caowj.newtask.module1.presenter.BasePresenter;
import com.example.caowj.newtask.module1.presenter.impl.TabNamePresenterImpl;
import com.example.caowj.newtask.module1.view.BaseView;
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


    //当前页
    private int pageIndex = 1;
    //是否继续加载
    private boolean isLoading = true;
    //分类下标的Id 默认选择全部0
    private int typeId;
    //选中下标位置
    private int index;
    private CollectionAdapter adapter;
    //分类显示的商品信息
    private List<PaiPinInfo> fixedAuctionList = new ArrayList<>();
    //获取分类集合
    private List<NavigationBean> navigationInfoList = new ArrayList<>();
    //获取标题内容
    private List<String> titles = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);

        adapter = new CollectionAdapter(mActivity, fixedAuctionList);
        rvList.setLayoutManager(new LinearLayoutManager(mActivity));
        rvList.setAdapter(adapter);
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        BasePresenter.TabNamePresenter tabNamePresenter = new TabNamePresenterImpl(mActivity, this);
        tabNamePresenter.requestNetWork();
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

    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    /***************************************************/

    @Override
    public void setData(List datas) {

    }

    @Override
    public void netWorkError() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void showFoot() {

    }

    @Override
    public void hideFoot() {

    }

    @Override
    public void switchNews() {

    }

    @Override
    public void setTitleData(List<NavigationBean> titleList) {
        //将分类集合添加到Tab中
        boolean isSelected;//下标是否选中
        String title;//下标标题名称
        int tempID;//临时下标Id
        for (int i = 0; i < titleList.size(); i++) {
            tempID = titleList.get(i).getId();
            title = titleList.get(i).getCateName();
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

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    /***************************************************/
}
