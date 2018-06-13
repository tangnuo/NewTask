package com.example.caowj.newtask.module1.constants;


import com.kedacom.utils.LogUtil;

/**
 * webservice常量
 *
 * @author caowj
 */
public class WSConstants {

    /**
     * 天行API的key
     */
    public static final String TIAN_XING_API_KEY = "145170256f0d5a708fef46f45977122b";
    /***
     * 获取WebService命名空间
     **/
    public static final String NAME_SPACE = "http://tempuri.org/";
    /***
     * 服务端域名
     **/
    public static final String WEB_SERVER_DOMAIN_NAME = LogUtil.DEBUG_SERVER ? "http://test.qipaiapp.com/" : "http://admin.qipaiapp.com/";

    /**
     * 服务端接口(每个都是不同的版本)
     */
    public static final String WEB_SERVER_API = WEB_SERVER_DOMAIN_NAME + "QiPaiAPI_2_6_3/";

    /**
     * socket端口信息
     */
    public static final String SOCKET_IP = LogUtil.DEBUG_SERVER ? "192.168.1.11" : "112.124.7.97";
    public static final int SOCKET_PORT = 8888;
    /**
     * 网页端cookie的域名
     */
    public static final String COOKIES_DOMAIN = LogUtil.DEBUG_SERVER ? "http://test.qipaiapp.com" : "http://webapp.qipaiapp.com";
    /**
     * 图片路径前缀
     */
    public static final String IMAGE_URL_PREFIX = LogUtil.DEBUG_SERVER ? "http://teststatic.qipaiapp.com" : "http://static.qipaiapp.com";
    /**
     * 访问webservice接口的TOKEN
     */
    public static final String WEB_SERVER_TOKEN = "QWASD874HAsSF8asJAYOgaIU3JG98hDSN2g3SD671g29385FSA811NASs";
//    Wandoujia
    /***
     * 拍品信息url
     **/
    public static final String WEB_SERVER_URL_PAIPIN = WEB_SERVER_API + "PaipinInfo.asmx";
    /***
     * 用户信息url
     **/
    public static final String WEB_SERVER_URL_USERMAIN = WEB_SERVER_API + "UserMain.asmx";
    /***
     * 拍品分类信息url
     **/
    public static final String WEB_SERVER_URL_PAIPINCATE = WEB_SERVER_API + "PaipinCate.asmx";
    /***
     * 预约信息url
     **/
    public static final String WEB_SERVER_URL_YYMESSAGE = WEB_SERVER_API + "QiPyymessage.asmx";
    /***
     * 点赞信息url
     **/
    public static final String WEB_SERVER_URL_LOVELOG = WEB_SERVER_API + "LoveLog.asmx";
    /***
     * 搜索记录url
     **/
    public static final String WEB_SERVER_URL_SEELOG = WEB_SERVER_API + "SeeLog.asmx";
    /***
     * 出价记录url
     **/
    public static final String WEB_SERVER_URL_AUCTIONRECORD = WEB_SERVER_API + "AuctionRecord.asmx";
    /***
     * 我的银子url
     **/
    public static final String WEB_SERVER_URL_CUSTOMERPAIBI = WEB_SERVER_API + "customerPaiBi.asmx";
    /***
     * 意见反馈url
     **/
    public static final String WEB_SERVER_URL_FEEDBACK = WEB_SERVER_API + "FeedBack.asmx";
    /***
     * 个人信息url
     **/
    public static final String WEB_SERVER_URL_CUSTOMERINFO = WEB_SERVER_API + "CustomerInfo.asmx";
    /***地址订单url**/
//	public static final String WEB_SERVER_URL_USERADDRESS  =  WEB_SERVER_DOMAIN_NAME+"UserAddress.asmx";
    /***
     * 订单信息管理
     **/
    public static final String WEB_SERVER_URL_SYSORDER = WEB_SERVER_API + "SysOrder.asmx";
    /***百度推送**/
//	public static final String WEB_SERVER_URL_BAIDUTS =WEB_SERVER_DOMAIN_NAME+"Baiduts.asmx";
    /***
     * 图片上传 （指的是串码,特定的除外）
     **/
    public static final String WEB_SERVER_URL_SYSPIC = WEB_SERVER_API + "Syspic.asmx";
    /***
     * 站内信
     **/
    public static final String WEB_SERVER_URL_SYSNEWS = WEB_SERVER_API + "SysNews.asmx";
    /***
     * 拍友圈
     **/
    public static final String WEB_SERVER_URL_PF = WEB_SERVER_API + "PF/PF.asmx";
    /***
     * 专场
     **/
    public static final String WEB_SERVER_URL_SPECIAL = WEB_SERVER_API + "PF/Special.asmx";
    /**
     * 发现
     */
    public static final String WEB_SERVER_URL_FIND = WEB_SERVER_API + "FindList.asmx";
    /***
     * 获取合作机构的Icon
     **/
//    public static final String WEB_SERVER_URL_TODAY_COOPERATIVE = WEB_SERVER_DOMAIN_NAME + "FindList.asmx";
    /**
     * 拍品评论
     */
    public static final String WEB_SERVER_URL_PAIPIN_COMMENT = WEB_SERVER_API + "PF/ProductComment.asmx";
    /***
     * 添加代理出价，取消代理出价
     **/
    public static final String WEB_SERVER_URL_PROXYRECORD = WEB_SERVER_API + "ProxyRecord.asmx";
    /***
     * 物流
     **/
    public static final String WEB_SERVER_URL_WULIU = WEB_SERVER_API + "WUliu.asmx?";
    /***
     * 支付宝[订单付款]回调页面
     **/
    public static final String WEB_SERVER_URL_ALIPAY_PAY = WEB_SERVER_DOMAIN_NAME + "AlipayCallback.aspx";
    /***
     * 支付宝[余额充值]回调页面
     **/
    public static final String WEB_SERVER_URL_ALIPAY_CHARGE = WEB_SERVER_DOMAIN_NAME + "AlipayCallbackRecharge.aspx";
    /***
     * 微信[订单付款]回调页面
     **/
    public static final String WEB_SERVER_URL_WXPAY_PAY = WEB_SERVER_DOMAIN_NAME + "WeiAlipayCallback.aspx";
    /***
     * 微信[余额充值]回调页面
     **/
    public static final String WEB_SERVER_URL_WXPAY_CHARGE = WEB_SERVER_DOMAIN_NAME + "WeiAlipayCallbackRecharge.aspx";
    /***
     * 微信[保证金充值]回调页面
     **/
    public static final String WEB_SERVER_URL_WXPAY_MARGIN_CHARGE = WEB_SERVER_DOMAIN_NAME + "WeiCallbackMarginRecharge.aspx";
    /***
     * 支付宝[保证金充值]回调页面
     **/
    public static final String WEB_SERVER_URL_ALIPAY_MARGIN_CHARGE = WEB_SERVER_DOMAIN_NAME + "AlipayCallbackMarginRecharge.aspx";
    /***
     * 明日预展轮播url 正式数据时修改地址
     **/
    public static final String WEB_SERVER_URL_SPECIAL_AD = WEB_SERVER_API + "PaipinInfo.asmx";

