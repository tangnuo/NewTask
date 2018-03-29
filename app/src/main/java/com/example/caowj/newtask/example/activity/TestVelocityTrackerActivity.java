package com.example.caowj.newtask.example.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.ViewConfiguration;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;

/**
 * 滑动速度跟踪类VelocityTracker介绍
 * Velocity：速度
 * Tracker：追踪者，纤夫
 * <br/>http://www.jcodecraeer.com/a/anzhuokaifa/androidkaifa/2012/1117/574.html
 */
public class TestVelocityTrackerActivity extends AppCompatActivity {

    private TextView mTextView;
    /**
     * 滑动速度跟踪类
     */
    private VelocityTracker mVelocityTracker;

    /**
     * 最大速度
     */
    private int mMaxVelocity;
    /**
     * 第一个触点的id
     */
    private int mPointerId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_test_velocity_tracker);


        if (mVelocityTracker == null) {
            mVelocityTracker = VelocityTracker.obtain();//获得VelocityTracker类实例
        }
//        mMaxVelocity = ViewConfiguration.get(this).getMaximumFlingVelocity();
        //以像素/秒为单位测量的启动投掷的最大速度。
        mMaxVelocity = ViewConfiguration.get(this).getScaledMaximumFlingVelocity();

        mTextView = new TextView(this);
        mTextView.setLines(4);
        mTextView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        mTextView.setTextColor(Color.RED);
        setContentView(mTextView);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        final int action = event.getAction();

        if (mVelocityTracker == null) {
            mVelocityTracker = VelocityTracker.obtain();//获得VelocityTracker类实例
        }
        mVelocityTracker.addMovement(event);

        final VelocityTracker verTracker = mVelocityTracker;
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                //求第一个触点的id， 此时可能有多个触点，但至少一个
                mPointerId = event.getPointerId(0);
                break;

            case MotionEvent.ACTION_MOVE:
                //求伪瞬时速度
                verTracker.computeCurrentVelocity(1000, mMaxVelocity);
                final float velocityX = verTracker.getXVelocity(mPointerId);
                final float velocityY = verTracker.getYVelocity(mPointerId);
                recodeInfo(velocityX, velocityY);
                break;

            case MotionEvent.ACTION_UP:
                releaseVelocityTracker();
                break;

            case MotionEvent.ACTION_CANCEL:
                releaseVelocityTracker();
                break;

            default:
                break;
        }
        return super.onTouchEvent(event);
    }

    //释放VelocityTracker

    private void releaseVelocityTracker() {
        if (null != mVelocityTracker) {
            mVelocityTracker.clear();
            mVelocityTracker.recycle();
            mVelocityTracker = null;
        }
    }

    private static final String sFormatStr = "以触摸点为起始点，向上或向左都是负值；反之。\n velocityX=%f\n velocityY=%f";

    /**
     * 记录当前速度
     *
     * @param velocityX x轴速度
     * @param velocityY y轴速度
     */
    private void recodeInfo(final float velocityX, final float velocityY) {
        final String info = String.format(sFormatStr, velocityX, velocityY);
        mTextView.setText(info);
    }
}
