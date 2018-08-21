package com.kedacom.module_common.utils;

import android.annotation.SuppressLint;
import android.text.TextUtils;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

@SuppressLint("SimpleDateFormat")
public class StringTool {

    private static long lastClickTime;

    /**
     * 防止缓存的webView的url
     * 修改的css和其他样式会有缓存，加自定义后缀可以新加载
     *
     * @param webUrl
     * @return
     */
    public static String getNoCacheWebUrl(String webUrl) {
        if (!TextUtils.isEmpty(webUrl)) {
            if (isAddSurfix(webUrl)) {
                Date date = new Date();
                String cacheFlag1 = "&qipaicache=" + date.getTime();
                String cacheFlag2 = "?qipaicache=" + date.getTime();
                String anchorFlag = "#";
//                是否包含锚点
                if (webUrl.contains(anchorFlag)) {
                    int anchorIndex = webUrl.indexOf(anchorFlag);
                    String url1 = webUrl.substring(0, anchorIndex);
                    String url2 = webUrl.substring(anchorIndex, webUrl.length());
                    LogUtil.myD("url1:" + url1 + "-----url2:" + url2);
                    if (url1.contains("?")) {
                        webUrl = url1 + cacheFlag1 + url2;
                    } else {
                        webUrl = url1 + cacheFlag2 + url2;
                    }
                } else {
                    if (webUrl.contains("?")) {
                        webUrl = webUrl + cacheFlag1;
                    } else {
                        webUrl = webUrl + cacheFlag2;
                    }
                }

                LogUtil.myD("防止缓存的webUrl:" + webUrl);
            } else {
                //				LogUtil.myD("支付宝链接，不能加自定义参数。："+webUrl);
            }
        }

        return webUrl;
    }

    /**
     * 是否添加刷新后缀
     * 1、内部自定义支付页面（微信，支付宝，回调页面）不能加自定义参数
     * 2、外部页面不能加
     *
     * @param webUrl
     * @return
     */
    private static boolean isAddSurfix(String webUrl) {
        boolean flag = false;
        if (webUrl.contains("qipaiapp.com")) {
            if (webUrl.contains("/alipay/") || webUrl.contains("/wechatpay/")) {
                flag = false;
            } else {
                //启拍的非支付页面
                flag = true;
            }
        } else {
            //非启拍页面
            flag = false;
        }
        return flag;
    }

    /**
     * 通过value值获取在数组中的下标
     *
     * @param array
     * @param value
     * @return
     */
    public static int getIndexByValue(String[] array, String value) {
        int index = 0;
        if (array != null && !TextUtils.isEmpty(value)) {
            for (int i = 0; i < array.length; i++) {
                if (array[i].equals(value)) {
                    index = i;
                    break;
                }
            }
        }

        return index;
    }

    /**
     * 通过value值获取在Map集合中的下标
     *
     * @param map
     * @param value
     * @return
     */
    public static int getIndexByValue(Map<Integer, String> map, String value) {
        int index = 0;
        if (map != null && !TextUtils.isEmpty(value)) {
            Set set = map.entrySet();
            Iterator i = set.iterator();
            while (i.hasNext()) {
                Map.Entry<Integer, String> entry1 = (Map.Entry<Integer, String>) i.next();
                if (value.equals(entry1.getValue())) {
                    index = entry1.getKey();
                    break;
                }
            }
        }

        return index;
    }


    /**
     * 通过大图路径获取缩略图路径
     *
     * @param bigImageUrl
     * @return
     */
//    public static String getThumbnailUrl(String bigImageUrl) {
//        String thumbnailUrl = "";
//        if (!TextUtils.isEmpty(bigImageUrl)) {
//            int end = bigImageUrl.lastIndexOf(".");
//            thumbnailUrl = bigImageUrl.substring(0, end) + Constants.SYSTEM_IMAGE_THUMBNAIL_SUFFIX + bigImageUrl.substring(end);
//        } else {
//            LogUtil.myD("bigImageUrl is null");
//        }
////		LogUtil.myD( "thumbnailUrl is "+thumbnailUrl);
//        return thumbnailUrl;
//    }

    /**
     * 如果double类型小数位是0，就不显示小数位；反之
     *
     * @param d
     * @return
     */
    public static String doubleTrans(double d) {
        DecimalFormat fnum = new DecimalFormat("##0.00");
        if (Math.round(d) - d == 0) {
            fnum = new DecimalFormat("##0");
        }
        return fnum.format(d);
    }

