package com.example.caowj.newtask.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.example.caowj.newtask.R;
import com.example.caowj.newtask.utils.LogUtil;

/**
 * 自定义属性（attrs）<br/>
 * http://blog.csdn.net/iispring/article/details/50708044
 * package: com.example.caowj.newtask.widget
 * author: Administrator
 * date: 2017/9/6 10:31
 */
public class MyView4 extends View {
    //定义画笔和初始位置
    Paint p = new Paint();
    public float currentX = 50;
    public float currentY = 50;
    public int textColor;
    int textSize;
    String aString = "";

    public MyView4(Context context) {
        super(context);
    }

    public MyView4(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        //写法一1：不依赖declare-styleable
//        int[] coustom_attrs = {R.attr.TextColor2, R.attr.TextSize2};
//        TypedArray array1 = context.obtainStyledAttributes(attrs, coustom_attrs);
        textColor = attrs.getAttributeIntValue(R.attr.TextColor2, 0xff000000);
        textSize = attrs.getAttributeIntValue(R.attr.TextSize2, 12);

        //写法一（进阶）：
//首先判断attrs是否为null
        if (attrs != null) {
            //获取AttributeSet中所有的XML属性的数量
            int count = attrs.getAttributeCount();
            //遍历AttributeSet中的XML属性
            for (int i = 0; i < count; i++) {
                //获取attr的资源ID
                int attrResId = attrs.getAttributeNameResource(i);
                switch (attrResId) {
                    case R.attr.TextColor2:
                        //customText属性
//                        textColor = attrs.getAttributeValue(i);
                        attrs.getAttributeIntValue(i, 0xff000000);
                        break;
                    case R.attr.TextSize2:
                        //customColor属性
                        //如果读取不到对应的颜色值，那么就用黑色作为默认颜色
                        textSize = attrs.getAttributeIntValue(i, 12);
                        break;
                }
            }
        }

        LogUtil.myD("sss:" + textSize);

        //写法二1：使用declare-styleable（分组）
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.CaowjMyView4);

        textColor = array.getColor(R.styleable.CaowjMyView4_TextColor, Color.GRAY);
//        textSize = array.getInteger(R.styleable.CaowjMyView4_TextSize, 20);
        float aFloat = array.getFloat(R.styleable.CaowjMyView4_caowjFloat, 10f);
        aString = array.getString(R.styleable.CaowjMyView4_caowjString);
        Boolean aBoolean = array.getBoolean(R.styleable.CaowjMyView4_caowjBoolen, false);
        float dimension = array.getDimension(R.styleable.CaowjMyView4_caowjDimension, 12f);
        array.recycle();

//        写法二(进阶)：
        //首先判断attributeSet是否为null
        if (attrs != null) {
            //获取当前MyView所在的Activity的theme
            Resources.Theme theme = getContext().getTheme();
            //通过theme的obtainStyledAttributes方法获取TypedArray对象
            TypedArray typedArray = theme.obtainStyledAttributes(attrs, R.styleable.CaowjMyView4, 0, 0);
            //获取typedArray的长度
            int count = typedArray.getIndexCount();
            //通过for循环遍历typedArray
            for (int i = 0; i < count; i++) {
                //通过typedArray的getIndex方法获取指向R.styleable中对应的属性ID
                int styledAttr = typedArray.getIndex(i);
                switch (styledAttr) {
                    case R.styleable.CaowjMyView4_caowjString:
                        //如果是R.styleable.MyView_customText，表示属性是customText
                        //通过typedArray的getString方法获取字符串值
                        aString = typedArray.getString(i);
                        break;
                    case R.styleable.CaowjMyView4_TextColor:
                        //如果是R.styleable.MyView_customColor，表示属性是customColor
                        //通过typedArray的getColor方法获取整数类型的颜色值
                        textColor = typedArray.getColor(i, 0xFF000000);
                        break;
                    case R.styleable.CaowjMyView4_TextSize:
                        textSize = typedArray.getIndex(i);
                        break;
                }
            }
            //在使用完typedArray之后，要调用recycle方法回收资源
            typedArray.recycle();
        }
            

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //画一个蓝色的圆形
        p.setColor(Color.BLUE);
        canvas.drawCircle(currentX, currentY, 30, p);

        //设置文字和颜色，这里的颜色是资源文件values里面的值
        p.setColor(textColor);
        p.setTextSize(textSize);
        canvas.drawText(aString, currentX - 30, currentY + 50, p);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        currentX = event.getX();
        currentY = event.getY();
        invalidate();//重新绘制图形
        return true;
    }
}
