package com.example.caowj.newtask.example.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.AppCompatSeekBar;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.caowj.newtask.R;
import com.example.caowj.newtask.example.fragment.MasterDetailShowFragment;
import com.kedacom.base.common.BasePagerAdapter;
import com.kedacom.base.mvc.BaseButterKnifeActivity;
import com.kedacom.customview.headerScrollView.HeaderScrollHelper;
import com.kedacom.customview.headerScrollView.HeaderScrollView;
import com.kedacom.utils.LogUtil;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 微博详情页，在{@link TestWeiboDetailActivity}的基础上继续深化。
 * <p>
 * HeaderScrollView + ViewPager + RecyclerView.
 * 如果配合刷新控件，最好的解决办法就是将加载更多放入RecyclerView中；针对不同的Fragment，请求不同的数据，降低耦合度。
 * </p>
 */
public class TestMasterVoiceActivity extends BaseButterKnifeActivity implements HeaderScrollHelper.ScrollableContainer {
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.iv_photo)
    CircleImageView ivPhoto;
    @BindView(R.id.seekbar)
    AppCompatSeekBar seekbar;
    @BindView(R.id.tv_play_progress)
    TextView tvPlayProgress;
    @BindView(R.id.tv_total_time)
    TextView tvTotalTime;
    @BindView(R.id.ll_container)
    RelativeLayout llContainer;
    @BindView(R.id.tab)
    TabLayout tab;
    @BindView(R.id.vps)
    ViewPager vps;
    @BindView(R.id.view_hover)
    HeaderScrollView viewHover;

    MasterDetailShowFragment stickHeaderFragment1;
    MasterDetailShowFragment stickHeaderFragment2;
    MasterDetailShowFragment stickHeaderFragment3;

    RecyclerView mRecyclerView;
    private BasePagerAdapter viewPagerAdapter;
    private int position = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //关键点
        viewHover.setCurrentScrollableContainer(this);

        stickHeaderFragment1 = new MasterDetailShowFragment();
        stickHeaderFragment2 = new MasterDetailShowFragment();
        stickHeaderFragment3 = new MasterDetailShowFragment();

        viewPagerAdapter = new BasePagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addItem(stickHeaderFragment1, "Tab1");
        viewPagerAdapter.addItem(stickHeaderFragment2, "Tab2");
        viewPagerAdapter.addItem(stickHeaderFragment3, "Tab3");
        vps.setAdapter(viewPagerAdapter);
        tab.setupWithViewPager(vps);

        tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                position = tab.getPosition();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    @Override
    protected int getContentView() {
        return R.layout.activity_test_master_voice;
    }

    @Override
    protected void memoryRecovery() {

    }

    @Override
    public View getScrollableView() {
        //TODO 初始值只能等Fragment加载完成后才能获取。
        if (position == 0) {
            mRecyclerView = stickHeaderFragment1.getmRecyclerView();
        } else if (position == 1) {
            mRecyclerView = stickHeaderFragment2.getmRecyclerView();
        } else if (position == 2) {
            mRecyclerView = stickHeaderFragment3.getmRecyclerView();
        } else {
            LogUtil.myW("未知的position:" + position);
        }

        return mRecyclerView;
    }

    @OnClick({R.id.iv_photo})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_photo:
                break;

        }
    }
}
