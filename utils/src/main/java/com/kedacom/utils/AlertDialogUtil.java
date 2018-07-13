package com.kedacom.utils;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


/**
 * AlertDialog封装<br/>
 * <p/>
 * 补充：http://blog.csdn.net/caesardadi/article/details/11982721
 * <br/>
 * 使用Meterial风格的dialog(android.support.v7.app.AlertDialog)
 * <br/>
 *
 * @author caowj
 */
public class AlertDialogUtil {

    private static android.support.v7.app.AlertDialog.Builder builder;
    private static TextView tv_progress;

    /**
     * 温馨提示
     *
     * @param context
     * @param msg
     */
    public static void showSimpleDialog(Context context, String msg) {
        showDialog(context, true, "温馨提示", msg, null, "确定", null, null);
    }

    /**
     * 加载中(标题+图形)<br/>
     * 参考ProgressDialog
     *
     * @param context 上下文
     * @param msg     提示内容
     * @param width   宽
     * @param height  高
     * @return
     */
    public static AlertDialog Loading(Context context, String msg, int width, int height) {
        //锤子手机属性设置无效
//        if (MyAndroidUtils.isSmartisan()) {
//            width = width * 2;
//            height = height * 2;
//        }

//        builder = new android.support.v7.app.AlertDialog.Builder(context, android.support.v7.app.AlertDialog.THEME_DEVICE_DEFAULT_DARK);
        builder = new AlertDialog.Builder(context);
        builder.setCancelable(true);

        LayoutInflater lif = LayoutInflater.from(context);
        LinearLayout lay = (LinearLayout) lif.inflate(R.layout.loading_dialog, null);
        tv_progress = (TextView) lay.findViewById(R.id.load_txt);
        if (TextUtils.isEmpty(msg)) {
            tv_progress.setVisibility(View.GONE);
        } else {
            tv_progress.setVisibility(View.VISIBLE);
            tv_progress.setText(msg);
        }
        builder.setView(lay);

        android.support.v7.app.AlertDialog ad = builder.show();

        // 设置对话框大小
        WindowManager.LayoutParams lp = ad.getWindow().getAttributes();
        lp.width = width;//定义宽度
        lp.height = height;//定义高度
        lp.alpha = 0.7f;//值越小越透明
        ad.getWindow().setAttributes(lp);
        return ad;
    }

    /**
     * 默认宽高的dialogue（屏幕宽度的1/3）
     *
     * @param context
     * @param msg
     * @return
     */
    public static AlertDialog LoadingDefault(Context context, String msg) {
        int screenW = AppUtil.getScreenWidth(context);
        //默认宽度为屏幕的1/3
        int defaultW = screenW / 3;

        return Loading(context, msg, defaultW, defaultW);
    }

    /**
     * 修改当前进度
     *
     * @param progress
     */
    public static void changeText(String progress) {
        if (tv_progress != null) {
            tv_progress.setText(progress);
        }
    }

    /**
     * 加载中（图像）
     *
     * @param context 上下文
     * @param width   宽
     * @param height  高
     * @return
     */
    public static android.support.v7.app.AlertDialog showLoading(Context context, int width, int height) {
        return Loading(context, null, width, height);
    }

    /**
     * 关闭AlertDialog
     *
     * @param ad
     */
    public static void LoadingDismis(Dialog ad) {
        if (ad != null) {
            ad.dismiss();
            ad = null;
        }
        if (tv_progress != null) {
            tv_progress = null;
        }
        if (builder != null) {
            builder = null;
        }
    }


    /**
     * @param context
     * @param titleid
     * @param msgid
     * @param leftbtnid
     * @param rightbtnid
     * @param LeftOnClickListener
     * @param RightOnClickListener
     * @param cancelable
     */
    public static void showDialog(Context context, int titleid, int msgid,
                                  int leftbtnid, int rightbtnid, DialogInterface.OnClickListener LeftOnClickListener,
                                  DialogInterface.OnClickListener RightOnClickListener, boolean cancelable) {
        android.support.v7.app.AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(false);
        builder.setTitle(titleid);
        builder.setMessage(msgid);
        builder.setNegativeButton(leftbtnid, LeftOnClickListener);
        builder.setPositiveButton(rightbtnid, RightOnClickListener).create();
        builder.show();
    }

