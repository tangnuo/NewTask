package com.example.caowj.newtask.example.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.caowj.newtask.R;
import com.example.caowj.newtask.base.BaseActivity;
import com.example.caowj.newtask.widget.TimeTextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 倒计时控件
 */
public class TestCountdownActivity extends BaseActivity {

    @BindView(R.id.mRecyclerView)
    RecyclerView mRecyclerView;
    CountdownAdapter audioAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_test_countdown;
    }

    @Override
    protected void memoryRecovery() {

    }

    @Override
    protected void initData() {
        super.initData();

        mRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        audioAdapter = new CountdownAdapter();
        mRecyclerView.setAdapter(audioAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }


    class CountdownAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        List<Long> musicList = new ArrayList<>();

        public CountdownAdapter() {
            //2018-03-22 17:21:51
            musicList.add(1521688911L);
            musicList.add(1521692511L);
            musicList.add(1521696111L);
            musicList.add(1521699711L);
            musicList.add(1521703311L);
            musicList.add(1521710511L);

            //2019-03-22 17:21:51
            musicList.add(1553246511L);
            musicList.add(1553242911L);
            musicList.add(1553239311L);
            musicList.add(1553235711L);
            musicList.add(1553228511L);

        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new CountdownAdapter.PlayViewHolder(LayoutInflater.from(mActivity).inflate(R.layout.item_countdown, null, false));
        }

        @Override
        public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {

            if (holder instanceof CountdownAdapter.PlayViewHolder) {
                long time = musicList.get(position);
                ((PlayViewHolder) holder).tvTitle.setTimes(time);
            }
        }

        @Override
        public int getItemCount() {
            return musicList.size();
        }


        class PlayViewHolder extends RecyclerView.ViewHolder {
            @BindView(R.id.tv_title)
            TimeTextView tvTitle;

            PlayViewHolder(View view) {
                super(view);
                ButterKnife.bind(this, view);
            }
        }
    }
}
