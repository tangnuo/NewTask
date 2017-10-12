package com.example.caowj.newtask.utils;

import android.content.Context;
import android.util.DisplayMetrics;

/**
 * 密度转换工具
 * package: com.jsfengling.qipai.tools
 * className: DensityUtil
 *
 * @author caowj
 *         date：2015年9月17日 下午6:33:14
 */
public class DensityUtil {

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素) <br/>
     */
    public static int dip2px(Context context, float dpValue) {
        DisplayMetrics metric = context.getResources().getDisplayMetrics();
        int width = metric.widthPixels;     // 屏幕宽度（像素）
        int height = metric.heightPixels;   // 屏幕高度（像素）
        float density = metric.density;      // 屏幕密度（0.75 / 1.0 / 1.5）
        int densityDpi = metric.densityDpi;  // 屏幕密度DPI（120 / 160 / 240）
        float scaledDensity = metric.scaledDensity;  // 屏幕密度DPI（120 / 160 / 240）

        //写法二（系统自带方法。把指定的单位的值转换成和屏幕尺寸参数相关的（以px为单位）的值。）
//        int s = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpValue, metric);
        return (int) (dpValue * density + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp <br/>
     */
    public static int px2dip(Context context, float pxValue) {
        DisplayMetrics metric = context.getResources().getDisplayMetrics();
        final float scale = metric.density;

        return (int) (pxValue / scale + 0.5f);
    }


    public static int getHeightInPx(Context context) {
        final int height = context.getResources().getDisplayMetrics().heightPixels;
        return height;
    }

    public static int getWidthInPx(Context context) {
        final int width = context.getResources().getDisplayMetrics().widthPixels;
        return width;
    }

    public static int getHeightInDp(Context context) {
        final float height = context.getResources().getDisplayMetrics().heightPixels;
        int heightInDp = px2dip(context, height);
        return heightInDp;
    }

    public static int getWidthInDp(Context context) {
        final float width = context.getResources().getDisplayMetrics().widthPixels;
        int widthInDp = px2dip(context, width);
        return widthInDp;
    }
}
