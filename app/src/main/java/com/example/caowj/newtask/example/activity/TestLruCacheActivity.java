package com.example.caowj.newtask.example.activity;

import android.os.Bundle;
import android.widget.GridView;

import com.example.caowj.newtask.R;
import com.example.caowj.newtask.base.BaseActivity;
import com.example.caowj.newtask.example.adapter.PhotoWallAdapter;
import com.example.caowj.newtask.example.data.Images;

import butterknife.BindView;

/**
 * LruCache缓存图片
 * http://blog.csdn.net/guolin_blog/article/details/9526203
 * <p>
 * <p>
 * 缺点：这个程序中没有使用本地缓存
 * </p>
 */
public class TestLruCacheActivity extends BaseActivity {

    @BindView(R.id.photo_wall)
    GridView photoWall;

    /**
     * GridView的适配器
     */
    private PhotoWallAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new PhotoWallAdapter(this, 0, Images.imageThumbUrls, photoWall);
        photoWall.setAdapter(adapter);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_test_lru_cache;
    }

    @Override
    protected void memoryRecovery() {
        // 退出程序时结束所有的下载任务
        adapter.cancelAllTasks();
    }
}
