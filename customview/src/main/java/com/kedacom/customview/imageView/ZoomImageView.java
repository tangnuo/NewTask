package com.kedacom.customview.imageView;

import android.content.Context;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewTreeObserver;
import android.widget.ImageView;

/**
 * 可以缩放的图片空间<p/>
 * <p>
 * 参考视频：http://www.imooc.com/video/6716<br/>
 * <p>
 * 图片加载完成之后需要回调监听（OnGlobalLayoutListener）,onGlobalLayout（）
 * <p>
 * 参考博客：
 * http://blog.csdn.net/lmj623565791/article/details/39474553<br/>
 * <p>
 * 需求：
 * 1、自由缩放，中心显示
 * 2、设置缩放中心
 * <p>
 * <p>
 * package: com.example.caowj.newtask.widget
 * author: Administrator
 * date: 2017/9/7 16:03
 */
public class ZoomImageView extends android.support.v7.widget.AppCompatImageView implements ViewTreeObserver.OnGlobalLayoutListener, ScaleGestureDetector.OnScaleGestureListener, View.OnTouchListener {
    private Boolean mOnce = false;
    private float initScale;//初始化的缩放量
    private float mMaxScale;//最大缩放量
    private float midScale;//中间缩放量
    private Matrix mScaleMatrix;
    private int lastTouchPointCount = 0;//上次屏幕触点数
    private float lastX;
    private float lastY;
    private int onTouchSlop;//开始移动的滑动距离
    private Boolean isCanDrag = false;//是否可以移动
    private Boolean isNeedCheckTopAndBottom;//是否需要考虑top和boottom出现白边
    private Boolean isNeedCheckLeftAndRight;//是否需要考虑left和right出现白边
    private GestureDetector gestureDetector;//手势监听


    private ScaleGestureDetector scaleGestureDetector;//缩放手势监听(放大或缩小)

    public ZoomImageView(Context context) {
        this(context, null);
    }