    /**
     * 格式化价格，不显示小数位<br/>
     * 使用Math类的方法。http://blog.csdn.net/lixiang0522/article/details/7557851
     *
     * @param price
     * @return
     */
    public static String formatPrice(String price) {
        if (price != null) {
            if (price.contains(".")) {
                int index = price.indexOf(".");
                price = price.substring(0, index);
            } else {
                LogUtil.myD("价格已经是整形");
            }
        } else {
            LogUtil.myD("价格为空");
        }
        return price;
    }

    /**
     * 保留两位小数(float只能表示7位有效数字,包括小数点后位数)
     *
     * @param str
     */
    public static float KeepTwo(String str) {
        str = "";
        LogUtil.d("StringTool", "Json字段中金额:" + str);
        float result = Float.parseFloat(str);
        float result2 = Float.valueOf(str);
//        double result = Double.parseDouble(str);
        LogUtil.d("StringTool", "转化成double:" + result + ",,," + result2);
        result = KeepTwoF(result);
//        result = keepTwoD(result);
        LogUtil.d("StringTool", "保留两位:" + result);
        float d = (float) result;
        LogUtil.d("StringTool", "强转 double-->float:" + d);
        return d;
    }

    /*
     * public static String KeepTwoS(float str){ DecimalFormat fnum = new
     * DecimalFormat("##0.00"); String dd = fnum.format(str); return dd; }
     */

    /**
     * 用于金额展示两位小数
     *
     * @param number
     * @return
     */
    public static String keepTwoDecimalPlaces(String number) {
        if (TextUtils.isEmpty(number)) {
            return "";
        }
        int index = number.indexOf(".");
        if (index > -1) {
            if (number.substring(index + 1).length() == 1) {
                number += "0";
            }
        } else {
            number += ".00";
        }
        return number;
    }

    /**
     * 取消银子小数位，为整数显示
     *
     * @param silver
     * @return
     */
    public static String cancelSilverDecimalPoint(String silver) {
        int index = silver.indexOf(".");
        LogUtil.d("StringTool", "index:" + index);
        if (index > -1) {
            return silver.substring(0, index);
        }
        return silver;
    }

    /**
     * 保留两位小数
     *
     * @param str
     * @return
     */
    public static float KeepTwoF(float str) {
        str = (float) (Math.round(str * 100)) / 100;
        return str;
    }

    /**
     * 保留两位小数双精度
     *
     * @param str
     * @return
     */
    public static double KeepTwoD(double str) {
        str = (double) (Math.round(str * 100)) / 100;
        return str;
    }

    /**
     * 是否为快速点击（1秒内点击多次）
     *
     * @return
     */
    public synchronized static boolean isFastClick() {
        long time = System.currentTimeMillis();
        if (time - lastClickTime < 1000) {
            return true;
        }
        lastClickTime = time;
        return false;
    }


    /**
     * 判断两个出价记录是否相等（出价内容、顺序必须一致）
     *
     * @param list1
     * @param list2
     * @return
     */
//    public static boolean isListEquals(List<AuctionRecord> list1, List<AuctionRecord> list2) {
//        if (list1 != null && list2 != null) {
//            if (list1.size() == list2.size()) {
//                for (int i = 0; i < list1.size(); i++) {
//                    if (list1.get(i).getId() != list2.get(i).getId()) {
//                        return false;
//                    }
//                }
//                return true;
//            } else {
//                return false;
//            }
//        } else {
//            return false;
//        }
//    }

    /**
     * 字符串超过指定长度显示...
     *
     * @param str
     * @param limitLength 限制的长度
     * @return
     */
    public static String sub(String str, int limitLength) {
        String newStr = "";
        if (str != null) {
            if (str.length() > limitLength) {
                newStr = str.substring(0, limitLength) + "...";
            } else {
                newStr = str;
            }
        }
        return newStr;
    }

    /**
     * 数组转换成json字符串 如：[abc,123,45]
     *
     * @param array
     * @return
     */
    public static String arrToStr(String[] array) {
        String str = "";
        int length = array.length;
        StringBuffer sb = new StringBuffer();
        if (array != null && length != 0) {
            for (int i = 0; i < array.length; i++) {
                if (!TextUtils.isEmpty(array[i])) {
                    sb.append(array[i] + ",");
                }
            }
        }
        str = sb.toString();
        str = str.substring(0, str.length() - 1);
        /*
         * if(str.contains(",")){ str = "["+ str.substring(0,
         * str.length()-1)+"]"; }
         */

        return str;
    }

