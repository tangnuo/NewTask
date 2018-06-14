package com.example.caowj.newtask.utils.business;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.Selection;
import android.text.Spannable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.caowj.newtask.base.BaseApp;
import com.example.caowj.newtask.mvp.constants.WSConstants;
import com.example.caowj.newtask.mvp.listener.BroadcastCallback;
import com.kedacom.utils.DensityUtil;
import com.kedacom.utils.LogUtil;

import java.util.List;

/**
 * 基于android的工具类（包含业务逻辑）
 * package: com.jsfengling.qipai.tools
 * className: MyAndroidUtils
 *
 * @author caowj
 * date：2016年1月18日 下午5:00:58
 */
public class MyAndroidUtils {
    public final static String SERVICE_ACTION = "com.baidu.android.pushservice.action.PUSH_SERVICE";
    private static final String myTag = "MyAndroidUtils~~~";
    static Toast toast;

    public static void handleBroadcastReturn(String code, BroadcastCallback broadcastCallback) {
        if (code != null && code.equals(WSConstants.CODE_NUM_OK)) {
            broadcastCallback.return1001();
        } else if (code != null && code.equals(WSConstants.CODE_DATA_NULL)) {
            broadcastCallback.return1002();
            LogUtil.myD(myTag + ",code:" + code);
        } else {
            broadcastCallback.returnOther(code);
            LogUtil.myD(myTag + "，code:" + code);
        }
    }

    /**
     * 获取TalkingData的渠道信息<br/>
     * 如果动态获取到的渠道信息为空，默认为官方
     *
     * @param ctx
     * @return
     */
    public static String getAppChannelValue(Context ctx) {
        //TD_CHANNEL_ID是TalkingData的Key
        String app_channel = getAppMetaData(ctx, "TD_CHANNEL_ID");
        if (TextUtils.isEmpty(app_channel)) {
            LogUtil.myE(myTag + "，获取TalkingData渠道信息失败：" + app_channel);
            //默认为官方渠道
            app_channel = "official";
        }
        LogUtil.myD("渠道信息：" + app_channel);
        return app_channel;
    }

    /**
     * 获取application中指定的meta-data
     *
     * @return 如果没有获取成功(没有对应值 ， 或者异常)，则返回值为空
     */
    public static String getAppMetaData(Context ctx, String key) {
        if (ctx == null || TextUtils.isEmpty(key)) {
            return null;
        }
        String resultData = null;
        try {
            PackageManager packageManager = ctx.getPackageManager();
            if (packageManager != null) {
                ApplicationInfo applicationInfo = packageManager.getApplicationInfo(ctx.getPackageName(), PackageManager.GET_META_DATA);
                if (applicationInfo != null) {
                    if (applicationInfo.metaData != null) {
                        resultData = applicationInfo.metaData.getString(key);
                    }
                }
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }


        return resultData;
    }


    /**
     * 如果输入法在窗口上已经显示，则隐藏，反之则显示
     *
     * @param mContext
     */
    public static void toggleSoftInput(Context mContext) {
        //打开软键盘
        InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
    }

    /**
     * 强制显示
     */
    public static void showSoftInput(Context mContext, View view) {
        //打开软键盘
        InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(view, InputMethodManager.SHOW_FORCED);
    }


    /**
     * 强制隐藏键盘
     *
     * @param mContext
     * @param view
     */
    public static void hideSoftInput(Context mContext, View view) {
        //打开软键盘
        InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0); //强制隐藏键盘
    }


    public static String getDataColumn(Context context, Uri uri, String selection,
                                       String[] selectionArgs) {

        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {column};

        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs,
                    null);
            if (cursor != null && cursor.moveToFirst()) {
                final int index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }


