package com.kedacom.customview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.kedacom.customview.R;


/**
 * 线冒样式
 *
 * @author Heaven Cheng Created on 2018/6/29.
 */
public class DividerView extends View {

    Paint mPaint;
    int mColor = -1;
    int mLineLength = 0;
    int mCircleRadius = 10;

    public DividerView(Context context) {
        this(context, null);
    }

    public DividerView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DividerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint();
        mPaint.setStrokeWidth(12);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        widthMeasureSpec = MeasureSpec.makeMeasureSpec(mLineLength + mCircleRadius * 2, MeasureSpec.EXACTLY);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int mDefaultColor = getResources().getColor(R.color.projectInfoNormal);

        mPaint.setColor(mColor == -1 ? mDefaultColor : mColor);
        canvas.drawLine(10, 20 + mCircleRadius, mLineLength, 20 + mCircleRadius, mPaint);
        float centerX = mLineLength + mCircleRadius;
        float centerY = 20 + mCircleRadius;
        canvas.drawCircle(centerX, centerY, mCircleRadius, mPaint);
    }

    /**
     * 设置颜色
     *
     * @param color
     */
    public void setColor(int color) {
        this.mColor = color;
    }

    /**
     * 设置长度
     *
     * @param lineLength
     */
    public void setLineLength(int lineLength) {
        if (lineLength == 0) {
            this.mLineLength = lineLength;
        } else {
            this.mLineLength = 50 + lineLength;
        }
    }

    /**
     * 设置圆角半径
     *
     * @param circleRadius
     */
    public void setCircleRadius(int circleRadius) {
        this.mCircleRadius = circleRadius;
    }
}
