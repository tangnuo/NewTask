package com.example.caowj.newtask.example.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.caowj.newtask.R;
import com.example.caowj.newtask.example.adapter.TaoBaoDetailAdater;
import com.kedacom.base.mvc.BaseActivity1;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 标题和列表联动（淘宝详情页）
 * 导航栏不适合使用TabLayout，因为设置标题样式的时候会触发点击事件，冲突了。
 */
public class TestTaoBaoDetailActivity extends BaseActivity1 {
    @BindView(R.id.mRecyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.tv_title1)
    TextView tvTitle1;
    @BindView(R.id.tv_title2)
    TextView tvTitle2;
    @BindView(R.id.tv_title3)
    TextView tvTitle3;

    private TaoBaoDetailAdater detailAdater;
    private List<String> dataList1 = new ArrayList<>();
    private List<String> dataList2 = new ArrayList<>();
    private List<String> dataList3 = new ArrayList<>();
    private int colors[] = new int[2];
    private LinearLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initRecyclerView();
    }

    private void initRecyclerView() {
        colors[0] = getResources().getColor(R.color.black);
        colors[1] = getResources().getColor(R.color.red);

        for (int i = 0; i < 20; i++) {
            dataList1.add("type1,count:" + i);
            dataList2.add("type2,count:" + i);
            dataList3.add("type3,count:" + i);
        }
        layoutManager = new LinearLayoutManager(mActivity);
        mRecyclerView.setLayoutManager(layoutManager);
        detailAdater = new TaoBaoDetailAdater(dataList1);

        mRecyclerView.setAdapter(detailAdater);
        detailAdater.setDataList2(dataList2);
        detailAdater.setDataList3(dataList3);
        detailAdater.notifyDataSetChanged();

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager layoutManager1 = (LinearLayoutManager) recyclerView.getLayoutManager();
                int firstVisible = layoutManager1.findFirstVisibleItemPosition();

                tvTitle1.setTextColor(colors[0]);
                tvTitle2.setTextColor(colors[0]);
                tvTitle3.setTextColor(colors[0]);

                //TODO 注意：如果最后一项数据太少，不能达到一屏，可能永远到不了最后一项。
                if (detailAdater.isFirstType(firstVisible)) {
                    tvTitle1.setTextColor(colors[1]);
                } else if (detailAdater.isSecondType(firstVisible)) {
                    tvTitle2.setTextColor(colors[1]);
                } else if (detailAdater.isThirdType(firstVisible)) {
                    tvTitle3.setTextColor(colors[1]);
                }
            }
        });
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_test_tao_bao_detail;
    }

    @Override
    protected void memoryRecovery() {

    }


    @OnClick({R.id.tv_title1, R.id.tv_title2, R.id.tv_title3})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_title1:
                mRecyclerView.scrollToPosition(0);

                break;
            case R.id.tv_title2:
                int i = dataList1.size();

                layoutManager.scrollToPositionWithOffset(i, 0);
                layoutManager.setStackFromEnd(true);//不要这行也有效果，不知道为什么
                break;
            case R.id.tv_title3:

                int ss = dataList1.size() + dataList2.size();

                layoutManager.scrollToPositionWithOffset(ss, 0);
                layoutManager.setStackFromEnd(true);//不要这行也有效果，不知道为什么

                break;
        }
    }
}
