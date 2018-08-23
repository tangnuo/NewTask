package com.kedacom.module_lib.customview.view;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kedacom.module_lib.R;


/**
 * Author：chumengwei on 2018/7/19 13:47
 * <p>
 * We can do anything we want to do if we stick to it long enough.
 */
public class CommonTextLabel extends LinearLayout {


    private static final int COMMON_TEXT_SIZE = 16;
    private static final int COMMON_MARGIN = 16;
    private static final int COMMON_TEXT_MARGIN = 10;
    private static final int COMMON_LEFT_IMAGE_MARGIN = 10;
    private static final int COMMON_RIGHT_IMAGE_MARGIN = 5;
    private int COMMON_TEXT_COLOR = getColor(R.color.colorGrayl);
    private TextView leftText;
    private TextView rightText;

    public CommonTextLabel(Context context) {
        super(context);
        init(context, null);
    }

    public CommonTextLabel(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public CommonTextLabel(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CommonTextLabel(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        LinearLayout commonTextLabel = (LinearLayout) LayoutInflater.from(context).
                inflate(R.layout.common_text_label, this, true);

        leftText = commonTextLabel.findViewById(R.id.left_text);
        rightText = commonTextLabel.findViewById(R.id.right_text);
        ImageView rightImage = commonTextLabel.findViewById(R.id.right_image);
        ImageView leftImage = commonTextLabel.findViewById(R.id.left_image);
        RelativeLayout rlRightText = commonTextLabel.findViewById(R.id.rl_right_text);

        if (null == attrs) {
            leftText.setTextColor(COMMON_TEXT_COLOR);
            rightText.setTextColor(COMMON_TEXT_COLOR);
            leftText.setTextSize(COMMON_TEXT_SIZE);
            rightText.setTextSize(COMMON_TEXT_SIZE);
        } else {
            @SuppressLint("CustomViewStyleable")
            TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.commonTextLabel);

            Drawable drawable = ta.getDrawable(R.styleable.commonTextLabel_leftImageDrawable);
            if (null != drawable) {
                leftImage.setImageDrawable(drawable);
                LayoutParams layoutParams = (LayoutParams) leftImage.getLayoutParams();
                layoutParams.leftMargin = dp2px(ta.getDimensionPixelSize(R.styleable.commonTextLabel_rightTextLabelMargin, COMMON_LEFT_IMAGE_MARGIN));
            } else {
                leftImage.setVisibility(GONE);
            }

            leftText.setTextSize(ta.getInt(R.styleable.commonTextLabel_leftTextSize, COMMON_TEXT_SIZE));
            leftText.setTextColor(ta.getColor(R.styleable.commonTextLabel_leftTextColor, COMMON_TEXT_COLOR));
            leftText.setText(ta.getString(R.styleable.commonTextLabel_leftText));
            LayoutParams leftTextLayoutParams = (LayoutParams) leftText.getLayoutParams();
            if (null != drawable) {
                leftTextLayoutParams.leftMargin = dp2px(COMMON_TEXT_MARGIN);
            } else {
                leftTextLayoutParams.leftMargin = dp2px(COMMON_MARGIN);
            }

            boolean imageVisible = ta.getBoolean(R.styleable.commonTextLabel_rightImageVisible, true);
            if (imageVisible) {
                LayoutParams layoutParams = (LayoutParams) rightImage.getLayoutParams();
                layoutParams.rightMargin = dp2px(ta.getDimensionPixelSize(R.styleable.commonTextLabel_rightTextLabelMargin, COMMON_RIGHT_IMAGE_MARGIN));
            } else {
                rightImage.setVisibility(GONE);
            }

            rightText.setTextSize(ta.getInt(R.styleable.commonTextLabel_rightTextSize, COMMON_TEXT_SIZE));
            rightText.setTextColor(ta.getColor(R.styleable.commonTextLabel_rightTextColor, COMMON_TEXT_COLOR));
            String rightTextString = ta.getString(R.styleable.commonTextLabel_rightText);
            if (!TextUtils.isEmpty(rightTextString)) {
                rightText.setText(rightTextString);
            }
            LayoutParams rightTextLayoutParams = (LayoutParams) rlRightText.getLayoutParams();
            if (imageVisible) {
                rightTextLayoutParams.rightMargin = dp2px(COMMON_TEXT_MARGIN);
            } else {
                rightTextLayoutParams.rightMargin = dp2px(COMMON_MARGIN);
            }
            ta.recycle();
        }
    }


    private int getColor(int color) {
        return getResources().getColor(color);
    }

    private int dp2px(int dpValue) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpValue, getResources().getDisplayMetrics());
    }

    public void setLeftText(String text) {
        leftText.setText(text);
    }

    public void setRightText(String text) {
        rightText.setText(text);
    }

}
