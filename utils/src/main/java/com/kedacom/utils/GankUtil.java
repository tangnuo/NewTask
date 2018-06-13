package com.kedacom.utils;

/**
 * <p>
 * 干货集中营API
 * </p>
 * http://gank.io/api
 *
 * @Author : caowj
 * @Date : 2018/4/19
 */

public class GankUtil {
    /**
     * 获取干货集中营的数据（仅支持Get请求）
     * <p>
     * 例如：http://gank.io/api/data/福利/10/1
     * </p>
     *
     * @param type      1：福利 | 2：Android | 3：iOS | 4：休息视频 | 5：拓展资源 | 6：前端 | 0：all
     * @param pageSize  每页的数量（大于0）
     * @param pageIndex 第几页（大于0）
     * @return
     */
    public static String getUrl(int type, int pageSize, int pageIndex) {
        StringBuffer baseUrl = new StringBuffer("http://gank.io/api/data/");

        //注意type类型的大小写，否则访问失败。
        switch (type) {
            case 0:
                baseUrl.append("all/");
                break;
            case 1:
                baseUrl.append("福利/");
                break;
            case 2:
                baseUrl.append("Android/");
                break;
            case 3:
                baseUrl.append("iOS/");
                break;
            case 4:
                baseUrl.append("休息视频/");
                break;
            case 5:
                baseUrl.append("拓展资源/");
                break;
            case 6:
                baseUrl.append("前端/");
                break;
            default:
                baseUrl.append("all/");
                break;

        }
        baseUrl.append(pageSize + "/");
        baseUrl.append(pageIndex);
        String url = baseUrl.toString();
        LogUtil.myD("干货Url：" + url);
        return url;

    }
}
