package com.example.caowj.newtask.mvp.constants;

import android.os.Environment;

import java.io.File;

/**
 * 常量
 *
 * @author caowj
 */
public class Constants {
    /**
     * 本地缓存目录
     */
    public static final String CACHE_DIR = "qipai";
    /***
     * 轮播图广播标记-轮播图
     */
    public static final String IMG_RECEIVER_FLAG_CAROUSEL = "IMG_RECEIVER_FLAG_CAROUSEL";
    /***
     * 详情图广播标记-详情
     */
    public static final String IMG_RECEIVER_FLAG_DETAIL = "IMG_RECEIVER_FLAG_DETAIL";
    /***
     * 详情图广播标记-拍友圈
     */
    public static final String IMG_RECEIVER_FLAG_FRIEND = "IMG_RECEIVER_FLAG_FRIEND";
    /***
     * 详情图广播标记-拍友圈
     */
    public static final String IMG_RECEIVER_FLAG_FRIEND_DETAIL = "IMG_RECEIVER_FLAG_FRIEND_DETAIL";
    /***
     * 自定义文本分割符
     */
    public static final String CUSTOM_TEXT_SEPARATOR = "，";
    /**
     * 美物内容分隔符
     */
//    public static final String GOOD_THINGS_CONTENT_SEPARATOR = "<*-qipai-*>";
    public static final String GOOD_THINGS_CONTENT_SEPARATOR = "qipai_meiwu";
    /**
     * 自定义地址分隔符
     */
    public static final String CUSTOM_ADDRESS_SEPARATOR = " ";
    /**
     * 宝贝种类，一次性允许操作最大数量
     */
    public static final int MAX_TYPE_COUNT = 3;
    public static final int max = 0;
    /**
     * 客服QQ号
     */
    public static final String CUSTOMER_SERVICE_QQ = "2584085150";
    /**
     * app文件夹路径：[/storage/emulated/0/qipai/]
     */
    public static final String APP_FILE_URL = Environment.getExternalStorageDirectory() + File.separator + Constants.CACHE_DIR + File.separator;
    /**
     * app用户图像路径：[/storage/emulated/0/qipai/userPic/]
     */
    public static final String APP_USER_PIC_URL = APP_FILE_URL + "userPic" + File.separator;
    /**
     * app拍品图像缓存路径1：[/storage/emulated/0/qipai/paipinImg/]
     */
    public static final String APP_PAIPIN_IMG_URL = APP_FILE_URL + "paipinImg" + File.separator;
    /**
     * app拍品图像缓存路径1：[/storage/emulated/0/qipai/qipaiImg/]
     */
    public static final String APP_SHOW_IMG_URL = APP_FILE_URL + "qipaiImg" + File.separator;
    /**
     * app拍品图像缓存路径2：[/storage/emulated/0/qipai/ImageLoader]
     */
    public static final String APP_PAIPIN_IMAGELOADER_URL = APP_FILE_URL + "ImageLoader";
    /**
     * app拍品图像缓存路径1：[/storage/emulated/0/qipai/apatch/]
     */
    public static final String APP_PATCH_URL = APP_FILE_URL + "apatch" + File.separator;
    /**
     * 自定义图片后缀
     */
    public static final String CUSTOMIZE_IMAGE_SUFFIX = ".cwj";
    /**
     * 服务端缩略图后缀
     */
    public static final String SYSTEM_IMAGE_THUMBNAIL_SUFFIX = "_thumbnail";
    /**
     * 百度推送通知标志
     */
    public static final String NOTIFICATION_TAG = "NOTIFICATION_TAG";
    /**
     * 百度推送Type=101，今日拍品
     */
    public static final String NOTIFICATION_TYPE_PAIPIN_TODAY = "101";
    /**
     * 百度推送Type=102，宝贝预展
     */
    public static final String NOTIFICATION_TYPE_PAIPIN_TOMORROW = "102";
    /**
     * 百度推送Type=103，一口价
     */
    public static final String NOTIFICATION_TYPE_PAIPIN_FIXEDPRICE = "103";
    /**
     * 外链跳转专场
     */
    public static final String NOTIFICATION_TYPE_PAIPIN_SPECIAL = "105";
    /**
     * 团购跳转商品支付页面Type=303
     */
    public static final String NOTIFICATION_TYPE_PAIPIN_BUY = "303";
    /**
     * 跳转到拍友圈
     */
    public static final String NOTIFICATION_TYPE_FRIEND = "304";