    /**
     * 显示Material风格的dialog
     *
     * @param mContext             上下文
     * @param cancelable           可否取消
     * @param titleStr             标题
     * @param messageStr           内容消息
     * @param leftBtn              取消按钮
     * @param rightBtn             确定按钮
     * @param leftOnClickListener  取消按钮事件
     * @param rightOnClickListener 确定按钮事件
     */
    public static void showDialog(Context mContext, boolean cancelable, String titleStr, String messageStr,
                                  String leftBtn, String rightBtn,
                                  OnClickListener leftOnClickListener,
                                  OnClickListener rightOnClickListener) {
        android.support.v7.app.AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setCancelable(cancelable);
        if (!TextUtils.isEmpty(titleStr)) {
            builder.setTitle(titleStr);
        }
        if (!TextUtils.isEmpty(messageStr)) {
            builder.setMessage(messageStr);
        }
        if (!TextUtils.isEmpty(leftBtn)) {
            builder.setNegativeButton(leftBtn, leftOnClickListener);
        }
        //可以设置两个setNegativeButton，效果有变化哦
        if (!TextUtils.isEmpty(rightBtn)) {
            builder.setPositiveButton(rightBtn, rightOnClickListener);
        }

        builder.create().show();
    }

    /**
     * 设置AlertDialog的宽高(默认，高度*0.7；宽度*0.9)
     *
     * @param builder
     */
    public static void setDialogBuilder(AlertDialog.Builder builder, Context mContext) {
        /*
         * 获取对话框的窗口对象及参数对象以修改对话框的布局设置,
         * 可以直接调用getWindow(),表示获得这个Activity的Window
         * 对象,这样这可以以同样的方式改变这个Activity的属性.
         */
        Window dialogWindow = builder.show().getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        dialogWindow.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);

        /*
         * lp.x与lp.y表示相对于原始位置的偏移.
         * 当参数值包含Gravity.LEFT时,对话框出现在左边,所以lp.x就表示相对左边的偏移,负值忽略.
         * 当参数值包含Gravity.RIGHT时,对话框出现在右边,所以lp.x就表示相对右边的偏移,负值忽略.
         * 当参数值包含Gravity.TOP时,对话框出现在上边,所以lp.y就表示相对上边的偏移,负值忽略.
         * 当参数值包含Gravity.BOTTOM时,对话框出现在下边,所以lp.y就表示相对下边的偏移,负值忽略.
         * 当参数值包含Gravity.CENTER_HORIZONTAL时
         * ,对话框水平居中,所以lp.x就表示在水平居中的位置移动lp.x像素,正值向右移动,负值向左移动.
         * 当参数值包含Gravity.CENTER_VERTICAL时
         * ,对话框垂直居中,所以lp.y就表示在垂直居中的位置移动lp.y像素,正值向右移动,负值向左移动.
         * gravity的默认值为Gravity.CENTER,即Gravity.CENTER_HORIZONTAL |
         * Gravity.CENTER_VERTICAL.
         *
         * 本来setGravity的参数值为Gravity.LEFT | Gravity.TOP时对话框应出现在程序的左上角,但在
         * 我手机上测试时发现距左边与上边都有一小段距离,而且垂直坐标把程序标题栏也计算在内了,
         * Gravity.LEFT, Gravity.TOP, Gravity.BOTTOM与Gravity.RIGHT都是如此,据边界有一小段距离
         */
        int screenW = AppUtil.getScreenWidth(mContext);
        int screenH = AppUtil.getScreenHeight(mContext);
        lp.x = 0; // 新位置X坐标
        lp.y = 0; // 新位置Y坐标
        lp.width = (int) (screenW * 0.9); // 宽度
        lp.height = (int) (screenH * 0.7); // 高度
        lp.alpha = 0.9f; // 透明度
        dialogWindow.setAttributes(lp);
    }
}