    /**
     * 获取已安装apk的版本号
     *
     * @param context
     * @return
     */
    public static int getVersionCode(Context context) {
        if (context == null) {
            return 0;
        }
        try {
            PackageInfo pi = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return pi.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 获取已安装apk的版本名称
     *
     * @param context
     * @return
     */
    public static String getVersionName(Context context) {
        try {
            PackageInfo pi = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return pi.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 动态设置图片ListView的高度<br/>
     * 要求DetailCommentAdapter使用LinearLayout布局，否则获取不到listItem
     * 但是这个方法有个两个细节需要注意：
     * 一是Adapter中getView方法返回的View的必须由LinearLayout组成，因为只有LinearLayout才有measure()方法，如果使用其他的布局如RelativeLayout，
     * 在调用listItem.measure(0,0);时就会抛异常，因为除LinearLayout外的其他布局的这个方法就是直接抛异常的，没理由…。
     * 我最初使用的就是这个方法，但是因为子控件的顶层布局是RelativeLayout，所以一直报错，不得不放弃这个方法。
     * 二是需要手动把ScrollView滚动至最顶端，因为使用这个方法的话，默认在ScrollView顶端的项是ListView，具体原因不了解，求大神解答…可以在Activity中设置：
     * <p>
     * http://blog.csdn.net/subaohao/article/details/8250186
     *
     * @param listView
     */
    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            LogUtil.myD("listAdapter is null");
            return;
        }
        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            if (listItem != null) {
                listItem.measure(0, 0);
                totalHeight += listItem.getMeasuredHeight();
            } else {
                LogUtil.myD(",listItem is null，adapter中请使用LinearLayout布局");
            }

        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        int hei = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));

        params.height = hei;
        listView.setLayoutParams(params);
    }

    /**
     * 根据URI返回图片的真实路径（适合android4.4以下）<br/>
     * 读取相册：file:///storage/emulated/0/DCIM/Camera/IMG_20160610_203246.jpg<br/>
     * 读取本地：content://media/external/images/media/376320
     * 转换为：:/storage/emulated/0/DCIM/Camera/IMG_20160610_203246.jpg
     *
     * @param mContext
     * @param uri
     */
    public static String getImageUrlByUri(Context mContext, Uri uri) {
        String img_path = "";
        if (uri != null) {
            if (uri.toString().startsWith("file:")) {
                img_path = uri.getPath();
            } else {
                String[] proj = {MediaStore.Images.Media.DATA};
                Cursor actualimagecursor = mContext.getContentResolver().query(uri, proj, null, null, null);
                if (actualimagecursor != null) {
                    int actual_image_column_index = actualimagecursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                    actualimagecursor.moveToFirst();
                    img_path = actualimagecursor.getString(actual_image_column_index);
                } else {
                    LogUtil.myE("根据URI返回图片的真实路径失败，actualimagecursor is null,Uri:" + uri);
                }
            }
        } else {
            LogUtil.myE("根据URI返回图片的真实路径失败，uri is null,Uri:" + uri);
        }

        return img_path;
    }

    /**
     * 取得图片uri的列名和此列的详细信息
     *
     * @param mContext
     * @param uri
     */
    public static void showImageUri(Context mContext, Uri uri) {
        Cursor cursor = mContext.getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        for (int i = 0; i < cursor.getColumnCount(); i++) {// 取得图片uri的列名和此列的详细信息
            System.out.println(i + "-" + cursor.getColumnName(i) + "-" + cursor.getString(i));
        }
    }

    /**
     * 根据宽度或高度等比例缩放高度或宽度
     * 1、num = 0;就表示宽度固定，等比例缩放高度
     * <p>
     * 2、num = 1;就表示高度固定，等比例缩放宽度
     *
     * @param mContext
     * @param fixedValue 固定值(单位dp)
     * @param num
     * @param wScale
     * @param hScale
     * @return
     */
    public static int getWidthOrHeightByScale(Context mContext, int fixedValue, int num, int wScale, int hScale) {
        int result = 0;
        fixedValue = DensityUtil.dip2px(mContext, fixedValue);
        if (num == 0) {
            //宽度固定，等比例缩放高度
            result = fixedValue * hScale / wScale;
        } else if (num == 1) {
            //高度固定，等比例缩放宽度
            result = fixedValue * wScale / hScale;
        } else {
            System.out.println("-其他错误-");
        }

        return result;
    }

