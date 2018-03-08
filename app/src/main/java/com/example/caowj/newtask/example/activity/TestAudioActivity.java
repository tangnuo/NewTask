package com.example.caowj.newtask.example.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

import com.example.caowj.newtask.R;
import com.example.caowj.newtask.base.BaseActivity;
import com.example.caowj.newtask.other.Player;

/**
 * 音频播放一
 */
public class TestAudioActivity extends BaseActivity {

    private Button btnPause, btnPlayUrl, btnStop, btnOpen;
    private SeekBar skbProgress;
    private Player player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTitle("在线音乐播放---hellogv编写");

        btnPlayUrl = (Button) this.findViewById(R.id.btnPlayUrl);
        btnPlayUrl.setOnClickListener(new ClickEvent());

        btnPause = (Button) this.findViewById(R.id.btnPause);
        btnPause.setOnClickListener(new ClickEvent());

        btnStop = (Button) this.findViewById(R.id.btnStop);
        btnStop.setOnClickListener(new ClickEvent());

        btnOpen = (Button) findViewById(R.id.btnOpen);
        btnOpen.setOnClickListener(new ClickEvent());

        skbProgress = (SeekBar) this.findViewById(R.id.skbProgress);
        skbProgress.setOnSeekBarChangeListener(new SeekBarChangeEvent());
        player = new Player(skbProgress);
    }

    class ClickEvent implements View.OnClickListener {

        @Override
        public void onClick(View arg0) {
            if (arg0 == btnPause) {
                player.pauseOrStart();
            } else if (arg0 == btnPlayUrl) {
                //在百度MP3里随便搜索到的,大家可以试试别的链接
                String url = "http://sc1.111ttt.cn:8282/2017/1/11m/11/304112003137.m4a?tflag=1516348800&pin=1d3e05b2704f5dca0b6ff2e5d040bf2c#.mp3";
                player.playUrl(url);
            } else if (arg0 == btnStop) {
                player.stop();
            } else if (arg0 == btnOpen) {
                String url = "http://sc1.111ttt.cn:8282/2017/1/11m/11/304112002593.m4a?tflag=1516348800&pin=1d3e05b2704f5dca0b6ff2e5d040bf2c#.mp3";
                player.playUrl2(btnOpen, url);
            }
        }
    }

    class SeekBarChangeEvent implements SeekBar.OnSeekBarChangeListener {
        int progress;

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress,
                                      boolean fromUser) {
            // 原本是(progress/seekBar.getMax())*player.mediaPlayer.getDuration()
            this.progress = progress * player.mediaPlayer.getDuration()
                    / seekBar.getMax();
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            // seekTo()的参数是相对与影片时间的数字，而不是与seekBar.getMax()相对的数字
            player.mediaPlayer.seekTo(progress);
        }
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_test_audio;
    }

    @Override
    protected void memoryRecovery() {

    }
}
