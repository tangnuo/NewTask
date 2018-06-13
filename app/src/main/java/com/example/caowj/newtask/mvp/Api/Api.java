package com.example.caowj.newtask.mvp.Api;

/**
 * by y on 2016/4/28.
 */
public class Api {

//  http://test.qipaiapp.com//QiPaiAPI/PaipinInfo.asmx?op=GetAdList
//  http://test.qipaiapp.com/QiPaiAPI/PaipinInfo.asmx/GetAdList?token=12

    /////////////////////启拍////////////////////////////
    public static final String BASE_API_QIPAI = "http://test.qipaiapp.com/QiPaiAPI_2_6_6/";

    public static final String PAIPININFO_URL = "PaipinInfo.asmx/";
    public static final String PAIPINCATE_URL = "PaipinCate.asmx/";
    public static final String SYSNEWS_URL = "SysNews.asmx/";
    public static final String SYSPIC_URL = "Syspic.asmx/";


    /////////////////////天行数据////////////////////////////

    public static final String BASE_API_TX = "http://api.tianapi.com/";


    ///////////////////////////////////////////////////////////////

    //天狗api
    public static final String BASE_API_TNGOU = "http://www.tngou.net/";

    //图片URL前缀
    public static final String IMAGER_URL = "http://tnfs.tngou.net/image";

    //图片分类
    public static final String TAB_NAME = "tnfs/api/classify";

    //图片列表
    public static final String IMAGE_LIST = "tnfs/api/list";

    //最新图片
    public static final String IMAGE_NEW = "tnfs/api/news";

    //图片详情
    public static final String IMAGE_SHOW = "tnfs/api/show";

    //新闻分类
    public static final String TAB_NEWS = "api/top/classify";

    //新闻列表
    public static final String NEWS_LIST = "api/top/list";

    //新闻详情
    public static final String NEWS_SHOW = "api/top/show";

}