    public ZoomImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ZoomImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setScaleType(ImageView.ScaleType.MATRIX);//设置缩放类型
        mScaleMatrix = new Matrix();
        scaleGestureDetector = new ScaleGestureDetector(getContext(), this);
        setOnTouchListener(this);
        onTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();//获取系统的默认开始移动的滑动距离
        /**
         * 处理双击图片放大和缩小
         */
        gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onDoubleTap(MotionEvent e) {
                float scale = getScale();
                float eventX = e.getX();
                float eventY = getY();
                if (scale < midScale) {
                    postDelayed(new AutoScaleRunnable(eventX, eventY, mMaxScale), 16);

                } else {
                    postDelayed(new AutoScaleRunnable(eventX, eventY, initScale), 16);

                }
                return true;
            }
        });

    }

    /**
     * 用线程来实现图片缓慢变大和缩小
     */
    public class AutoScaleRunnable implements Runnable {
        private float clickX;
        private float clickY;
        private float targetScale;
        private float scaleTo;
        private float scaleBig = 1.07f;
        private float scaleSmall = 0.93f;

        /**
         * 传入目标缩放值，根据目标值与当前值，判断应该放大还是缩小
         *
         * @param targetScale
         */
        public AutoScaleRunnable(float clickX, float clickY, float targetScale) {
            this.clickX = clickX;
            this.clickY = clickY;
            this.targetScale = targetScale;
            if (getScale() < targetScale) {
                scaleTo = scaleBig;
            }
            if (getScale() > targetScale) {
                scaleTo = scaleSmall;
            }
        }

        @Override
        public void run() {
            mScaleMatrix.postScale(scaleTo, scaleTo, clickX, clickY);
            setImageMatrix(mScaleMatrix);
            checkBorderWhenScale();

            float currentScale = getScale();
            if ((currentScale < targetScale && scaleTo > 1.0f) || (currentScale > targetScale && scaleTo < 1.0f)) {
                postDelayed(this, 16);
            } else {
                mScaleMatrix.postScale(targetScale / currentScale, targetScale / currentScale, clickX, clickY);
                setImageMatrix(mScaleMatrix);
                checkBorderWhenScale();
            }
        }
    }

    /**
     * 注册 GlobalLayoutListener监听事件
     */
    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        getViewTreeObserver().addOnGlobalLayoutListener(this);
    }

    /**
     * 移除监听事件
     */
    @Override
    protected void onDetachedFromWindow() {

        super.onDetachedFromWindow();
        getViewTreeObserver().removeGlobalOnLayoutListener(this);
    }

    /**
     * 把图片定位到屏幕中央，并进行初始化缩放适应屏幕<br/>
     * <p>
     * 图片大小和控件大小存在四种可能性：
     * 1：图片宽大于，高小于
     * 2：图片宽小于，高大于
     * 3：图片整体小于
     * 4：图片整体大于
     */
    @Override
    public void onGlobalLayout() {
        if (!mOnce) {
            //得到控件的宽高
            int with = getWidth();
            int height = getHeight();

            //得到图片，以及图片的宽高
            Drawable drawable = getDrawable();
            if (drawable == null) return;
            int draWith = drawable.getIntrinsicWidth();
            int draHeight = drawable.getIntrinsicHeight();

            //使用1.0f，免去强转。
            /**
             * 如果图片的宽度大于控件的宽度，但是高度小于控件的高度；将其缩小。
             */
            if (draWith > with && draHeight < height) {
                initScale = with * 1.0f / draWith;
            }
            /**
             * 如果图片的宽度小于控件的宽度，但是高度大于控件的高度；将其缩小。
             */
            if ((draWith > with && draHeight > height) || (draWith < with && draHeight < height)) {
                initScale = Math.min(with * 1.0f / draWith, height * 1.0f / draHeight);
            }
            if (draWith < with && draHeight > height) {
//                initScale = height * 1.0f / draHeight;
                initScale = with * 1.0f / draHeight;
            }

            /**
             * 得到初始化时缩放比例
             */
            mMaxScale = 4f * initScale;
            midScale = 2f * initScale;

            //将图片移到控件中心
            float dx = with / 2.0f - draWith / 2.0f;
            float dy = height / 2.0f - draHeight / 2.0f;
            mScaleMatrix.postTranslate(dx, dy);
            mScaleMatrix.postScale(initScale, initScale, with / 2.0f, height / 2.0f);
            this.setImageMatrix(mScaleMatrix);
            mOnce = true;
        }
    }

    /**
     * 获得缩放图片中图片的缩放值
     *
     * @return
     */
    public float getScale() {
        float values[] = new float[9];
        mScaleMatrix.getValues(values);
        return values[Matrix.MSCALE_X];
    }

    /**
     * 获的缩放中图片的边界信息；
     *
     * @return
     */
    private RectF getMatrixRectF() {
        Drawable drawable = getDrawable();
        if (drawable == null)
            return null;
        int draWith = drawable.getIntrinsicWidth();
        int draHeight = drawable.getIntrinsicHeight();
        RectF rectF = new RectF();
        rectF.set(0, 0, draWith, draHeight);
        mScaleMatrix.mapRect(rectF);//why
        return rectF;
    }

    /**
     * 处理多触点缩放
     *
     * @param detector
     * @return
     */

    @Override
    public boolean onScale(ScaleGestureDetector detector) {
        float scale = getScale();

        //当前捕获的缩放值（大于1表示想放大，小于1表示想缩小）。
        float scaleFactor = detector.getScaleFactor();
        if (getDrawable() == null) return true;
        if ((scale < mMaxScale && scaleFactor > 1.0f) || (scale > initScale && scaleFactor < 1.0f)) {
            if (scale * scaleFactor > mMaxScale) {
                scaleFactor = mMaxScale / scale;
            }
            if (scale * scaleFactor < initScale) {
                scaleFactor = initScale / scale;
            }

//            单纯的设置缩放中心
            mScaleMatrix.postScale(scaleFactor, scaleFactor, detector.getFocusX(), detector.getFocusY());
            setImageMatrix(mScaleMatrix);
//            控制缩放时图片显示的范围
            checkBorderWhenScale();
        }


        return true;//why
    }

    /**
     * 缩放中边缘控制，防止边缘出现白边(边界不留白，位置居中)
     */
    private void checkBorderWhenScale() {
        RectF draRectF = getMatrixRectF();
        if (draRectF == null) return;
        int with = getWidth();
        int height = getHeight();
        float dx = 0;
        float dy = 0;
        // 如果宽或高大于屏幕，则控制范围
        if (draRectF.width() >= with) {
            if (draRectF.left > 0) {
                //左边有白边，需要向左移动
                dx = -draRectF.left;
            }
            if (draRectF.right < with) {
                //右边有白边，需要向右移动
                dx = with - draRectF.right;
            }
        }
        if (draRectF.height() >= height) {
            if (draRectF.top > 0) {
                dy = -draRectF.top;
            }
            if (draRectF.bottom < height) {
                dy = height - draRectF.bottom;
            }

        }

        // 如果宽或高小于屏幕，则让其居中
        if (draRectF.height() < height) {
            dy = (height / 2.0f - draRectF.bottom + draRectF.height() / 2.0f);
        }
        if (draRectF.width() < with) {
            dx = (with / 2.0f - draRectF.right + draRectF.width() / 2.0f);
        }
        mScaleMatrix.postTranslate(dx, dy);
        setImageMatrix(mScaleMatrix);
    }


    @Override
    public boolean onScaleBegin(ScaleGestureDetector detector) {
        return true;//why
    }

    @Override
    public void onScaleEnd(ScaleGestureDetector detector) {

    }

    /**
     * 处理touch事件<p/>
     * <p>
     * 首先我们拿到触摸点的数量，然后求出多个触摸点的平均值，设置给我们的mLastX , mLastY ，
     * 然后在移动的时候，得到dx ,dy 进行范围检查以后，调用mScaleMatrix.postTranslate进行设置偏移量，
     * 当然了，设置完成以后，还需要再次校验一下，不能把图片移动的与屏幕边界出现白边，校验完成后，调用setImageMatrix.
     *
     * @param v
     * @param event
     * @return
     */
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (gestureDetector.onTouchEvent(event))//传入touch事件给GestureDetector
            return true;
        scaleGestureDetector.onTouchEvent(event);//传入touch事件给scaleGestureDetector
        // 拿到触摸点的个数
        int touchPointCount = event.getPointerCount();
        float x = 0;
        float y = 0;
        for (int i = 0; i < touchPointCount; i++) {
            x += event.getX(i);
            y += event.getY(i);
        }
        // 得到多个触摸点的x与y均值
        float centerX = x / touchPointCount;
        float centerY = y / touchPointCount;

