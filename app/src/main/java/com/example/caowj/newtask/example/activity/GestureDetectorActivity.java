package com.example.caowj.newtask.example.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.caowj.newtask.R;
import com.example.caowj.newtask.base.BaseActivity;

/**
 * 手势监听GestureDetector<p/>
 * <p>
 * 1.OnGestureListener<br/>
 * 2.OnDoubleTapListener<br/>
 * 3.SimpleOnGestureListener<br/>
 * <p>
 * http://blog.csdn.net/harvic880925/article/details/39520901<br/>
 */
public class GestureDetectorActivity extends BaseActivity {

    private TextView mTextView;
    private GestureDetector mGestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        mGestureDetector = new GestureDetector(this, new MySimpleOnGestureListener());
        mGestureDetector = new GestureDetector(this, new GestureDetectorListener());

        //设置双击监听
        mGestureDetector.setOnDoubleTapListener(new DoubleTapListener());

        mTextView = (TextView) findViewById(R.id.btn_textgesture);
        mTextView.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.i(getClass().getName(), "onTouch-----" + getActionName(event.getAction()));

                // 一定要返回true，不然获取不到完整的事件
                return mGestureDetector.onTouchEvent(event);
            }
        });
    }

    private String getActionName(int action) {
        String name = "";
        switch (action) {
            case MotionEvent.ACTION_DOWN: {
                name = "ACTION_DOWN";
                break;
            }
            case MotionEvent.ACTION_MOVE: {
                name = "ACTION_MOVE";
                break;
            }
            case MotionEvent.ACTION_UP: {
                name = "ACTION_UP";
                break;
            }
            default:
                break;
        }
        return name;
    }

    /**
     * 使用内部类：SimpleOnGestureListener
     */
    class MySimpleOnGestureListener extends GestureDetector.SimpleOnGestureListener {
        /*****OnGestureListener的函数*****/
        public boolean onDown(MotionEvent e) {
            Log.i("MyGesture", "onDown");
            Toast.makeText(mActivity, "onDown", Toast.LENGTH_SHORT)
                    .show();
            return false;
        }

        public void onShowPress(MotionEvent e) {
            Log.i("MyGesture", "onShowPress");
            Toast.makeText(mActivity, "onShowPress", Toast.LENGTH_SHORT)
                    .show();
        }

        public boolean onSingleTapUp(MotionEvent e) {
            Log.i("MyGesture", "onSingleTapUp");
            Toast.makeText(mActivity, "onSingleTapUp",
                    Toast.LENGTH_SHORT).show();
            return true;
        }

        public boolean onScroll(MotionEvent e1, MotionEvent e2,
                                float distanceX, float distanceY) {
            Log.i("MyGesture", "onScroll:" + (e2.getX() - e1.getX()) + "   "
                    + distanceX);
            Toast.makeText(mActivity, "onScroll", Toast.LENGTH_LONG)
                    .show();

            return true;
        }

        public void onLongPress(MotionEvent e) {
            Log.i("MyGesture", "onLongPress");
            Toast.makeText(mActivity, "onLongPress", Toast.LENGTH_LONG)
                    .show();
        }

        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                               float velocityY) {
            Log.i("MyGesture", "onFling");
            Toast.makeText(mActivity, "onFling", Toast.LENGTH_LONG)
                    .show();
            return true;
        }

        /*****OnDoubleTapListener的函数*****/
        public boolean onSingleTapConfirmed(MotionEvent e) {
            Log.i("MyGesture", "onSingleTapConfirmed");
            Toast.makeText(mActivity, "onSingleTapConfirmed",
                    Toast.LENGTH_LONG).show();
            return true;
        }

        public boolean onDoubleTap(MotionEvent e) {
            Log.i("MyGesture", "onDoubleTap");
            Toast.makeText(mActivity, "onDoubleTap", Toast.LENGTH_LONG)
                    .show();
            return true;
        }

        public boolean onDoubleTapEvent(MotionEvent e) {
            Log.i("MyGesture", "onDoubleTapEvent");
            Toast.makeText(mActivity, "onDoubleTapEvent",
                    Toast.LENGTH_LONG).show();
            return true;
        }
    }

    /**
     * 实现监听器：OnGestureListener
     */
    private class GestureDetectorListener implements GestureDetector.OnGestureListener {

        final int FLING_MIN_DISTANCE = 100, FLING_MIN_VELOCITY = 200;

        // 用户轻触触摸屏，由1个MotionEvent ACTION_DOWN触发     
        public boolean onDown(MotionEvent e) {
            Log.i("MyGesture", "onDown");
            Toast.makeText(mActivity, "onDown", Toast.LENGTH_SHORT).show();
            return false;
        }

        /*   
         * 用户轻触触摸屏，尚未松开或拖动，由一个1个MotionEvent ACTION_DOWN触发   
         * 注意和onDown()的区别，强调的是没有松开或者拖动的状态   
         *  
         * 而onDown也是由一个MotionEventACTION_DOWN触发的，但是他没有任何限制， 
         * 也就是说当用户点击的时候，首先MotionEventACTION_DOWN，onDown就会执行， 
         * 如果在按下的瞬间没有松开或者是拖动的时候onShowPress就会执行，如果是按下的时间超过瞬间 
         * （这块我也不太清楚瞬间的时间差是多少，一般情况下都会执行onShowPress），拖动了，就不执行onShowPress。 
         */
        public void onShowPress(MotionEvent e) {
            Log.i("MyGesture", "onShowPress");
            Toast.makeText(mActivity, "onShowPress", Toast.LENGTH_SHORT).show();
        }

        // 用户（轻触触摸屏后）松开，由一个1个MotionEvent ACTION_UP触发     
        ///轻击一下屏幕，立刻抬起来，才会有这个触发  
        //从名子也可以看出,一次单独的轻击抬起操作,当然,如果除了Down以外还有其它操作,那就不再算是Single操作了,所以这个事件 就不再响应  
        public boolean onSingleTapUp(MotionEvent e) {
            Log.i("MyGesture", "onSingleTapUp");
            Toast.makeText(mActivity, "onSingleTapUp", Toast.LENGTH_SHORT).show();
            return true;
        }

        // 用户按下触摸屏，并拖动，由1个MotionEvent ACTION_DOWN, 多个ACTION_MOVE触发     
        public boolean onScroll(MotionEvent e1, MotionEvent e2,
                                float distanceX, float distanceY) {
            Log.i("MyGesture22", "onScroll:" + (e2.getX() - e1.getX()) + "   " + distanceX);
            Toast.makeText(mActivity, "onScroll", Toast.LENGTH_LONG).show();

            return true;
        }

        // 用户长按触摸屏，由多个MotionEvent ACTION_DOWN触发     
        public void onLongPress(MotionEvent e) {
            Log.i("MyGesture", "onLongPress");
            Toast.makeText(mActivity, "onLongPress", Toast.LENGTH_LONG).show();
        }


        // 触发条件 ：
        // X轴的坐标位移大于FLING_MIN_DISTANCE，且移动速度大于FLING_MIN_VELOCITY个像素/秒

        // 参数解释：
        // e1：第1个ACTION_DOWN MotionEvent
        // e2：最后一个ACTION_MOVE MotionEvent
        // velocityX：X轴上的移动速度，像素/秒
        // velocityY：Y轴上的移动速度，像素/秒
        // 用户按下触摸屏、快速移动后松开，由1个MotionEvent ACTION_DOWN, 多个ACTION_MOVE, 1个ACTION_UP触发     
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                               float velocityY) {
//            Log.i("MyGesture", "onFling");
//            Toast.makeText(mActivity, "onFling", Toast.LENGTH_LONG).show();
            if (e1.getX() - e2.getX() > FLING_MIN_DISTANCE
                    && Math.abs(velocityX) > FLING_MIN_VELOCITY) {
                // Fling left
                Log.i("MyGesture", "Fling left");
                Toast.makeText(mActivity, "Fling Left", Toast.LENGTH_SHORT).show();
            } else if (e2.getX() - e1.getX() > FLING_MIN_DISTANCE
                    && Math.abs(velocityX) > FLING_MIN_VELOCITY) {
                // Fling right
                Log.i("MyGesture", "Fling right");
                Toast.makeText(mActivity, "Fling Right", Toast.LENGTH_SHORT).show();
            }
            return true;
        }
    }


    /**
     * 实现双击监听：OnDoubleTapListener
     */
    private class DoubleTapListener implements GestureDetector.OnDoubleTapListener {

        public boolean onSingleTapConfirmed(MotionEvent e) {
            Log.i("MyGesture", "onSingleTapConfirmed");
            Toast.makeText(mActivity, "onSingleTapConfirmed", Toast.LENGTH_LONG).show();
            return true;
        }

        public boolean onDoubleTap(MotionEvent e) {
            Log.i("MyGesture", "onDoubleTap");
            Toast.makeText(mActivity, "onDoubleTap", Toast.LENGTH_LONG).show();
            return true;
        }

        public boolean onDoubleTapEvent(MotionEvent e) {
            Log.i("MyGesture", "onDoubleTapEvent");
            Toast.makeText(mActivity, "onDoubleTapEvent", Toast.LENGTH_LONG).show();
            return true;
        }
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_gesture_detector;
    }

    @Override
    protected void memoryRecovery() {

    }
}
