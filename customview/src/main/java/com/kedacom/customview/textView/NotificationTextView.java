package com.kedacom.customview.textView;/**
 * @author Dick.Pan
 * @date 2017/10/9
 */

import android.content.Context;
import android.graphics.Camera;
import android.graphics.Matrix;
import android.os.Handler;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.kedacom.customview.bean.ScrollNotification;

import java.util.List;


/**
 * @author Dick.Pan
 * @Date 2017/10/9$
 * 通知滚动控件
 */
public class NotificationTextView extends TextSwitcher implements ViewSwitcher.ViewFactory, View.OnClickListener {

    private float mHeight = 13;//字体大小像素单位px
    private Context mContext;
    //mInUp,mOutUp分别构成向上翻页的进出动画
    private Animation mInUp;
    private Animation mOutUp;

    //mInDown,mOutDown分别构成向下翻页的进出动画
    private Animation mInDown;
    private Animation mOutDown;

    private int index;//当前通知集合数据下标位置

    private int currentNotificationID;//当前通知ID

    private int position;//实际点击位置

    private boolean isScrollingUp;//是向上滚动

    private List<ScrollNotification> scrollDataList;//滚动数据集合

    private int size;//通知集合长度，决定是否调用滚动动画

    private Handler handler;

    private OnItemClickListener onItemClickListener;//跳转到通知详情监听
    private Runnable runnable = new Runnable() {

        @Override
        public void run() {
            try {
                //获取滚动通知信息
                ScrollNotification scrollNotification = scrollDataList.get(index);
                //设置滚动通知信息
                setText(scrollNotification.getTitle());
                //当前下标位置
                position = index;
                //当前通知ID
                currentNotificationID = scrollNotification.getId();
                // handler自带方法实现定时器
                if (size > 1) {
                    if (isScrollingUp) {
                        next();
                    } else {
                        previous();
                    }

                    index++;
                    if (index >= size || index < 0) {
                        index = 0;
                    }
                    handler.postDelayed(this, 3000);
                }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    };

    public NotificationTextView(Context context) {
        this(context, null);
    }

    public NotificationTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        handler = new Handler();
        init();
    }

    /**
     * 初始化
     */
    private void init() {
        // TODO Auto-generated method stub
        setFactory(this);
        mInUp = createAnim(0, 0, true, true);
        mOutUp = createAnim(0, 0, false, true);
        mInDown = createAnim(90, 0, true, false);
        mOutDown = createAnim(0, -90, false, false);
        setInAnimation(mInUp);
        setOutAnimation(mOutUp);
    }

    /**
     * 创建动画属性值
     *
     * @param start
     * @param end
     * @param turnIn
     * @param turnUp
     * @return
     */
    private Animation createAnim(float start, float end, boolean turnIn, boolean turnUp) {
        final Animation rotation = new Rotate3dAnimation(start, end, turnIn, turnUp);
        rotation.setDuration(1000);
        rotation.setFillAfter(false);
        rotation.setInterpolator(new AccelerateInterpolator());
        return rotation;
    }

    //这里返回的TextView，就是我们看到的View
    @Override
    public View makeView() {
        TextView t = new TextView(mContext);
        t.setPadding(0, 10, 0, 10);
        t.setGravity(Gravity.CENTER_VERTICAL);
        t.setTextSize(mHeight);
        t.setMaxLines(1);
        t.setEllipsize(TextUtils.TruncateAt.END);
        return t;
    }

    @Override
    public void onClick(View v) {
        if (onItemClickListener != null) {
            onItemClickListener.onItemClick(position, currentNotificationID);
        }
    }

    /**
     * 1.设置滚动数据集合
     *
     * @param isScrollingUp  是否向上滚动
     * @param scrollDataList 滚动数据集合
     */
    public void setScrollData(boolean isScrollingUp, List<ScrollNotification> scrollDataList) {
        this.isScrollingUp = isScrollingUp;
        this.scrollDataList = scrollDataList;
        //当刷新控件调用时需要清空handler所有异步操作和消息
        handler.removeCallbacksAndMessages(null);
        size = scrollDataList.size();
        if (size > 0) {
            handler.postDelayed(runnable, 0);
        }
    }

    /**
     * 2.设置当前滚动监听
     *
     * @param onItemClickListener
     */
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.setOnClickListener(this);
        this.onItemClickListener = onItemClickListener;
    }

    /**
     * 内存回收
     */
    public void onDestroy() {
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
            handler = null;
        }

        if (runnable != null) {
            runnable = null;
        }
        if (scrollDataList != null) {
            scrollDataList.clear();
            scrollDataList = null;
        }
        if (mContext != null) {
            mContext = null;
        }
    }

    //定义动作，向下滚动翻页
    public void previous() {
        if (getInAnimation() != mInDown) {
            setInAnimation(mInDown);
        }
        if (getOutAnimation() != mOutDown) {
            setOutAnimation(mOutDown);
        }
    }

    //定义动作，向上滚动翻页
    public void next() {
        if (getOutAnimation() != mOutUp) {
            setOutAnimation(mOutUp);
        }
        if (getInAnimation() != mInUp) {
            setInAnimation(mInUp);
        }
    }

    /**
     * 监听跳转详情
     */
    public interface OnItemClickListener {
        //当前显示位置，当前通知ID
        void onItemClick(int position, int notificationID);
    }

    private class Rotate3dAnimation extends Animation {
        private final float mFromDegrees;
        private final float mToDegrees;
        private final boolean mTurnIn;
        private final boolean mTurnUp;
        private float mCenterX;
        private float mCenterY;
        private Camera mCamera;

        public Rotate3dAnimation(float fromDegrees, float toDegrees, boolean turnIn, boolean turnUp) {
            mFromDegrees = fromDegrees;
            mToDegrees = toDegrees;
            mTurnIn = turnIn;
            mTurnUp = turnUp;
        }

        @Override
        public void initialize(int width, int height, int parentWidth, int parentHeight) {
            super.initialize(width, height, parentWidth, parentHeight);
            mCamera = new Camera();
            mCenterY = getHeight();
            mCenterX = getWidth() / 2;
        }

        @Override
        protected void applyTransformation(float interpolatedTime, Transformation t) {
            final float fromDegrees = mFromDegrees;
            float degrees = fromDegrees + ((mToDegrees - fromDegrees) * interpolatedTime);

            final float centerX = mCenterX;
            final float centerY = mCenterY;
            final Camera camera = mCamera;
            final int derection = mTurnUp ? 1 : -1;

            final Matrix matrix = t.getMatrix();

            camera.save();
            if (mTurnIn) {
                camera.translate(0.0f, derection * mCenterY * (interpolatedTime - 1.0f), 0.0f);
            } else {
                camera.translate(0.0f, derection * mCenterY * (interpolatedTime), 0.0f);
            }
            camera.rotateX(degrees);
            camera.getMatrix(matrix);
            camera.restore();

            matrix.preTranslate(-centerX, -centerY);
            matrix.postTranslate(centerX, centerY);
        }
    }
}
