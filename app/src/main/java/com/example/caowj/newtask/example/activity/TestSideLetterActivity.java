package com.example.caowj.newtask.example.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.caowj.newtask.R;
import com.kedacom.customview.view.SideLetterBar;
import com.kedacom.utils.LogUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 列表索引
 */
public class TestSideLetterActivity extends AppCompatActivity {

    @BindView(R.id.side)
    SideLetterBar mSide;
    @BindView(R.id.tv_letter_overlay)
    TextView mTvLetterOverlay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_side_letter);
        ButterKnife.bind(this);

        initData();
    }

    private void initData() {
        mSide.setOverlay(mTvLetterOverlay);
        mSide.setOnLetterChangedListener(new SideLetterBar.OnLetterChangedListener() {
            @Override
            public void onLetterChanged(String letter) {
//                linearLayoutManager.scrollToPositionWithOffset(getLetterPosition(letter, allInfoDatas), 0);
                //这里可以做一些关联的搜索，同步操作。
                LogUtil.myD("letter:" + letter);
            }
        });
    }
}