    /**
     * 消除字符串为null的情况
     *
     * @param arg
     * @return
     */
    public static String avoidNull(String arg) {
        if (arg == null || arg.equals("null")) {
            arg = "";
        }
        return arg;
    }

    /**
     * 将Unicode编码字符串，转换成相应的汉字字符串
     *
     * @param utfString Unicode编码字符串
     * @return 汉字字符串
     */
    public static String convert(String utfString) {
        if (!utfString.contains("\\u")) {
            return utfString;
        }
        StringBuilder sb = new StringBuilder();
        int i = -1;
        int pos = 0;

        while ((i = utfString.indexOf("\\u", pos)) != -1) {
            sb.append(utfString.substring(pos, i));
            if (i + 5 < utfString.length()) {
                pos = i + 6;
                sb.append((char) Integer.parseInt(utfString.substring(i + 2, i + 6), 16));
            }
        }

        return sb.toString();
    }

    /**
     * 获取文件名称
     *
     * @param value
     * @return
     */
    static public String getFileName(String value) {
        int slashpos = value.lastIndexOf("/");
        int lastpos = value.length() - 1;
        if (slashpos < 0) {
            slashpos = 0;
        } else {
            slashpos += 1;
        }

        String name = value.substring(slashpos, lastpos + 1);

        return name;
    }

    /**
     * 验证手机格式
     */
    public static boolean isMobileNO(String mobiles) {
        /*
         * 移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188
         * 联通：130、131、132、152、155、156、185、186 电信：133、153、180、189、（1349卫通）
         * 总结起来就是第一位必定为1，第二位必定为3或5或8，其他位置的可以为0-9
         */
        String telRegex = "[1][34578]\\d{9}";// "[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        if (TextUtils.isEmpty(mobiles)) {
            return false;
        } else {
            return mobiles.matches(telRegex);
        }
    }

    /**
     * 验证6位数字的支付密码
     */
    public static boolean isPayCode(String mobiles) {
        /*
         * 移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188
         * 联通：130、131、132、152、155、156、185、186 电信：133、153、180、189、（1349卫通）
         * 总结起来就是第一位必定为1，第二位必定为3或5或8，其他位置的可以为0-9
         */
        String telRegex = "\\d{6}";// "[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        if (TextUtils.isEmpty(mobiles)) {
            return false;
        } else {
            return mobiles.matches(telRegex);
        }
    }

    /**
     * 验证输入的身份证号格式是否符合 长度为18位
     *
     * @param id
     * @return 是否合法
     */
    public static boolean isID(String id) {
        if (TextUtils.isEmpty(id)) {
            return false;
        } else {
            boolean result = false;
            if (id.length() == 18) {
                result = true;
            }
            return result;
        }
    }

    /**
     * 验证输入的邮箱格式是否符合
     *
     * @param email
     * @return 是否合法
     */
    public static boolean isEmail(String email) {
        String emailPattern = "[a-zA-Z0-9][a-zA-Z0-9._-]{2,16}[a-zA-Z0-9]@[a-zA-Z0-9]+.[a-zA-Z0-9]+";
        if (TextUtils.isEmpty(email)) {
            return false;
        } else {
            boolean result = Pattern.matches(emailPattern, email);
            return result;
        }
    }

    /**
     * 验证输入的邮编格式是否符合
     *
     * @param postcode
     * @return 是否符合
     */
    public static boolean isPostcode(String postcode) {
        String emailPattern = "[0-9]{6}";
        if (TextUtils.isEmpty(postcode)) {
            return false;
        } else {
            boolean result = Pattern.matches(emailPattern, postcode);
            return result;
        }
    }

    /**
     * 验证密码的格式 1、长度是否为6-30位 2、只能是数字与字母 3、区分大小写
     *
     * @param password
     * @return 是否符合
     */
    public static boolean validatePwd(String password) {
        String telRegex = "[A-Za-z0-9]{6,30}";
        if (TextUtils.isEmpty(password)) {
            return false;
        } else {
            return password.matches(telRegex);
        }
    }
}
