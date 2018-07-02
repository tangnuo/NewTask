package com.kedacom.customview.view;


import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kedacom.customview.R;


/**
 * 标签视图（图片方向）
 * Created by Pan S.Q
 * on 2017/4/28.
 */

public class LabelView extends LinearLayout {

    private final String TAG = getClass().getSimpleName();
    public Direction direction;
    int textBgWidth = 126;//文字背景图的宽度126px
    int textBgHeight = 41;//背景图的高度值41px
    int effectiveWidth = 100;//文字有效默认最小宽度100px
    int effectiveHeight = 31;//文字有效默认最小高度31px
    int pointWidth = 62;//圆形图形宽度值
    int textHeight; //文字的高度
    //坐标点
    private ImageView mCoordinate;
    //标签内容
    private TextView mLabelContent;
    //坐标动画
    private AnimationDrawable animationDrawable;
    //标签内容
    private String text = "";
    //标签指示方向图片数组
    private int[] labels = new int[]{
            R.drawable.icon_label_left,
            R.drawable.icon_label_right
    };
    public LabelView(Context context) {
        super(context);
        initData(context);
    }


    public LabelView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initData(context);
    }

    public LabelView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initData(context);
    }

    /**
     * sp转px
     *
     * @param context
     * @param spValue
     * @return
     */
    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    /**
     * 3.设置控件显示的位置
     *
     * @param parent      将该布局添加到此父容器中
     * @param width       父容器的宽度
     * @param height      父容器的高度
     * @param proportionX x坐标占比
     * @param proportionY y坐标占比
     */
    public void setDisplayPosition(RelativeLayout parent, int width, int height, float proportionX, float proportionY) {
        /*62：表示闪点图的宽度值/2表示居中点位置，作用是将该控件的坐标位置以闪点位置中心作为起点
          39：表示对话框的高度值/2同上
        */
        //1.将该自定义控件添加到指定的父布局中
        parent.addView(this);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//        LogUtil.d(TAG, "width:" + width + "\theight:" + height + "\tleft:" + (width * proportionX - 62 / 2) + "\ttop:" + (height * proportionY - 39 / 2));
        //实际Y轴显示位置
        int showHeight = textHeight > effectiveHeight ? (textBgHeight + (textHeight - effectiveHeight)) / 2 : textBgHeight / 2;
        if (direction == Direction.RIGHT) {
            //表示方向朝右
            int textWidth = (int) mLabelContent.getPaint().measureText(text);
//            LogUtil.d(TAG, text + "\tsize:" + textWidth);
            //实际X轴显示位置
            int showWidth = textWidth > effectiveWidth ? textBgWidth + (textWidth - effectiveWidth) + pointWidth / 2 : textBgWidth + pointWidth / 2;
            layoutParams.setMargins((int) (width * proportionX - showWidth), (int) (height * proportionY - showHeight), 0, 0);
        } else {
            //表示方向朝左
            layoutParams.setMargins((int) (width * proportionX - pointWidth / 2), (int) (height * proportionY - showHeight), 0, 0);
        }
        this.setLayoutParams(layoutParams);
    }

    /**
     * 2.设置标签的内容
     *
     * @param text
     */
    public void setLabelContent(String text) {
        this.text = text;
        if (TextUtils.isEmpty(text)) {
            mLabelContent.setText("");
            return;
        }
        mLabelContent.setText(text);
    }

    /**
     * 1.设置标签方向
     *
     * @param direction Direction.LEFT:左 Direction.RIGHT:右
     */
    public void setDirection(Direction direction) {
        this.direction = direction;
        switch (direction) {
            case LEFT:
                mLabelContent.setBackgroundResource(labels[0]);
                this.addView(mCoordinate);
                this.addView(mLabelContent);
                break;
            case RIGHT:
                mLabelContent.setBackgroundResource(labels[1]);
                this.addView(mLabelContent);
                this.addView(mCoordinate);
                break;
        }
    }

    /**
     * 初始化数据
     *
     * @param context
     */
    private void initData(Context context) {
        textHeight = sp2px(getContext(), 12);
        //设置父容器宽高参数
        ViewGroup.LayoutParams parent = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        this.setLayoutParams(parent);
        //设置方向为水平
        this.setOrientation(HORIZONTAL);
        //设置内容垂直居中
        this.setGravity(Gravity.CENTER_VERTICAL);

        mCoordinate = new ImageView(context);
        LayoutParams param = new LayoutParams((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, 62f, context.getResources().getDisplayMetrics()),
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, 62f, context.getResources().getDisplayMetrics()));
        mCoordinate.setLayoutParams(param);
        mCoordinate.setScaleType(ImageView.ScaleType.CENTER_CROP);
        mCoordinate.setBackgroundResource(R.drawable.choice_label);
        animationDrawable = (AnimationDrawable) mCoordinate.getBackground();
        animationDrawable.start();

        mLabelContent = new TextView(context);
        mLabelContent.setLayoutParams(parent);
        //字体颜色为白色
        mLabelContent.setTextColor(Color.parseColor("#FFFFFF"));
        //字体大小
        mLabelContent.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
        mLabelContent.setText(text);
        mLabelContent.setGravity(Gravity.CENTER);
    }

    /**
     * 内存回收
     */
    public void onDestory() {

        if (mCoordinate != null) {
            this.removeView(mCoordinate);
            mCoordinate = null;
            if (animationDrawable != null) {
                //动画停止
                if (animationDrawable.isRunning()) {
                    animationDrawable.stop();
                }
                animationDrawable = null;
            }
        }

        if (mLabelContent != null) {
            this.removeView(mLabelContent);
            mLabelContent = null;
        }
    }

    //方向
    public enum Direction {
        LEFT,
        RIGHT
    }
}