    /**
     * 给RelativeLayout设置bitmap背景
     *
     * @param mContext
     * @param bitmap
     * @param relativeLayout
     */
    public static void setBackgroundForRL(Context mContext, Bitmap bitmap, RelativeLayout relativeLayout) {
        Drawable bd = new BitmapDrawable(mContext.getResources(), bitmap);
        if (relativeLayout != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                relativeLayout.setBackground(bd);
            } else {
                relativeLayout.setBackgroundDrawable(bd);
            }
        } else {
            LogUtil.myW("设置背景失败，relativeLayout is null");
        }

    }


    /**
     * 使用getApplicationContext（）防止内存泄漏<br/>
     * http://blog.csdn.net/u011781834/article/details/42553249<br/>
     *
     * @param mContext
     * @param text
     */
    public static void showShortToast(Context mContext, String text) {
        if (toast == null) {
            toast = Toast.makeText(mContext.getApplicationContext(), text, Toast.LENGTH_SHORT);
        } else {
            toast.setText(text);
        }
        toast.show();
    }

    public static void showLongToast(Context mContext, String text) {
        if (toast == null) {
            toast = Toast.makeText(mContext.getApplicationContext(), text, Toast.LENGTH_LONG);
        } else {
            toast.setText(text);
        }
        toast.show();
    }

    /**
     * 服务端返回码提示信息
     *
     * @param returnCode
     * @param myBundle   异常原因
     */
    public static void returnCodePrompt(Context mContext, String returnCode, Bundle myBundle) {
        if (returnCode != null) {
            if (returnCode.equals(WSConstants.CODE_ERROR)) {
                //returnCode：1000
                showShortToast(mContext, "服务端错误");
            } else if (returnCode.equals(WSConstants.CODE_NUM_OK)) {
                //returnCode：1001
                //正常返回
            } else if (returnCode.equals(WSConstants.CODE_DATA_NULL)) {
                //returnCode：1002
                //showShortToast(mContext, "返回数据为空");
            } else if (returnCode.equals(WSConstants.CODE_PHONE_EXIST)) {
                //returnCode：1003
                showShortToast(mContext, "手机号已注册");
            } else if (returnCode.equals(WSConstants.CODE_LOGIN_FAILED)) {
                //returnCode：1004
                showShortToast(mContext, "密码错误");
            } else if (returnCode.equals(WSConstants.CODE_SCRIPT_ATTACK)) {
                //returnCode：1005
                showShortToast(mContext, "脚本攻击");
            } else if (returnCode.equals(WSConstants.CODE_DELETED)) {
                //returnCode：1006
                showShortToast(mContext, "已删除(逻辑删除)");
            } else if (returnCode.equals(WSConstants.CODE_PARAMETER_ERROR)) {
                //returnCode：1007
                showShortToast(mContext, "客户端传递参数错误");
            } else if (returnCode.equals(WSConstants.CODE_INVALID_PARAM_)) {
                //returnCode：1009
                showShortToast(mContext, "传来的参数无效");
            } else if (returnCode.equals(WSConstants.CODE_AFFAIR_FAILED)) {
                //returnCode：1010
                showShortToast(mContext, "事务执行失败");
            } else if (returnCode.equals(WSConstants.CODE_NOW_FIRST)) {
                //returnCode：1011
                showShortToast(mContext, "当前商品的最高价格");
            } else if (returnCode.equals(WSConstants.CODE_UNMATCHED)) {
                //returnCode：1012
                showShortToast(mContext, "删除用户地址, 地址编号与用户编号不匹配");
            } else if (returnCode.equals(WSConstants.CODE_USER_NOT_EXIST)) {
                //returnCode：1013
                showShortToast(mContext, "用户不存在");
            } else if (returnCode.equals(WSConstants.CODE_GOODS_NOT_EXIST)) {
                //returnCode：1014
                showShortToast(mContext, "拍品不存在");
            } else if (returnCode.equals(WSConstants.CODE_LOW_PRICE)) {
                //returnCode：1015
                showShortToast(mContext, "价格低于当前拍品价格");
            } else if (returnCode.equals(WSConstants.CODE_ADDRESS_NUM_NOT_EXIST)) {
                //returnCode：1016
                showShortToast(mContext, "收货地址编号不存在");
            } else if (returnCode.equals(WSConstants.CODE_ADDRESS_ID_NOT_EXIST)) {
                //returnCode：1017
                showShortToast(mContext, "收货地址ID不存在");
            } else if (returnCode.equals(WSConstants.CODE_PAYMENT_CODE)) {
                //returnCode：1018
                showShortToast(mContext, "支付密码不匹配");
            } else if (returnCode.equals(WSConstants.CODE_VALIDATE_CODE)) {
                //returnCode：1019
                showShortToast(mContext, "验证码不匹配");
            } else if (returnCode.equals(WSConstants.CODE_EMAIL_ALREADY_BINDING)) {
                //returnCode：1020
                showShortToast(mContext, "邮箱已被绑定");
            } else if (returnCode.equals(WSConstants.CODE_EMAIL_ALREADY_VERIFY)) {
                //returnCode：1021
                showShortToast(mContext, "身份证已被实名");
            } else if (returnCode.equals(WSConstants.CODE_PAYMENT_ERROR)) {
                //returnCode：1022
                showShortToast(mContext, "支付密码不正确");
            } else if (returnCode.equals(WSConstants.CODE_ORDER_NOT_EXIST)) {
                //returnCode：1023
                showShortToast(mContext, "订单不存在");
            } else if (returnCode.equals(WSConstants.CODE_REMIND_TIME_OUT)) {
                //returnCode：1024
                showShortToast(mContext, "预约失败");
            } else if (returnCode.equals(WSConstants.CODE_REMIND_NONSUPPORT)) {
                //returnCode：1025
                showShortToast(mContext, "该拍品状态无法预约");
            } else if (returnCode.equals(WSConstants.CODE_IS_USED)) {
                //returnCode：1026
                showShortToast(mContext, "该验证码已被使用");
            } else if (returnCode.equals(WSConstants.CODE_NOT_LOWER)) {
                //returnCode：1027
                showShortToast(mContext, "拍品出价不能比当前最高出价低");
            } else if (returnCode.equals(WSConstants.CODE_IS_FINISHED)) {
                //returnCode：1028
                showShortToast(mContext, "竞拍已结束");
            } else if (returnCode.equals(WSConstants.CODE_UNSTART)) {
                //returnCode：1029
                showShortToast(mContext, "竞拍未开始");
            } else if (returnCode.equals(WSConstants.CODE_IS_NOT_TODAY)) {
                //returnCode：1030
                showShortToast(mContext, "该拍品不是今日拍品");
            } else if (returnCode.equals(WSConstants.CODE_IS_PROXY)) {
                //returnCode：1031
                showShortToast(mContext, "低于最高代理价");
            } else if (returnCode.equals(WSConstants.CODE_IS_FREEZE)) {
                //returnCode：1032
                showShortToast(mContext, "账号被冻结");
            } else if (returnCode.equals(WSConstants.CODE_ORDER_IS_EXIST)) {
                //returnCode：1033
                showShortToast(mContext, "拍品已生成订单");
            } else if (returnCode.equals(WSConstants.CODE_SYSNEWS_IS_READ)) {
                //returnCode：1034
                showShortToast(mContext, "站内信已是已读状态");
            } else if (returnCode.equals(WSConstants.CODE_PAIPIN_IS_INVALID)) {
                //returnCode：1035
                showShortToast(mContext, "拍品已失效");
            } else if (returnCode.equals(WSConstants.CODE_NOTE_NOT_EXIST)) {
                //returnCode：1036
                showShortToast(mContext, "优惠码不存在");
            } else if (returnCode.equals(WSConstants.CODE_NOTE_IS_USED)) {
                //returnCode：1037
                showShortToast(mContext, "优惠码已使用");
            } else if (returnCode.equals(WSConstants.CODE_NOTE_IS_OUTTIME)) {
                //returnCode：1038
                showShortToast(mContext, "优惠码已过期");
            } else if (returnCode.equals(WSConstants.CODE_LAZY_WEIGH)) {
                //returnCode：1039
                showShortToast(mContext, "拍品库存数量不足");
            } else if (returnCode.equals(WSConstants.CODE_NOT_RETURN)) {
                //returnCode：1040
                showShortToast(mContext, "该订单无法退货");
            } else if (returnCode.equals(WSConstants.CODE_EQUALS_PROXY_PRICE)) {
                //returnCode：1041
                showShortToast(mContext, "您的出价与其他代理价相同，请重新出价");
            } else if (returnCode.equals(WSConstants.CODE_TOKEN_WRONG)) {
                //returnCode：1042
                showShortToast(mContext, "Token无效");
            } else if (returnCode.equals(WSConstants.CODE_LACK_BALANCE)) {
                //returnCode：1043
                showShortToast(mContext, "余额不足");
            } else if (returnCode.equals(WSConstants.CODE_LACK_SILVER)) {
                //returnCode：1044
                showShortToast(mContext, "银子不足");
            } else if (returnCode.equals(WSConstants.CODE_UNALLOWED_WITHDRAW)) {
                //returnCode：1045
                showShortToast(mContext, "客官，您今日提现次数已满。");
            } else if (returnCode.equals(WSConstants.CODE_UPPER_LIMIT)) {
                //returnCode：1046
                showShortToast(mContext, "客官，您今日提现金额已达上限。");
            } else if (returnCode.equals(WSConstants.CODE_ORDER_UNCANCLE)) {
                //returnCode：1047
                showShortToast(mContext, "客官，这个订单不允许取消。");
            } else if (returnCode.equals(WSConstants.CODE_USER_NOT_FROZEN)) {
                //returnCode：1048
                showShortToast(mContext, "该用户未被冻结。");
            } else if (returnCode.equals(WSConstants.CODE_DATA_REMIND_OK)) {
                //returnCode：10002
                showShortToast(mContext, "预约成功");
            } else if (returnCode.equals(WSConstants.CODE_DATA_REMIND_NO)) {
                //returnCode：10003
                showShortToast(mContext, "取消预约成功");
            } else if (returnCode.equals(WSConstants.CODE_STRING_IS_NULL)) {
                //1050
                showShortToast(mContext, "空字符串");
            } else if (returnCode.equals(WSConstants.CODE_FRIEND_ID_NOT_EXIST)) {
                //1051
                showShortToast(mContext, "拍友圈ID不存在");
            } else if (returnCode.equals(WSConstants.CODE_FRIEND_ID_NOT_MISMATCHING)) {
                //105２
                showShortToast(mContext, "拍友圈ID不匹配");
            } else if (returnCode.equals(WSConstants.CODE_FRIEND_COMMENT_ID_NOT_EXIST)) {
                //1053
                showShortToast(mContext, "评论编号不存在");
            } else if (returnCode.equals(WSConstants.CODE_FRIEND_PARISE_ID_NOT_EXIST)) {
                //1054
                showShortToast(mContext, "点赞编号不存在");
            } else if (returnCode.equals(WSConstants.CODE_SPECIAL_ID_NOT_EXIST)) {
                //1055
                showShortToast(mContext, "专场编号不存在");
            } else if (returnCode.equals(WSConstants.CODE_SPECIAL_REMIND_TIME_OUT)) {
                //1056
                showShortToast(mContext, "专场预约时间已过");
            } else if (returnCode.equals(WSConstants.CODE_ALREADY_APPLY_PROMOTION)) {
                //1057
                showShortToast(mContext, "已申请推广商");
            } else if (returnCode.equals(WSConstants.CODE_ALREADY_REPLY_COMMENT)) {
                //1058
                showShortToast(mContext, "您已经回复过本条评论了");
            } else if (returnCode.equals(WSConstants.CODE_DEPOSIT_ORDER)) {
                //1059
                showShortToast(mContext, "订单未支付");
            } else if (returnCode.equals(WSConstants.CODE_DEPOSIT_MOST_COUNT)) {
                //1060
                showShortToast(mContext, "兑换失败：您本次兑换次数已用完");
            } else if (returnCode.equals(WSConstants.CODE_NEED_UPDATE)) {
                //1061
//                showShortToast(mContext, "获取当前APP版本过低，需要强制更新");
            } else if (returnCode.equals(WSConstants.CODE_TODAY_NOT_BIRTHDAY)) {
                //1062
//                showShortToast(mContext, "今天不是用户生日");
            } else if (returnCode.equals(WSConstants.CODE_TODAY_IS_BIRTHDAY)) {
                //1063
//                showShortToast(mContext, "该用户领取过生日奖励了");
            } else if (returnCode.equals(WSConstants.CODE_ORDER_DELETE_FAILED)) {
                showShortToast(mContext, "订单删除失败");
            } else if (returnCode.equals(WSConstants.CODE_TASK_REWARD_HAS_DONE)) {
                LogUtil.myD("returnCode:" + returnCode + ",exceptionReason:" + "任务活跃今天任务已做将不得积分");
            } else {
//				showShortToast(mContext, "returnCode:"+returnCode);
//                String exceptionReason = "数据异常。。。";
//                if (myBundle != null) {
//                    exceptionReason = myBundle.getString(BundleConstants.BUNDLE_EXCEPTION_REASON);
//                }
//                LogUtil.myD("returnCode:" + returnCode + ",exceptionReason:" + exceptionReason);
            }
        } else {
            LogUtil.myD("服务端返回码为null");
        }
    }


    /**
     * app是否已启动
     *
     * @param context
     * @return
     */
    public static boolean isAppInForeground3(Context context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<RunningAppProcessInfo> appProcesses = activityManager.getRunningAppProcesses();
        for (RunningAppProcessInfo appProcess : appProcesses) {
            if (appProcess.processName.equals(context.getPackageName())) {
                return appProcess.importance == RunningAppProcessInfo.IMPORTANCE_FOREGROUND;
            }
        }
        return false;
    }

    /**
     * 判断当前应用程序处于前台还是后台<br/>
     *
     * @return true:前台；false:后台。
     */
    public static boolean isAppInForeground(Context context) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<RunningTaskInfo> tasks = am.getRunningTasks(1);
        if (!tasks.isEmpty()) {
            ComponentName topActivity = tasks.get(0).topActivity;
            if (topActivity.getPackageName().equals(context.getPackageName())) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断activity是否在运行
     *
     * @param mContext
     * @return
     */
    public static boolean isActivityRunning(Context mContext, String activityClassName) {
        ActivityManager activityManager = (ActivityManager) mContext.getSystemService(Context.ACTIVITY_SERVICE);
        List<RunningTaskInfo> info = activityManager.getRunningTasks(1);
        if (info != null && info.size() > 0) {
            ComponentName component = info.get(0).topActivity;
            if (activityClassName.equals(component.getClassName())) {
                return true;
            }
        } else {
            LogUtil.myE(myTag + "RunningTaskInfo is null");
        }
        return false;
    }

    /**
     * 返回Uri方法的详细信息
     * <p>
     * 例如（自定义的协议）：
     * <a href="qipai://www.qipaiapp.com/weblink?Game/index.html?data=123">打开webb</a>
     * 输出：
     * uri:qipai://www.qipaiapp.com/weblink?Game/index.html?data=123
     * Authority:www.qipaiapp.com
     * Fragment:null
     * Host:www.qipaiapp.com
     * LastPathSegment:weblink
     * Path:/weblink
     * Port:-1
     * Query:Game/index.html?data=123
     * Scheme:qipai
     * SchemeSpecificPart://www.qipaiapp.com/weblink?Game/index.html?data=123
     * UserInfo:null
     *
     * @return
     */
    public static String getUriDetail(Uri uri) {
        String uriStr = "uri:" + uri;
        if (uri != null) {
            uriStr += "\n Authority:" + uri.getAuthority();
            uriStr += "\n Fragment:" + uri.getFragment();
            uriStr += "\n Host:" + uri.getHost();
            uriStr += "\n LastPathSegment:" + uri.getLastPathSegment();
            uriStr += "\n Path:" + uri.getPath();
            uriStr += "\n Port:" + uri.getPort();
            uriStr += "\n Query:" + uri.getQuery();
            uriStr += "\n Scheme:" + uri.getScheme();
            uriStr += "\n SchemeSpecificPart:" + uri.getSchemeSpecificPart();
            uriStr += "\n UserInfo:" + uri.getUserInfo();
        }
        return uriStr;
    }

    /**
     * 判断是否是锤子手机
     * 不考虑刷过机的
     *
     * @return
     */
    public static boolean isSmartisan() {
        boolean flag = false;
        if ("smartisan".equals(android.os.Build.MANUFACTURER)) {
            flag = true;
        }
        return flag;
    }

    /**
     * 获取设备信息
     *
     * @return
     */
    public static String getDeviceInfo() {
        String phoneInfo = "Product（手机制造商）: " + android.os.Build.PRODUCT;
        phoneInfo += ",\n BOARD（主板）: " + android.os.Build.BOARD;
        phoneInfo += ",\n BRAND（android系统定制商）: " + android.os.Build.BRAND;
        phoneInfo += ",\n BOOTLOADER（系统启动程序版本号：） " + android.os.Build.BOOTLOADER;
        phoneInfo += ",\n CPU_ABI（cpu指令集）: " + android.os.Build.CPU_ABI;
        phoneInfo += ",\n CPU_ABI2（cpu指令集2）: " + android.os.Build.CPU_ABI2;
        phoneInfo += ",\n DEVICE（设备参数）: " + android.os.Build.DEVICE;
        phoneInfo += ",\n DISPLAY（显示参数）: " + android.os.Build.DISPLAY;
//        phoneInfo += ",\n （无线电固件版本）: " + android.os.Build.getRadioVersion();
        phoneInfo += ",\n FINGERPRINT（硬件识别码 ）:" + android.os.Build.FINGERPRINT;
        phoneInfo += ",\n HARDWARE（硬件名称 ）: " + android.os.Build.HARDWARE;
        phoneInfo += ",\n ID（修订版本列表）: " + android.os.Build.ID;
        phoneInfo += ",\n MANUFACTURER（硬件制造商 ）: " + android.os.Build.MANUFACTURER;
        phoneInfo += ",\n MODEL（版本）: " + android.os.Build.MODEL;
        phoneInfo += ",\n SERIAL（硬件序列号）: " + android.os.Build.SERIAL;
        phoneInfo += ",\n TAGS（描述build的标签）: " + android.os.Build.TAGS;
        phoneInfo += ",\n VERSION_CODES.BASE: " + android.os.Build.VERSION_CODES.BASE;
        phoneInfo += ",\n SDK: " + android.os.Build.VERSION.SDK;
        phoneInfo += ",\n VERSION.RELEASE: " + android.os.Build.VERSION.RELEASE;
        phoneInfo += ",\n USER: " + android.os.Build.USER;
        LogUtil.myD(phoneInfo);
        return phoneInfo;
    }

    /**
     * 将EditText的光标定位到字符的最后面
     *
     * @param editText
     */
    public static void setEditTextCursorEnd(EditText editText) {
        CharSequence text = editText.getText();
        if (text instanceof Spannable) {
            Spannable spanText = (Spannable) text;
            Selection.setSelection(spanText, text.length());
        }
    }

    public static String getHighPriorityPackage(Context context) {
        Log.d("push", "PushManager.getHighPriorityPackage");
        Intent i = new Intent(SERVICE_ACTION);
        List<ResolveInfo> localList = context.getPackageManager().queryIntentServices(i, 0);
        String myPkgName = context.getPackageName();
        String pkgName = "";
        long pkgPriority = 0;
        for (ResolveInfo info : localList) {
            if (!info.serviceInfo.exported) {
                continue;
            }
            String pkg = info.serviceInfo.packageName;
            if (!info.serviceInfo.exported) {
                continue;
            }
            Context context1;
            try {
                context1 = context.createPackageContext(pkg, Context.CONTEXT_IGNORE_SECURITY);
            } catch (NameNotFoundException e) {
                continue;
            }
            String spName = new StringBuilder().append(pkg).append(".push_sync").toString();
            SharedPreferences sp = context1.getSharedPreferences(spName, Context.MODE_PRIVATE);
            long priority = sp.getLong("priority2", 0L);
            if (priority > pkgPriority || (myPkgName.equals(pkg) && priority >= pkgPriority)) {
                pkgPriority = priority;
                pkgName = pkg;
            }
            Log.d("push", "pkg--" + pkg + ", priority=" + priority);
        }
        return pkgName;
    }

    /**
     * 判断android版本是否在4.4或以上 4.4及其以上返回true
     *
     * @return
     */
    public static boolean androidVersion() {
        boolean versionFlag = false;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
            versionFlag = true;
        } else {
            versionFlag = false;
        }
        return versionFlag;
    }


    /**
     * 根据屏幕宽度，减去左、右内边距，减去外边距，按照设定宽高比例得到高度
     *
     * @param mContext     上下文
     * @param margin       外边距
     * @param paddingLeft  左内边距
     * @param paddingRight 右内边距
     * @param wScale       宽比例
     * @param hScale       高比例
     * @return 等比例高度
     */
    public static int getHeightByScale(Context mContext, int margin, int paddingLeft, int paddingRight, int wScale, int hScale) {
        int height = 0;
        margin = DensityUtil.dip2px(mContext, margin);
        paddingLeft = DensityUtil.dip2px(mContext, paddingLeft);
        paddingRight = DensityUtil.dip2px(mContext, paddingRight);
        int screenWidth = MyAndroidUtils.getScreenWidth(mContext);
        height = (screenWidth - margin - paddingLeft - paddingRight) * hScale / wScale;
        return height;
    }

    /**
     * 获取手机屏幕宽度(px)
     *
     * @param mContext
     * @return px
     */
    public static int getScreenWidth(Context mContext) {
        int width = 0;
        WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        //        width = display.getWidth();//已过时
        DisplayMetrics displayMetrics = new DisplayMetrics();
        display.getMetrics(displayMetrics);
        width = displayMetrics.widthPixels;
        return width;
    }

    /**
     * @return
     */
    public static int getScreenWidth() {
        int width = 0;
        WindowManager wm = (WindowManager) BaseApp.getContext().getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        //        width = display.getWidth();//已过时
        DisplayMetrics displayMetrics = new DisplayMetrics();
        display.getMetrics(displayMetrics);
        width = displayMetrics.widthPixels;
        return width;
    }

    /**
     * 获取手机屏幕高度
     *
     * @param mContext
     * @return
     */
    public static int getScreenHeight(Context mContext) {
        int height = 0;
        WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        height = display.getHeight();
        return height;
    }

    /**
     * 【金额】小数点后最多两位
     *
     * @param editText
     */
    public static void setPricePoint(final EditText editText) {
        editText.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().contains(".")) {
                    if (s.length() - 1 - s.toString().indexOf(".") > 2) {
                        s = s.toString().subSequence(0, s.toString().indexOf(".") + 3);
                        editText.setText(s);
                        editText.setSelection(s.length());
                    }
                }
                if (s.toString().trim().substring(0).equals(".")) {
                    s = "0" + s;
                    editText.setText(s);
                    editText.setSelection(2);
                }

                if (s.toString().startsWith("0") && s.toString().trim().length() > 1) {
                    if (!s.toString().substring(1, 2).equals(".")) {
                        editText.setText(s.subSequence(0, 1));
                        editText.setSelection(1);
                    }
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {


            }

        });

    }

    /**
     * 判断是否安装了某个应用
     *
     * @param context
     * @param packageName app包名
     * @return
     */
    public static boolean isApkInstalled(Context context, String packageName) {
        try {
            PackageManager packageManager = context.getPackageManager();
            ApplicationInfo applicationInfo = packageManager.getApplicationInfo(packageName, PackageManager.GET_UNINSTALLED_PACKAGES);
            return true;
        } catch (NameNotFoundException e) {
            return false;
        }
    }
}
