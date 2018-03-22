package com.example.caowj.newtask.utils;

import android.annotation.SuppressLint;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by 大灯泡 on 2016/11/1.
 * <p>
 * 时间工具
 */

public class TimeUtil {
    private static long limitDateTime = 10000000000l;
    private static long milliseFormat = 1l;//毫秒转换单位；如果有毫秒，此处是1000.
    private static final long ONE_MINUTE = 60L * milliseFormat;
    private static final long ONE_HOUR = 3600L * milliseFormat;
    private static final long ONE_DAY = 86400L * milliseFormat;
    private static final long ONE_WEEK = 604800L * milliseFormat;
    private static final long ONE_MONTH = 2592000L * milliseFormat;
    private static final long ONE_YEAR = 315576000L * milliseFormat;


    /**
     * 获取时间差（毫秒级别的运算）
     *
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return 时间差字符串（xx小时，xx分钟，xx秒）
     */
    @SuppressLint("SimpleDateFormat")
    public static String dateDiff(String startTime, String endTime) {
        String result = "";
        long nd = 1000 * 24 * 60 * 60;// 一天的毫秒数
        long nh = 1000 * 60 * 60;// 一小时的毫秒数
        long nm = 1000 * 60;// 一分钟的毫秒数
        long ns = 1000;// 一秒钟的毫秒数
        long diff;
        long day = 0;
        long hour = 0;
        long min = 0;
        long sec = 0;
        if (startTime != null && endTime != null) {
            // 获得两个时间的毫秒时间差异
            diff = getTimeDifference(startTime, endTime);
            day = diff / nd;// 计算差多少天
            hour = diff % nd / nh;// 计算差多少小时
            min = diff % nd % nh / nm;// 计算差多少分钟
            sec = diff % nd % nh % nm / ns;// 计算差多少秒
            // 输出结果
            /*
             * System.out.println("时间相差：" + day + "天" + (hour - day * 24) + "小时"
			 * + (min - day * 24 * 60) + "分钟" + sec + "秒。");
			 */
            result = hour + "," + min + "," + sec;
        }

        return result;
    }

    /**
     * 格式化时间戳（毫秒级别的运算）
     *
     * @param diff 时间差
     * @return 时间差字符串（xx天，xx小时，xx分钟，xx秒）
     */
    public static String dateDiffD(long diff) {
        String result = "";
        long nd = 1000 * 24 * 60 * 60;// 一天的毫秒数
        long nh = 1000 * 60 * 60;// 一小时的毫秒数
        long nm = 1000 * 60;// 一分钟的毫秒数
        long ns = 1000;// 一秒钟的毫秒数
        long day = 0;
        long hour = 0;
        long min = 0;
        long sec = 0;
        day = diff / nd;// 计算差多少天
        hour = diff % nd / nh;// 计算差多少小时
        min = diff % nd % nh / nm;// 计算差多少分钟
        // hour = diff % nd / nh + day * 24;// 计算差多少小时
        // min = diff % nd % nh / nm + day * 24 * 60;// 计算差多少分钟
        sec = diff % nd % nh % nm / ns;// 计算差多少秒
        // 输出结果
        /*
         * System.out.println("时间相差：" + day + "天" + (hour - day * 24) + "小时" +
		 * (min - day * 24 * 60) + "分钟" + sec + "秒。");
		 */
        result = day + "," + hour + "," + min + "," + sec;

        return result;
    }


