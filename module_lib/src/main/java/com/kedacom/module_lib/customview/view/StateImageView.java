package com.kedacom.module_lib.customview.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.util.Log;

import com.kedacom.module_lib.R;


/**
 * Created by chumengwei on 2018/3/14.
 */

public class StateImageView extends AppCompatImageView {

    private boolean isToggle;
    private StateImageOnStateChangedListener listener;
    private Drawable toggleDrawable;
    private Drawable commonDrawable;
    private boolean auto;

    public StateImageView(Context context) {
        super(context);
        initStateImageView(context, null);
    }

    public StateImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initStateImageView(context, attrs);
    }

    public StateImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initStateImageView(context, attrs);
    }

    private void initStateImageView(Context context, AttributeSet attrs) {
        commonDrawable = getDrawable();
        if (null == commonDrawable) {
            Log.e("StateImageView", "must set src");
            return;
        }
        @SuppressLint("CustomViewStyleable")
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.StateImage);
        if (null != attrs) {
            toggleDrawable = ta.getDrawable(R.styleable.StateImage_toggleImage);
            auto = ta.getBoolean(R.styleable.StateImage_auto, true);
        }
        ta.recycle();
        if (null == toggleDrawable) {
            Log.e("StateImageView", "must set toggleImage");
            return;
        }
        setOnClickListener(v -> {
            if (auto) {
                isToggle = !isToggle;
                changeToggleState();
            }
        });
    }

    private void changeToggleState() {
        setImageDrawable(isToggle);
        if (null != listener) {
            listener.onStateChanged(isToggle);
        }
    }

    private void setImageDrawable(boolean state) {
        setImageDrawable(state ? toggleDrawable : commonDrawable);
    }

    public void setOnStateChangedListener(StateImageOnStateChangedListener listener) {
        this.listener = listener;
    }

    public boolean getToggleState() {
        return isToggle;
    }

    public void setToggleState(boolean state) {
        if (isToggle != state) {
            isToggle = state;
            changeToggleState();
        }
    }

}
