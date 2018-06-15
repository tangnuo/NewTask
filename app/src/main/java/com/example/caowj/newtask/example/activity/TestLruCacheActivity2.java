package com.example.caowj.newtask.example.activity;

import android.os.Bundle;
import android.view.ViewTreeObserver;
import android.widget.GridView;

import com.example.caowj.newtask.R;
import com.example.caowj.newtask.example.adapter.PhotoWallAdapter2;
import com.kedacom.base.mvc.BaseButterKnifeActivity;
import com.kedacom.utils.DataList.DataList;

/**
 * <p>
 * 结合LruCache和DiskLruCache实现突破加载。
 * </p>
 * http://blog.csdn.net/guolin_blog/article/details/34093441
 */
public class TestLruCacheActivity2 extends BaseButterKnifeActivity {

    /**
     * 用于展示照片墙的GridView
     */
    private GridView mPhotoWall;

    /**
     * GridView的适配器
     */
    private PhotoWallAdapter2 mAdapter;

    private int mImageThumbSize;
    private int mImageThumbSpacing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        mImageThumbSize = getResources().getDimensionPixelSize(
//                R.dimen.image_thumbnail_size);
//        mImageThumbSpacing = getResources().getDimensionPixelSize(
//                R.dimen.image_thumbnail_spacing);

        mImageThumbSize = 100;
        mImageThumbSpacing = 2;
        mPhotoWall = (GridView) findViewById(R.id.photo_wall);
        mAdapter = new PhotoWallAdapter2(mActivity, 0, DataList.getImageArray(),
                mPhotoWall);
        mPhotoWall.setAdapter(mAdapter);
        mPhotoWall.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {

                    @Override
                    public void onGlobalLayout() {
                        final int numColumns = (int) Math.floor(mPhotoWall
                                .getWidth()
                                / (mImageThumbSize + mImageThumbSpacing));
                        if (numColumns > 0) {
                            int columnWidth = (mPhotoWall.getWidth() / numColumns)
                                    - mImageThumbSpacing;
                            mAdapter.setItemHeight(columnWidth);
                            mPhotoWall.getViewTreeObserver()
                                    .removeGlobalOnLayoutListener(this);
                        }
                    }
                });
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_test_lru_cache;
    }

    @Override
    protected void memoryRecovery() {

    }

    @Override
    protected void onPause() {
        super.onPause();
        mAdapter.fluchCache();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 退出程序时结束所有的下载任务
        mAdapter.cancelAllTasks();
    }

}
