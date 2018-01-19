package com.example.caowj.newtask.other;

/**
 * package: com.example.caowj.newtask.other
 * author: Administrator
 * date: 2018/1/19 16:06
 */

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnBufferingUpdateListener;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Button;
import android.widget.SeekBar;

import com.example.caowj.newtask.utils.LogUtil;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class Player implements OnBufferingUpdateListener,
        OnCompletionListener, MediaPlayer.OnPreparedListener {
    public MediaPlayer mediaPlayer;
    private SeekBar skbProgress;
    private boolean isPause = false;//是否暂停
    private Timer mTimer = new Timer();

    public Player(SeekBar skbProgress) {
        this.skbProgress = skbProgress;

        try {
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.setOnBufferingUpdateListener(this);
            mediaPlayer.setOnPreparedListener(this);
        } catch (Exception e) {
            Log.e("mediaPlayer", "error", e);
        }

        mTimer.schedule(mTimerTask, 0, 500);
    }

    /*******************************************************
     * 通过定时器和Handler来更新进度条
     ******************************************************/
    TimerTask mTimerTask = new TimerTask() {
        @Override
        public void run() {
            if (mediaPlayer == null)
                return;
            if (mediaPlayer.isPlaying() && skbProgress.isPressed() == false) {
                handleProgress.sendEmptyMessage(0);
            }
        }
    };

    Handler handleProgress = new Handler() {
        public void handleMessage(Message msg) {

            int position = mediaPlayer.getCurrentPosition();
            int duration = mediaPlayer.getDuration();

            if (duration > 0) {
                long pos = skbProgress.getMax() * position / duration;
                skbProgress.setProgress((int) pos);
            }

            LogUtil.myD("position:" + position + ",,duration:" + duration);
//            if (position)
        }

        ;
    };
    //*****************************************************


    public void playUrl(String videoUrl) {
        try {
            mediaPlayer.reset();
            mediaPlayer.setDataSource(videoUrl);
            mediaPlayer.prepare();//prepare之后自动播放
            //mediaPlayer.start();
        } catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalStateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    /**
     * 暂停、继续
     */
    public void pause() {
        if (mediaPlayer.isPlaying() && !isPause) {
            mediaPlayer.pause();
            isPause = true;
        } else {
            mediaPlayer.start();
            isPause = false;
        }
    }

    /**
     * 开始、暂停、继续
     */
    public void playUrl2(Button button, String videoUrl) {
        if (mediaPlayer.getCurrentPosition() > 0) {
            if (mediaPlayer.isPlaying() && !isPause) {
                mediaPlayer.pause();
                isPause = true;
                button.setText("继续");
            } else {
                mediaPlayer.start();
                isPause = false;
                button.setText("暂停2");
            }
        } else {
            playUrl(videoUrl);
            button.setText("暂停");
        }

    }

    public void stop() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    @Override
    /**
     * 通过onPrepared播放
     */
    public void onPrepared(MediaPlayer arg0) {
        arg0.start();
        if (isPause) {
            isPause = false;
        }
        Log.e("mediaPlayer", "onPrepared");
        LogUtil.myD("onPrepared...");
    }

    @Override
    public void onCompletion(MediaPlayer arg0) {
        Log.e("mediaPlayer", "onCompletion");
        LogUtil.myD("onCompletion（播放结束）...");

    }

    @Override
    public void onBufferingUpdate(MediaPlayer arg0, int bufferingProgress) {
        skbProgress.setSecondaryProgress(bufferingProgress);
        int currentProgress = skbProgress.getMax() * mediaPlayer.getCurrentPosition() / mediaPlayer.getDuration();
        Log.e(currentProgress + "% play", bufferingProgress + "% buffer");
    }

}