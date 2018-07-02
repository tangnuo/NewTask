package com.example.caowj.newtask.example.activity;

import android.graphics.SurfaceTexture;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Surface;
import android.view.TextureView;

import com.example.caowj.newtask.R;
import com.kedacom.base.mvc.BaseButterKnifeActivity;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * TextureView使用<br/>
 * <p>
 * http://blog.csdn.net/HardWorkingAnt/article/details/72784044
 */
public class TestTextureViewActivity extends BaseButterKnifeActivity implements TextureView.SurfaceTextureListener, MediaPlayer.OnPreparedListener {

    /**
     * 本地视频的路径
     */
//    public String videoPath = Environment.getExternalStorageDirectory().getPath() + "/ht.mp4";
    public String videoPath = "http://jzvd.nathen.cn/c494b340ff704015bb6682ffde3cd302/64929c369124497593205a4190d7d128-5287d2089db37e62345123a1be272f8b.mp4";
    @BindView(R.id.textureView1)
    TextureView myTexture;
    private MediaPlayer mediaPlayer;
    private SurfaceTexture mTexture;
    private Surface surface;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myTexture.setSurfaceTextureListener(this);
        //旋转，修改透明度
        myTexture.setRotation(45.0f);
        myTexture.setAlpha(0.5f);
        ButterKnife.bind(this);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_test_texture_view;
    }

    @Override
    protected void memoryRecovery() {

    }

    @Override
    public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
        playVideo();
    }

    /**
     * 播放视频的入口，当SurfaceTexure可得到时被调用
     */
    private void playVideo() {
        if (mediaPlayer == null) {
            mTexture = myTexture.getSurfaceTexture();
            surface = new Surface(mTexture);
            initMediaPlayer();
        }
    }

    private void initMediaPlayer() {
        this.mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource(videoPath);
            mediaPlayer.setSurface(surface);
            mediaPlayer.prepareAsync();
            mediaPlayer.setOnPreparedListener(this);
            mediaPlayer.setLooping(true);
        } catch (IllegalArgumentException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (SecurityException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (IllegalStateException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }


    @Override
    public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {

    }

    @Override
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
        return false;
    }

    @Override
    public void onSurfaceTextureUpdated(SurfaceTexture surface) {

    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        try {
            if (mp != null) {
                mp.start(); //视频开始播放
            }
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (myTexture.isAvailable()) {
            playVideo();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mTexture != null) {
            mTexture.release();  //停止视频的绘制线程
        }
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}
