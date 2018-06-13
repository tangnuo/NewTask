package com.kedacom.utils;

import android.text.TextUtils;

/**
 * Created by Pan S.Q
 * on 2017/2/17.
 * 阿里百川文档地址
 * http://baichuan.taobao.com/doc2/detail.htm?articleId=102853&docType=1&treeId=38&qq-pf-to=pcqq.group
 */

public class AlimmdnUtil {

    //固定宽度为750px
    public static final int WIDTH = 750;

    /**
     * 判断图片路径是否源自阿里百川服务器
     *
     * @param imgUrl 图片路径
     * @return -1:表示非阿里百川服务器，否则源自阿里百川服务器
     */
    public static int findCharByKey(String imgUrl) {
        if (TextUtils.isEmpty(imgUrl)) {
            return -1;
        }
        int index = imgUrl.indexOf("alimmdn");
        return index;
    }

    /**
     * 将根据固定宽度比例缩放图片
     *
     * @param imgUrl 图片路径  1L：表示当目标尺寸大于源尺寸将不做缩放处理,当宽或者高度任意值比源尺寸大时将维持原尺寸
     * @return
     */
    public static String modifyImagePath(String imgUrl) {
        if (findCharByKey(imgUrl) > 0) {
//            LogUtil.myD("阿里百川1url:"+imgUrl + "@" + WIDTH + "w_1l");
            return imgUrl + "@" + WIDTH + "w_1l";
        }
//        LogUtil.myD("阿里百川1旧图片:"+imgUrl);
        return imgUrl;
    }

    /**
     * 自定义宽度计算图片大小
     *
     * @param imgUrl
     * @param width
     * @return
     */
    public static String modifyImagePath(String imgUrl, int width) {
        if (findCharByKey(imgUrl) > 0) {
//            LogUtil.myD("阿里百川1url:"+imgUrl + "@" + WIDTH + "w_1l");
            return imgUrl + "@" + width + "w_1l";
        }
//        LogUtil.myD("阿里百川1旧图片:"+imgUrl);
        return imgUrl;
    }

    /**
     * 对图片进行尺寸定义
     *
     * @param imgUrl 图片源
     * @param width  目标宽
     * @param height 目标高
     * @return
     */
    public static String modifyImagePath(String imgUrl, int width, int height) {

        //判断是否上传到阿里百川的服务器，否则直接加载图片
        if (findCharByKey(imgUrl) > 0) {
            String url = imgUrl + "@";
            if (width != 0) {
                url = url + width + "w_";
            }
            if (height != 0) {
                url = url + height + "h_";
            }
            LogUtil.myD("阿里百川3url:" + url);
            return url;
        }
//        LogUtil.myD("阿里百川3旧图片:"+imgUrl);
        return imgUrl;
    }

    /**
     * 对图片是否进行缩放
     *
     * @param imgUrl  图片源
     * @param width   目标宽
     * @param height  目标高
     * @param isScale true:缩放 false:不缩放
     * @return
     */
    public static String modifyImagePath(String imgUrl, int width, int height, boolean isScale) {
        if (findCharByKey(imgUrl) > 0) {
            String url = modifyImagePath(imgUrl, width, height);
            if (isScale) {
                return url + "1e_";
            }
            return url;
        }
        return imgUrl;
    }

    /**
     * 对图片是否裁剪
     *
     * @param imgUrl  图片源
     * @param width   目标宽
     * @param height  目标高
     * @param isScale true:缩放 false:不缩放
     * @param isCut   true:裁剪 false:不裁剪
     * @return
     */
    public static String modifyImagePath(String imgUrl, int width, int height, boolean isScale, boolean isCut) {
        if (findCharByKey(imgUrl) > 0) {
            String url = modifyImagePath(imgUrl, width, height, isScale);
            if (isCut) {
                return url + "1c_";
            }
            return url;
        }
        return imgUrl;
    }

    /**
     * 当图片处理的尺寸大于原图尺寸时是否处理
     *
     * @param imgUrl  图片源
     * @param width   目标宽
     * @param height  目标高
     * @param isScale true:缩放 false:不缩放
     * @param isCut   true:裁剪 false:不裁剪
     * @param isFix   true:处理 false:不处理
     * @return
     */
    public static String modifyImagePath(String imgUrl, int width, int height, boolean isScale, boolean isCut, boolean isFix) {
        if (findCharByKey(imgUrl) > 0) {
            String url = modifyImagePath(imgUrl, width, height, isScale, isCut);
            if (isFix) {
                return url + "1l";
            }
            return url;
        }
        return imgUrl;
    }
}