//        每当触摸点发生变化时，重置mLasX , mLastY
        if (lastTouchPointCount != touchPointCount) {
            isCanDrag = false;
            lastX = centerX;
            lastY = centerY;
        }
        lastTouchPointCount = touchPointCount;
//        RectF rectF = getMatrixRectF();
        switch (event.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                if (rectF != null && (rectF.height() > getHeight() || rectF.width() > getWidth())) {
//                    getParent().requestDisallowInterceptTouchEvent(true);//阻止父布局（viewpager）拦截事件
//                }
//                break;
            case MotionEvent.ACTION_MOVE:
//
//                if (rectF != null && (rectF.height() > getHeight() || rectF.width() > getWidth())) {
//                    getParent().requestDisallowInterceptTouchEvent(true);
//                }
                float dx = centerX - lastX;
                float dy = centerY - lastY;
                if (!isCanDrag) {
                    isCanDrag = isMoveAction(dx, dy);
                }
                if (isCanDrag) {
                    isNeedCheckLeftAndRight = true;
                    isNeedCheckTopAndBottom = true;
                    RectF drawRecF = getMatrixRectF();
                    if (drawRecF != null) {
                        // 如果宽度小于屏幕宽度，则禁止左右移动
                        if (drawRecF.width() < getWidth()) {
                            isNeedCheckLeftAndRight = false;
                            dx = 0;
                        }

                        // 如果高度小雨屏幕高度，则禁止上下移动
                        if (drawRecF.height() < getHeight()) {
                            dy = 0;
                            isNeedCheckTopAndBottom = false;
                        }
                        mScaleMatrix.postTranslate(dx, dy);
                        setImageMatrix(mScaleMatrix);
                        checkBorderWhenTraslate();

                    }
                }
                lastX = centerX;
                lastY = centerY;
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                lastTouchPointCount = 0;
                break;
            default:
                break;
        }

        return true;
    }

    /**
     * 保证触点缩放后平移不出现白边
     */
    public void checkBorderWhenTraslate() {
        RectF rectF2 = getMatrixRectF();
        float dx = 0;
        float dy = 0;
        if (rectF2 == null) return;
        if (rectF2.right < getWidth() && isNeedCheckLeftAndRight) {
            dx = getWidth() - rectF2.right;
        }
        if (rectF2.left > 0 && isNeedCheckLeftAndRight) {
            dx = -rectF2.left;
        }
        if (rectF2.top > 0 && isNeedCheckTopAndBottom) {
            dy = -rectF2.top;
        }
        if (rectF2.bottom < getHeight() && isNeedCheckTopAndBottom) {
            dy = getHeight() - rectF2.bottom;
        }
        mScaleMatrix.postTranslate(dx, dy);
        setImageMatrix(mScaleMatrix);
    }

    /**
     * 判断是否可以移动
     *
     * @param dx
     * @param dy
     * @return
     */

    private boolean isMoveAction(float dx, float dy) {

        return Math.sqrt(dx * dx + dy * dy) > onTouchSlop;
    }
}