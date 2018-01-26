package com.example.caowj.newtask.example.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.caowj.newtask.R;
import com.example.caowj.newtask.base.BaseActivity;
import com.example.caowj.newtask.other.Player;
import com.example.caowj.newtask.utils.LogUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TestAudioListActivity extends BaseActivity {

    @BindView(R.id.mRecyclerView)
    RecyclerView mRecyclerView;
    AudioAdapter audioAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_test_audio_list;
    }

    @Override
    protected void memoryRecovery() {

    }

    @Override
    protected void initData() {
        super.initData();


        mRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        audioAdapter = new AudioAdapter();
        mRecyclerView.setAdapter(audioAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        audioAdapter.memory();

    }

    class AudioAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        List<String> musicList = new ArrayList<>();
        HashMap<Integer, Player> playerHashMap = new HashMap<>();
        int lastPosition = 0;
        Button lastBtn;

        public AudioAdapter() {
            musicList.add("http://mp4.111ttt.cn/2017/xc/08/ANB.mp4?#.mp3");
            musicList.add("http://sc1.111ttt.cn:8282/2017/1/11m/11/304112003137.m4a?tflag=1516348800&pin=1d3e05b2704f5dca0b6ff2e5d040bf2c#.mp3");
            musicList.add("http://sc1.111ttt.cn:8282/2017/1/11m/11/304112004168.m4a?tflag=1516348800&pin=1d3e05b2704f5dca0b6ff2e5d040bf2c#.mp3");
            musicList.add("http://sc1.111ttt.cn:8282/2017/1/11m/11/304112002593.m4a?tflag=1516348800&pin=1d3e05b2704f5dca0b6ff2e5d040bf2c#.mp3");
            musicList.add("http://sc1.111ttt.cn:8282/2017/1/11m/11/304112002493.m4a?tflag=1516348800&pin=1d3e05b2704f5dca0b6ff2e5d040bf2c#.mp3");
            musicList.add("http://mp4.111ttt.cn/2017/xc/08/ANB.mp4?#.mp3");
            musicList.add("http://sc1.111ttt.cn:8282/2017/1/11m/11/304112003137.m4a?tflag=1516348800&pin=1d3e05b2704f5dca0b6ff2e5d040bf2c#.mp3");
            musicList.add("http://sc1.111ttt.cn:8282/2017/1/11m/11/304112004168.m4a?tflag=1516348800&pin=1d3e05b2704f5dca0b6ff2e5d040bf2c#.mp3");
            musicList.add("http://sc1.111ttt.cn:8282/2017/1/11m/11/304112002593.m4a?tflag=1516348800&pin=1d3e05b2704f5dca0b6ff2e5d040bf2c#.mp3");
            musicList.add("http://sc1.111ttt.cn:8282/2017/1/11m/11/304112002493.m4a?tflag=1516348800&pin=1d3e05b2704f5dca0b6ff2e5d040bf2c#.mp3");
            musicList.add("http://mp4.111ttt.cn/2017/xc/08/ANB.mp4?#.mp3");
            musicList.add("http://sc1.111ttt.cn:8282/2017/1/11m/11/304112003137.m4a?tflag=1516348800&pin=1d3e05b2704f5dca0b6ff2e5d040bf2c#.mp3");
            musicList.add("http://sc1.111ttt.cn:8282/2017/1/11m/11/304112004168.m4a?tflag=1516348800&pin=1d3e05b2704f5dca0b6ff2e5d040bf2c#.mp3");
            musicList.add("http://sc1.111ttt.cn:8282/2017/1/11m/11/304112002593.m4a?tflag=1516348800&pin=1d3e05b2704f5dca0b6ff2e5d040bf2c#.mp3");
            musicList.add("http://sc1.111ttt.cn:8282/2017/1/11m/11/304112002493.m4a?tflag=1516348800&pin=1d3e05b2704f5dca0b6ff2e5d040bf2c#.mp3");
            musicList.add("http://mp4.111ttt.cn/2017/xc/08/ANB.mp4?#.mp3");
            musicList.add("http://sc1.111ttt.cn:8282/2017/1/11m/11/304112003137.m4a?tflag=1516348800&pin=1d3e05b2704f5dca0b6ff2e5d040bf2c#.mp3");
            musicList.add("http://sc1.111ttt.cn:8282/2017/1/11m/11/304112004168.m4a?tflag=1516348800&pin=1d3e05b2704f5dca0b6ff2e5d040bf2c#.mp3");
            musicList.add("http://sc1.111ttt.cn:8282/2017/1/11m/11/304112002593.m4a?tflag=1516348800&pin=1d3e05b2704f5dca0b6ff2e5d040bf2c#.mp3");
            musicList.add("http://sc1.111ttt.cn:8282/2017/1/11m/11/304112002493.m4a?tflag=1516348800&pin=1d3e05b2704f5dca0b6ff2e5d040bf2c#.mp3");
            musicList.add("http://mp4.111ttt.cn/2017/xc/08/ANB.mp4?#.mp3");
            musicList.add("http://sc1.111ttt.cn:8282/2017/1/11m/11/304112003137.m4a?tflag=1516348800&pin=1d3e05b2704f5dca0b6ff2e5d040bf2c#.mp3");
            musicList.add("http://sc1.111ttt.cn:8282/2017/1/11m/11/304112004168.m4a?tflag=1516348800&pin=1d3e05b2704f5dca0b6ff2e5d040bf2c#.mp3");
            musicList.add("http://sc1.111ttt.cn:8282/2017/1/11m/11/304112002593.m4a?tflag=1516348800&pin=1d3e05b2704f5dca0b6ff2e5d040bf2c#.mp3");
            musicList.add("http://sc1.111ttt.cn:8282/2017/1/11m/11/304112002493.m4a?tflag=1516348800&pin=1d3e05b2704f5dca0b6ff2e5d040bf2c#.mp3");
            musicList.add("http://mp4.111ttt.cn/2017/xc/08/ANB.mp4?#.mp3");
            musicList.add("http://sc1.111ttt.cn:8282/2017/1/11m/11/304112003137.m4a?tflag=1516348800&pin=1d3e05b2704f5dca0b6ff2e5d040bf2c#.mp3");
            musicList.add("http://sc1.111ttt.cn:8282/2017/1/11m/11/304112004168.m4a?tflag=1516348800&pin=1d3e05b2704f5dca0b6ff2e5d040bf2c#.mp3");
            musicList.add("http://sc1.111ttt.cn:8282/2017/1/11m/11/304112002593.m4a?tflag=1516348800&pin=1d3e05b2704f5dca0b6ff2e5d040bf2c#.mp3");
            musicList.add("http://sc1.111ttt.cn:8282/2017/1/11m/11/304112002493.m4a?tflag=1516348800&pin=1d3e05b2704f5dca0b6ff2e5d040bf2c#.mp3");

        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new PlayViewHolder(LayoutInflater.from(mActivity).inflate(R.layout.item_audio, null, false));
        }

        @Override
        public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {

            if (holder instanceof PlayViewHolder) {
                final String url = musicList.get(position);

                ((PlayViewHolder) holder).btnOpen.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//先停止上一个打开的
                        Player oldPlayer = playerHashMap.get(lastPosition);
                        if (oldPlayer != null && lastPosition != position) {
                            //暂停（防止，player准备没有完成的情况。）
                            oldPlayer.pauseOrStop(lastBtn);
                            playerHashMap.remove(lastPosition);
                            LogUtil.myD("停止其他lastPosition:" + lastPosition + ",position:" + position);
                        } else {
                            LogUtil.myD("无效lastPosition:" + lastPosition + ",position:" + position);
                        }

//新创建一个播放器
                        Player newPlayer;
                        if (playerHashMap.get(position) == null) {
                            newPlayer = new Player();
                            playerHashMap.put(position, newPlayer);
                        } else {
                            newPlayer = playerHashMap.get(position);
                        }

                        newPlayer.playUrl2(((PlayViewHolder) holder).btnOpen, url);
                        lastBtn = ((PlayViewHolder) holder).btnOpen;
                        lastPosition = position;


                    }
                });
            }
        }

        @Override
        public int getItemCount() {
            return musicList.size();
        }


        public void memory() {
            if (playerHashMap != null) {
                Iterator iter = playerHashMap.entrySet().iterator();
                while (iter.hasNext()) {
                    Map.Entry entry = (Map.Entry) iter.next();
//                    Object key = entry.getKey();
                    Player val = (Player) entry.getValue();
                    val.stop();

                }
            }
        }

        class PlayViewHolder extends RecyclerView.ViewHolder {
            @BindView(R.id.btnOpen)
            Button btnOpen;

            PlayViewHolder(View view) {
                super(view);
                ButterKnife.bind(this, view);
            }
        }
    }
}