    /**
     * 获取两个时间的差值
     *
     * @param startTime
     * @param endTime
     * @return
     */
    public static long getTimeDifference(String startTime, String endTime) {
        long diff = 0;
        // 按照传入的格式生成一个simpledateformate对象
        SimpleDateFormat sd = getDateFormat("yyyy-MM-dd HH:mm:ss");
        if (startTime != null && endTime != null) {
            try {
                diff = sd.parse(endTime).getTime() - sd.parse(startTime).getTime();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return diff;
    }


    /**
     * 格式化时间戳（秒级别的运算）
     *
     * @param diff 时间差
     * @return 时间差字符串（xx天，xx小时，xx分钟，xx秒）
     */
    public static String dateDiffDToSecond(long diff) {
//        String result = "";
//        long nd = 24 * 60 * 60;// 一天的秒数
//        long nh = 60 * 60;// 一小时的秒数
//        long nm = 60;// 一分钟的秒数
//        long day = 0;
//        long hour = 0;
//        long min = 0;
//        long sec = 0;
//        day = diff / nd;// 计算差多少天
//        hour = diff % nd / nh;// 计算差多少小时
//        min = diff % nd % nh / nm;// 计算差多少分钟
//        // hour = diff % nd / nh + day * 24;// 计算差多少小时
//        // min = diff % nd % nh / nm + day * 24 * 60;// 计算差多少分钟
//        sec = diff % nd % nh % nm;// 计算差多少秒
//        // 输出结果
//		/*
//		 * System.out.println("时间相差：" + day + "天" + (hour - day * 24) + "小时" +
//		 * (min - day * 24 * 60) + "分钟" + sec + "秒。");
//		 */
//        result = day + "," + hour + "," + min + "," + sec;

        return dateDiffDToSecond(diff, ",");
    }

    /**
     * 将毫秒时间格式化（包含天）
     *
     * @param diff
     * @param splitFlag 分隔符
     * @return x天x小时x分钟x秒
     */
    public static String dateDiffDToSecond(long diff, String splitFlag) {
        String result = "";
        long nd = 24 * 60 * 60;// 一天的秒数
        long nh = 60 * 60;// 一小时的秒数
        long nm = 60;// 一分钟的秒数
        long day = 0;
        long hour = 0;
        long min = 0;
        long sec = 0;
        day = diff / nd;// 计算差多少天
        hour = diff % nd / nh;// 计算差多少小时
        min = diff % nd % nh / nm;// 计算差多少分钟
        // hour = diff % nd / nh + day * 24;// 计算差多少小时
        // min = diff % nd % nh / nm + day * 24 * 60;// 计算差多少分钟
        sec = diff % nd % nh % nm;// 计算差多少秒
        // 输出结果
        /*
         * System.out.println("时间相差：" + day + "天" + (hour - day * 24) + "小时" +
		 * (min - day * 24 * 60) + "分钟" + sec + "秒。");
		 */
//        result = day + splitFlag + hour + splitFlag + min + splitFlag + sec;
        result = day + splitFlag + formatChar(hour) + splitFlag + formatChar(min) + splitFlag + formatChar(sec);//TODO 2.5.1时间显示需要2位数显示

        return result;
    }

    /**
     * 不包含天
     *
     * @param diff      秒
     * @param splitFlag
     * @return
     */
    public static String dateDiffDToSecond2(long diff, String splitFlag) {
        String result = "";
        long nd = 24 * 60 * 60;// 一天的秒数
        long nh = 60 * 60;// 一小时的秒数
        long nm = 60;// 一分钟的秒数
        long hour = 0;
        long min = 0;
        long sec = 0;
        hour = diff % nd / nh;// 计算差多少小时
        min = diff % nd % nh / nm;// 计算差多少分钟
        // hour = diff % nd / nh + day * 24;// 计算差多少小时
        // min = diff % nd % nh / nm + day * 24 * 60;// 计算差多少分钟
        sec = diff % nd % nh % nm;// 计算差多少秒
        // 输出结果
        /*
         * System.out.println("时间相差：" + day + "天" + (hour - day * 24) + "小时" +
		 * (min - day * 24 * 60) + "分钟" + sec + "秒。");
		 */
//        result = day + splitFlag + hour + splitFlag + min + splitFlag + sec;
        result = formatChar(hour) + splitFlag + formatChar(min) + splitFlag + formatChar(sec);//TODO 2.5.1时间显示需要2位数显示

        return result;
    }

    /**
     * 格式化音频时长
     *
     * @param diff      毫秒
     * @param splitFlag
     * @return
     */
    public static String formatAudioLength(long diff, String splitFlag) {
        diff = diff / 1000;
        String result = "00:00";
        long nd = 24 * 60 * 60;// 一天的秒数
        long nh = 60 * 60;// 一小时的秒数
        long nm = 60;// 一分钟的秒数
        long min = 0;
        long sec = 0;
        min = diff % nd % nh / nm;// 计算差多少分钟
        sec = diff % nd % nh % nm;// 计算差多少秒
        result = formatChar(min) + splitFlag + formatChar(sec);
//        LogUtil.myD("diff:" + diff + ",min:" + min + ",sec:" + sec);

        return result;
    }

    /**
     * 时间显示2位不足补0
     *
     * @param num
     * @return
     */
    public static String formatChar(long num) {
        return String.valueOf(num).length() == 1 ? "0" + String.valueOf(num) : String.valueOf(num);
    }

    /**
     * 将时间转换成时间戳
     *
     * @param dateTime
     * @return long（单位：秒）
     */
    public static long getTimestamp(String dateTime) {
        long date = 0;
        if (dateTime != null && !dateTime.equals("null") && !dateTime.equals("0")) {
            // 先格式化时间
            if (dateTime.startsWith("/Date")) {
                dateTime = dateTime.substring(6, 19);
            } else if (dateTime.contains("T")) {
                int length = dateTime.length();
                dateTime = dateTime.replace("T", " ");
                if (length > 19) {
                    dateTime = dateTime.substring(0, 19);
                }
            }
            SimpleDateFormat format = getDateFormat("yyyy-MM-dd HH:mm:ss");

            try {
                //getTime()取得的是毫秒级别的时间，此处转换成秒
                date = format.parse(dateTime).getTime() / 1000;
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return date;
    }

    /**
     * 防止手机设置的不是东8区，导致时间转换出现问题。
     *
     * @return
     */
    public static SimpleDateFormat getDateFormat(String pattern) {
        SimpleDateFormat format = new SimpleDateFormat(pattern, Locale.getDefault());
//        LogUtil.myD("当前Local信息："+Locale.getDefault().toString());
//        //防止手机设置的不是东8区，导致时间转换出现问题。
//        format.setTimeZone(TimeZone.getTimeZone("GMT+08"));
        return format;
    }

    /**
     * 将时间戳转换为字符串
     *
     * @param dateTime 毫秒级时间戳
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static String Timestamp2Str(long dateTime) {
        String str = "";
        str = Timestamp2Str(dateTime, "yyyy-MM-dd HH:mm:ss");
        return str;
    }

    /**
     * 将时间戳转换为字符串不含秒数
     *
     * @param dateTime 毫秒级时间戳
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static String Timestamp2String(long dateTime) {
        return Timestamp2Str(dateTime, "yyyy-MM-dd HH:mm");
    }

    /**
     * 将时间戳转换成字符串，自定义时间格式
     *
     * @param dateTime
     * @param format
     * @return
     */
    public static String Timestamp2Str(long dateTime, String format) {
        String str = "";
        if (dateTime > 0) {
            //由于时间戳的精确度不定，秒级别的长度10位；毫秒级别的13位
            if (dateTime < limitDateTime) {
                dateTime = dateTime * 1000;
            }
            SimpleDateFormat dfTime = getDateFormat(format);
            str = dfTime.format(new Date(dateTime));
        }
        return str;
    }

    /**
     * 获取今日日期
     *
     * @return 2015-10-15
     */
    public static String getToday() {
        Date dt = new Date();
        SimpleDateFormat format = getDateFormat("yyyy-MM-dd");
        String date = format.format(dt) + " ";
        return date;
    }

    /**
     * 将毫秒时间格式化
     *
     * @param dateTime
     * @return MM-dd
     */
    @SuppressLint("SimpleDateFormat")
    public static String getFormatDate(long dateTime, String format) {
        String date = "";
        if (dateTime > 0) {
            if (dateTime < limitDateTime) {
                dateTime = dateTime * 1000;
            }
            date = new java.text.SimpleDateFormat(format).format(new java.util.Date(dateTime));
        }

        return date;
    }


    /**
     * 是否属于今日
     *
     * @param dateTime 时间差（和当前时间比较的时间差）
     * @return
     */
    public static boolean isToday(long dateTime) {
        long startL = 24 * 60 * 60;
//        LogUtil.myD("时间差：" + dateTime + "," + startL);
        if (dateTime > 0 && dateTime > startL) {
            return false;
        } else {
            return true;
        }
    }
}