    /**
     * 软件发布路径
     */
    public static final String APK_NAME = "Qipai.apk";
    /**
     * 软件下载路径:<br/>
     * 正式服务器：http://static.qipaiapp.com/version/Qipai.apk<br/>
     * 测试服务器：http://teststatic.qipaiapp.com/version/Qipai.apk
     */
    public static final String APK_URL = "http://static.qipaiapp.com/" + "version/" + APK_NAME;
    //	public static final String APK_URL = WEB_SERVER_DOMAIN_NAME+"version/"+APK_NAME;

    /***
     * 内部错误 1000
     */
    public static final String CODE_ERROR = "1000";
    /***
     * 数据返回成功码 1001
     */
    public static final String CODE_NUM_OK = "1001";
    /***
     * 返回数据为空 1002
     */
    public static final String CODE_DATA_NULL = "1002";
    /***
     * 手机号已注册 1003
     */
    public static final String CODE_PHONE_EXIST = "1003";
    /***
     * 密码错误 1004
     */
    public static final String CODE_LOGIN_FAILED = "1004";
    /***
     * 脚本攻击 1005
     */
    public static final String CODE_SCRIPT_ATTACK = "1005";
    /***
     * 已删除(逻辑删除) 1006
     */
    public static final String CODE_DELETED = "1006";
    /***
     * 客户端传递参数错误 1007
     */
    public static final String CODE_PARAMETER_ERROR = "1007";
    /***
     * 传来的参数无效 1009
     */
    public static final String CODE_INVALID_PARAM_ = "1009";
    /***
     * 事务执行失败 1010
     */
    public static final String CODE_AFFAIR_FAILED = "1010";
    /***
     * 当前商品的最高价格 1011
     */
    public static final String CODE_NOW_FIRST = "1011";
    /***
     * 删除用户地址, 地址编号与用户编号不匹配 1012
     */
    public static final String CODE_UNMATCHED = "1012";
    /***
     * 用户不存在 1013
     */
    public static final String CODE_USER_NOT_EXIST = "1013";
    /***
     * 拍品不存在 1014
     */
    public static final String CODE_GOODS_NOT_EXIST = "1014";
    /***
     * 价格低于当前拍品价格 1015
     */
    public static final String CODE_LOW_PRICE = "1015";
    /***
     * 收货地址编号不存在 1016
     */
    public static final String CODE_ADDRESS_NUM_NOT_EXIST = "1016";
    /***
     * 收货地址ID不存在 1017
     */
    public static final String CODE_ADDRESS_ID_NOT_EXIST = "1017";
    /***
     * 支付密码不匹配（修改密码） 1018
     */
    public static final String CODE_PAYMENT_CODE = "1018";
    /***
     * 验证码不匹配 1019
     */
    public static final String CODE_VALIDATE_CODE = "1019";
    /***
     * 邮箱已被绑定 1020
     */
    public static final String CODE_EMAIL_ALREADY_BINDING = "1020";
    /***
     * 身份证已被实名 1021
     */
    public static final String CODE_EMAIL_ALREADY_VERIFY = "1021";
    /***
     * 支付密码不正确（付款） 1022
     */
    public static final String CODE_PAYMENT_ERROR = "1022";
    /***
     * 订单不存在 1023
     */
    public static final String CODE_ORDER_NOT_EXIST = "1023";
    /***
     * 预约失败,该拍品已经过期 或者离开拍时间太接近 1024
     */
    public static final String CODE_REMIND_TIME_OUT = "1024";
    /***
     * 该拍品状态无法预约，只针对今日 明日拍品预约 1025
     */
    public static final String CODE_REMIND_NONSUPPORT = "1025";
    /***
     * 该验证码已被使用 1026
     */
    public static final String CODE_IS_USED = "1026";
    /***
     * 拍品出价不能比当前最高出价低 1027
     */
    public static final String CODE_NOT_LOWER = "1027";
    /***
     * 竞拍已结束 1028
     */
    public static final String CODE_IS_FINISHED = "1028";
    /***
     * 竞拍未开始 1029
     */
    public static final String CODE_UNSTART = "1029";
    /***
     * 该拍品不是今日拍品 1030
     */
    public static final String CODE_IS_NOT_TODAY = "1030";
    /***
     * 低于最高代理价 1031
     */
    public static final String CODE_IS_PROXY = "1031";
    /***
     * 账号被冻结 1032
     */
    public static final String CODE_IS_FREEZE = "1032";
    /***
     * 拍品已生成订单 1033
     */
    public static final String CODE_ORDER_IS_EXIST = "1033";
    /***
     * 站内信已是已读状态 1034
     */
    public static final String CODE_SYSNEWS_IS_READ = "1034";
    /***
     * 拍品已失效 1035
     */
    public static final String CODE_PAIPIN_IS_INVALID = "1035";
    /***
     * 优惠码不存在 1036
     */
    public static final String CODE_NOTE_NOT_EXIST = "1036";
    /***
     * 优惠码已使用 1037
     */
    public static final String CODE_NOTE_IS_USED = "1037";
    /***
     * 优惠码已过期 1038
     */
    public static final String CODE_NOTE_IS_OUTTIME = "1038";
    /***
     * 拍品库存数量不足 1039
     */
    public static final String CODE_LAZY_WEIGH = "1039";
    /***
     * 该订单无法退货 1040
     */
    public static final String CODE_NOT_RETURN = "1040";
    /***
     * 您的出价与其他代理价相同，请重新出价 1041
     */
    public static final String CODE_EQUALS_PROXY_PRICE = "1041";
    /***
     * Token 1042
     */
    public static final String CODE_TOKEN_WRONG = "1042";
    /***
     * 余额不足 1043
     */
    public static final String CODE_LACK_BALANCE = "1043";
    /***
     * 银子不足 1044
     */
    public static final String CODE_LACK_SILVER = "1044";
    /***
     * 客官，您今日提现次数已满。 1045
     */
    public static final String CODE_UNALLOWED_WITHDRAW = "1045";
    /***
     * 客官，您今日提现金额已达上限。 1046
     */
    public static final String CODE_UPPER_LIMIT = "1046";
    /***
     * 客官，这个订单不允许取消。 1047
     */
    public static final String CODE_ORDER_UNCANCLE = "1047";
    /***
     * 该用户未被冻结。 1048
     */
    public static final String CODE_USER_NOT_FROZEN = "1048";
    /***
     * 空字符串。 1050
     */
    public static final String CODE_STRING_IS_NULL = "1050";
    /***
     * 拍友圈ID不存在。 1051
     */
    public static final String CODE_FRIEND_ID_NOT_EXIST = "1051";
    /***
     * 拍友圈ID不匹配。 1052
     */
    public static final String CODE_FRIEND_ID_NOT_MISMATCHING = "1052";
    /***
     * 评论编号不存在。 1053
     */
    public static final String CODE_FRIEND_COMMENT_ID_NOT_EXIST = "1053";
    /***
     * 点赞编号不存在。 1054
     */
    public static final String CODE_FRIEND_PARISE_ID_NOT_EXIST = "1054";
    /***
     * 专场编号不存在。 1055
     */
    public static final String CODE_SPECIAL_ID_NOT_EXIST = "1055";
    /***
     * 专场预约时间已过。 1056
     */
    public static final String CODE_SPECIAL_REMIND_TIME_OUT = "1056";
    /***
     * 已申请推广商。 1057
     */
    public static final String CODE_ALREADY_APPLY_PROMOTION = "1057";
    /***
     * 管理员已经回复过本条评论了。 1058
     */
    public static final String CODE_ALREADY_REPLY_COMMENT = "1058";
    /**
     * 订单未支付(保证金提现时) 1059
     **/
    public static final String CODE_DEPOSIT_ORDER = "1059";
    /**
     * 订本批次银票兑换上限 1060
     **/
    public static final String CODE_DEPOSIT_MOST_COUNT = "1060";
    /**
     * 获取当前APP版本过低，需要强制更新 1061
     */
    public static final String CODE_NEED_UPDATE = "1061";
    /**
     * 今天不是用户的生日 1062
     */
    public static final String CODE_TODAY_NOT_BIRTHDAY = "1062";
    /**
     * 该用户领取过生日奖励了 1063
     */
    public static final String CODE_TODAY_IS_BIRTHDAY = "1063";
    /**
     * 订单删除失败（代发货和待收货不能删除）
     */
    public static final String CODE_ORDER_DELETE_FAILED = "1064";
    /**
     * 任务活跃 中的任务已做将返回1065
     */
    public static final String CODE_TASK_REWARD_HAS_DONE = "1065";
    /***
     * 预约成功
     */
    public static final String CODE_DATA_REMIND_OK = "10002";
    /***
     * 取消预约成功
     */
    public static final String CODE_DATA_REMIND_NO = "10003";
    /***
     * 服务端数据解析异常
     */
    public static final String CODE_EXCEPTION_ANALYZE = "数据解析异常";


    /*** 服务端返回值为空 */
//	public static final String CODE_EXCEPTION_NULL = "CODE_EXCEPTION_NULL";
    /*** 服务端其他返回值 */
    //public static final String CODE_EXCEPTION_OTHER_CODE = "CODE_EXCEPTION_OTHER_CODE";
    /***
     * 服务端数据访问失败
     */
    public static final String CODE_EXCEPTION_ERROR = "网络连接超时";
    /**
     * 用户注册来源，用于统计渠道信息
     * TalkingData：“渠道ID”最多包含64个字符，支持中文、英文、数字、下划线，但不能包含空格或其他的转义字符。
     */
    public static String REGISTER_CHANNEL = "activity";
    /**
     * talking_data的app_id<br/>
     * 系统计算App ID时，读取App ID的优先级如下：AndroidManifest.xml中配置的App ID>TCAgent.init(this, "您的 App ID", "渠道 ID")方法配置的App ID；
     */
    public static String TD_APP_ID = "0E0213BBA86A47149F2E43D18A7E3502";
}