    /**
     * 任务活跃
     */
    public static final String NOTIFICATION_TYPE_TASK_REWARD = "401";
    /**
     * 百度推送Type=201，待支付订单
     */
    public static final String NOTIFICATION_TYPE_ORDER_PAY = "201";
    /**
     * 评论回复标志
     */
    public static final String COMMENT_REPLY_FLAG = "启拍回复：";
    /**
     * 判断是否为发布版
     */
    public static final boolean RELEASE = true;
    /**********************************************全部变量**********************************************/
    /**
     * 操作栏单条Item高度
     */
    public static final int itemHeight = 55;
    /**
     * 朋友圈内容最大长度限制
     */
    public static final int FRIEND_CONTENT_MAX_LENGTH = 200;
    /**
     * 拍友圈列表最大显示条数
     */
    public final static int FRIEND_MAX_SHOW_COUNT = 5;
    /**
     * 回复
     */
    public final static String FRIEND_REPLY_FLAG = " 回复 ";
    /** 判断预加载的今日的数据是否使用过*/
//	public static boolean ISUSED = false;
    /**
     * 冒号
     */
    public final static String FRIEND_COLON_FLAG = "：";
    /***
     * 每页显示的条数（最好是偶数，因为一口价每行显示2列）
     */
    public static int PAGESIZE = 10;
    /**
     * 未读站内信数量
     */
    public static int UNREAD_SYSNEWS = 0;
    /**
     * 是否显示宝贝预展通知（小喇叭）
     */
//    public static boolean SHOW_HORN = true;
    /**
     * 外部链接传入的type
     * 101	今日拍品
     * 102	宝贝预展
     * 103	一口价
     */
    public static String outLink_type = "";
    /**
     * 外部链接传入的拍品ID
     */
    public static String outLink_goods = "";
    /**
     * 团购外链传入的商品ID
     */
    public static String outLink_goodsId = "";
    /**
     * 团购外链传入的商品属性
     */
    public static String outLink_goodsAttr = "";
    /**
     * 进入拍友圈详情
     */
    public static String outLink_friendId = "";
    /**
     * 专场id
     */
    public static String outLink_specialId = "";
    /**
     * MainActivity adLinkOpen()使用。广告区域 2：今日；3宝贝预展；4一口价
     **/
    public static String TYPE_ID = "TYPE_ID";
    /**
     * 外部链接传入的分类ID（指向南红、翡翠等具体分类）
     */
    public static String outLink_cateid = "";
    /**
     * Logcat调试标志
     */
    public static String LogTag = "caowj";
    /**
     * 人民币标志
     */
    public static String RMBTag = " ¥ ";
    /**
     * 第一次打开宝贝预展
     */
    public static boolean FIRST_OPEN_TOMORROW = true;
    /**
     * 第一次打开一口价
     */
    public static boolean FIRST_OPEN_FIXEDPRICE = true;
    /**针对于分享专场数据使用*/
    /*public static final int SPECIAL_NUMBER=123456;
	*//**掌柜推荐的解析数据标示*//*
	public static final int ISRECOMMEND=1;
	*//**非掌柜推荐的解析数据标示*//*
	public static final int ISOTHER=0;*/
    /**
     * 分类标记 -今日
     */
    public static String TYPE_FLAG_TODAY = "3";
    /**
     * 分类标记 -宝贝预展
     */
    public static String TYPE_FLAG_TOMORROW = "2";
    /**
     * 分类标记 -一口价
     */
    public static String TYPE_FLAG_FIXEDPRICE = "4";
    /**
     * 拍友圈单张图片的高度限制(px)
     */
    public static int FRIEND_SINGLE_IMAGE_HEIGHT = 400;
    /**
     * 图片缩小的最小比例 scaleP
     */
    public static float GALLEY_SCALE_RATE = 0.8f;
    /**
     * 一口价综合排序标志 true:则回滚到顶部 false:不会滚保持当前状态
     */
    public static boolean isIntegratedSorting = false;
    /**
     * 一口价筛选标志 true:则回滚到顶部 false:不会滚保持当前状态
     */
    public static boolean isFilter = false;
    /**
     * 用于微信分享商品或二维码时调用
     */
    public static int shareType = -1;
    /**
     * 用于微信分享商品时调用
     */
    public static int productId = 0;
    public static String ImageJumpUrl = "";

}
