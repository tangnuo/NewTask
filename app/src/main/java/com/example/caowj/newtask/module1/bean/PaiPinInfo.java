package com.example.caowj.newtask.module1.bean;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * 拍品信息（包含【今日拍品】、【往期拍品】、【今日掌柜推荐】） <br/>
 * 注意：这是一个拍品相关的共同数据结构；在解析json数据时，提取了共同的字段，然后不同的接口，解析特有的字段。<br/>
 * package: com.jsfengling.qipai.data
 * className: PaiPinInfo
 * Description: 拍品基本信息
 *
 * @author caowj
 *         date：2015年8月27日 下午4:31:26
 */
@SuppressLint("SimpleDateFormat")
public class PaiPinInfo implements Parcelable {

    private int Id;
    /**
     * 宝贝名称
     */
    private String paipinName;
    /**
     * 商品编号
     */
    private String paipinNum;
    /**
     * 宝贝种类
     */
    private int paipinCate;
    /**
     * 宝贝品种
     */
    private String pinzhong;
    /**
     * 尺寸
     */
    private String size;
    /**
     * 重量
     */
    private String weights;
    /**
     * 宝贝描述
     */
    private String describle;
    /**
     * 一口价
     */
    private long y_price;
    /**
     * 活动价
     */
    private long ActivePrice;
    /**
     * 保留价
     */
    private long Retain_price;
    /**
     * 实际结束日期
     */
    private long ActualEndTim;
    /**
     * 访问数量
     */
    private int visitCount;
    /**
     * 点赞数
     */
    private int loveCount;
    /**
     * 宝贝视频
     */
    private String video;
    /**
     * 快递公司名称
     */
    private String kuaidiName;
    /**
     * 快递编号
     */
    private String kuaidiNum;
    /**
     * 是否允许进入一口价专区  默认愿意  0：愿意  1：不愿意
     */
    private int Prefecture;
    /**
     * 新增字段 pansq  2016-07-08
     */
    private String userTel;
    /**
     * 商家Tel
     */
    private String shopTel;
    /**
     * 商家名称
     */
    private String shopName;
    private String remark3;
    /**
     * 是否删除
     */
    private int flat1;
    private String remark5;
    private String remark6;
    /**
     * 是否进入过一口价专区
     */
    private int flat8;
    /**
     * 进入今日拍品时间 （只有进入明日返回的商品才有商家时间）
     */
    private String RegTim2;


    /*******************************************************************************************/
    /***************************被替换的旧字段**************************/
    /** 所属的商家
     private int shopId;
     *//** 宝贝品牌 *//*
    private String paipinPP;

    *//** 宝贝款式 *//*
    private String kuanshi;
    *//** 宝贝材质*//*
    private String caizhi;
    *//** 拍品数量*//*
    private int GuiGe;
    *//** 产地 *//*
    private String canDi;
    *//** 一口价-封面 *//*
    private String YKJ_pic;
    *//** 启拍价 *//*
    private long s_price;
    *//** 秒杀价 *//*
    private long m_price;
    *//** 加价幅度 *//*
    private long fudu;
    *//** 开始时间 *//*
    private String Stim;
    *//** 结束时间 *//*
    private String Etim;
    *//** 明日、今日预展-封面 *//*
    private String picMain;
    *//** 今日/明日/一口价轮播图串码 *//*
    private String pic;

    *//** 快递费用 *//*
    private float kuaidiMoney;
    *//** 宝贝详情图串码 *//*
    private String remark1;
    *//** 运费险 *//*
    private float yunfeixian;
    *//** 宝贝状态  （-1：未通过审核 0：未审核  1：保留审核）  2：明日 3：今日 4：一口价 5：已下架 6：已竞拍 7：已购买   PS：如果拍品已被竞拍但为付款，如果该拍品是今日商品将自动进入：一口价，如果是一口价商品：下架*//*
    private int isshenhe;
    *//** 审核时间 *//*
    private long examineTim;
    private String remark2;
    private String remark4;
    *//**0 商家添加 1管理员添加*//*
    private int flat7;
    *//** 当拍品下架后或者竞拍后 保存拍品在下架前或者竞拍前所在的区域， 比如一口价 今日  *//*
    private int flat2;
    *//** 宝贝添加时间 *//*
    private String AddTim;*/

    /*****************************新版本－－新增字段***********************/
    /**
     * 类别图片串码
     */
    private String Img;
    /**
     * 种类名称
     */
    private String cateName;
    /**
     * 是否设置了保留价 0默认没有
     */
    private int IsRetain_price;
    /**
     * 未通过审核原因 --NEW 新加字段
     */
    private String ReasonDesc;
    /**
     * 是否留拍
     */
    private int IsSurplus;

    /**＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊新版本－－重新命名的字段＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊*/
    /**
     * 留拍时间
     */
    private int TimeSurplus;
    /**
     * 所属的商家
     */
    private int UserID; //shopId;
    /**
     * 宝贝品牌
     */
    private String Brand;//paipinPP;
    /**
     * 宝贝款式
     */
    private String Pieces;//kuanshi;
    /**
     * 宝贝材质
     */
    private String Material;//caizhi;
    /**
     * 库存数量(针对管理员一口价)
     */
    private int Number;//GuiGe;
    /**
     * 产地
     */
    private String Production;//canDi;
    /**
     * 一口价-封面
     */
    private String PicFixedPrice;//YKJ_pic
    /**
     * 启拍价
     */
    private long S_price;//s_price
    /**
     * 秒杀价
     */
    private long M_price;//m_price
    /**
     * 加价幅度
     */
    private long Markup;//fudu
    /**
     * 开始时间
     */
    private long TimeS;//Stim
    /**
     * 结束时间
     */
    private long TimeE;//Etim;
    /**
     * 明日、今日预展-封面
     */
    private String PicMainTomorrow;//picMain;
    /**
     * 今日/明日/一口价轮播图串码
     */
    private String PicCarousel;//pic;
    /**
     * 宝贝详情图串码
     */
    private String PicDetails;//remark1;
    /**
     * 快递费用
     */
    private double Freight;//kuaidiMoney;
    //之前的字段isshenhe 拆分成3个字段 分别对应审核状态 宝贝区域 和宝贝状态
    /**
     * 运费险
     */
    private double InsurancePremium;//yunfeixian;
    /**
     * 是否审核 默认没有审核 （0：未审核  1：未通过审核 -1保留审核）
     */
    private int IsExamine;
    /**
     * 宝贝未位置    2：明日 3：今日 4：一口价
     */
    private int Position;
    /**
     * 宝贝状态 0:正常 5：已下架 6：已竞拍 7：已购买   PS：如果拍品已被竞拍但为付款，如果该拍品是今日商品将自动进入：一口价，如果是一口价商品：下架
     */
    private int State;
    /**
     * 审核时间
     */
    private long TimeExamine;//examineTim;
    /**
     * 是否放入库存
     */
    private int IsStock;//remark2;
    /**
     * 排序编号
     */
    private int Sort;// remark4;
    /**
     * 当拍品下架后或者竞拍后 保存拍品在下架前或者竞拍前所在的区域， 比如一口价 今日
     */
    private int RetainState;//flat2;
    /**
     * 0 商家添加 1管理员添加
     */
    private int RoleUse;//flat7;
    /**
     * 宝贝添加时间
     */
    private String TimeAdd;//AddTim;
    /**
     * 进入今日拍品时间 （只有进入明日返回的商品才有商家时间）
     */
    private String TimeYKJ;//RegTim1
    private long MaxPrice;//最终成交价（往期掌柜推荐）
    /**
     * 是否删除（针对一口价商品）
     */
    private int IsDel;
    /**
     * 市场估值
     */
    private long MarketPrice;
    /**
     * 商家头像 v2.6 新增字段
     */
    private String MerchantAvatar;
    /**
     * v2.6.0 一口价详情页介绍
     */
    private String Introduction;

    public String getIntroduction() {
        return Introduction;
    }

    public void setIntroduction(String introduction) {
        Introduction = introduction;
    }

    public String getUserPhoto() {
        return MerchantAvatar;
    }

    public void setUserPhoto(String userPhoto) {
        this.MerchantAvatar = userPhoto;
    }

    public long getMarketPrice() {
        return MarketPrice;
    }

    public void setMarketPrice(long marketPrice) {
        MarketPrice = marketPrice;
    }

    /***************************
     * 分割线

     *******************************/
    public PaiPinInfo() {
        super();
    }

    public static Creator<PaiPinInfo> getCREATOR() {
        return CREATOR;
    }

    public long getMaxPrice() {
        return MaxPrice;
    }

    public void setMaxPrice(long maxPrice) {
        MaxPrice = maxPrice;
    }

    public int getIsDel() {
        return IsDel;
    }

    public void setIsDel(int isDel) {
        IsDel = isDel;
    }

    public String getImg() {
        return Img;
    }

    public void setImg(String img) {
        Img = img;
    }

    public long getS_price() {
        return S_price;
    }

    public void setS_price(long s_price) {
        S_price = s_price;
    }

    public long getActualEndTim() {
        return ActualEndTim;
    }

    public void setActualEndTim(long actualEndTim) {
        ActualEndTim = actualEndTim;
    }

    public String getBrand() {
        return Brand;
    }

    public void setBrand(String brand) {
        Brand = brand;
    }

    public String getCateName() {
        return cateName;
    }

    public void setCateName(String cateName) {
        this.cateName = cateName;
    }

    public String getDescrible() {
        return describle;
    }

    public void setDescrible(String describle) {
        this.describle = describle;
    }

    public int getFlat1() {
        return flat1;
    }

    public void setFlat1(int flat1) {
        this.flat1 = flat1;
    }

    public int getFlat8() {
        return flat8;
    }

    public void setFlat8(int flat8) {
        this.flat8 = flat8;
    }

    public double getFreight() {
        return Freight;
    }

    public void setFreight(double freight) {
        Freight = freight;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public double getInsurancePremium() {
        return InsurancePremium;
    }

    public void setInsurancePremium(double insurancePremium) {
        InsurancePremium = insurancePremium;
    }

    public int getIsExamine() {
        return IsExamine;
    }

    public void setIsExamine(int isExamine) {
        IsExamine = isExamine;
    }

    public int getIsRetain_price() {
        return IsRetain_price;
    }

    public void setIsRetain_price(int isRetain_price) {
        IsRetain_price = isRetain_price;
    }

    public int getIsStock() {
        return IsStock;
    }

    public void setIsStock(int isStock) {
        IsStock = isStock;
    }

    public int getIsSurplus() {
        return IsSurplus;
    }

    public void setIsSurplus(int isSurplus) {
        IsSurplus = isSurplus;
    }

    public String getKuaidiName() {
        return kuaidiName;
    }

    public void setKuaidiName(String kuaidiName) {
        this.kuaidiName = kuaidiName;
    }

    public String getKuaidiNum() {
        return kuaidiNum;
    }

    public void setKuaidiNum(String kuaidiNum) {
        this.kuaidiNum = kuaidiNum;
    }

    public int getLoveCount() {
        return loveCount;
    }

    public void setLoveCount(int loveCount) {
        this.loveCount = loveCount;
    }

    public long getM_price() {
        return M_price;
    }

    public void setM_price(long m_price) {
        M_price = m_price;
    }

    public long getMarkup() {
        return Markup;
    }

    public void setMarkup(long markup) {
        Markup = markup;
    }

    public String getMaterial() {
        return Material;
    }

    public void setMaterial(String material) {
        Material = material;
    }

    public int getNumber() {
        return Number;
    }

    public void setNumber(int number) {
        Number = number;
    }

    public int getPaipinCate() {
        return paipinCate;
    }

    public void setPaipinCate(int paipinCate) {
        this.paipinCate = paipinCate;
    }

    public String getPaipinName() {
        return paipinName;
    }

    public void setPaipinName(String paipinName) {
        this.paipinName = paipinName;
    }

    public String getPaipinNum() {
        return paipinNum;
    }

    public void setPaipinNum(String paipinNum) {
        this.paipinNum = paipinNum;
    }

    public String getPicCarousel() {
        return PicCarousel;
    }

    public void setPicCarousel(String picCarousel) {
        PicCarousel = picCarousel;
    }

    public String getPicDetails() {
        return PicDetails;
    }

    public void setPicDetails(String picDetails) {
        PicDetails = picDetails;
    }

    public String getPicFixedPrice() {
        return PicFixedPrice;
    }

    public void setPicFixedPrice(String picFixedPrice) {
        PicFixedPrice = picFixedPrice;
    }

    public String getPicMainTomorrow() {
        return PicMainTomorrow;
    }

    public void setPicMainTomorrow(String picMainTomorrow) {
        PicMainTomorrow = picMainTomorrow;
    }

    public String getPieces() {
        return Pieces;
    }

    public void setPieces(String pieces) {
        Pieces = pieces;
    }

    public String getPinzhong() {
        return pinzhong;
    }

    public void setPinzhong(String pinzhong) {
        this.pinzhong = pinzhong;
    }

    public int getPosition() {
        return Position;
    }

    public void setPosition(int position) {
        Position = position;
    }

    public int getPrefecture() {
        return Prefecture;
    }

    public void setPrefecture(int prefecture) {
        Prefecture = prefecture;
    }

    public String getProduction() {
        return Production;
    }

    public void setProduction(String production) {
        Production = production;
    }

    public String getReasonDesc() {
        return ReasonDesc;
    }

    public void setReasonDesc(String reasonDesc) {
        ReasonDesc = reasonDesc;
    }

    public String getRegTim2() {
        return RegTim2;
    }

    public void setRegTim2(String regTim2) {
        RegTim2 = regTim2;
    }

    public String getRemark3() {
        return remark3;
    }

    public void setRemark3(String remark3) {
        this.remark3 = remark3;
    }

    public String getRemark5() {
        return remark5;
    }

    public void setRemark5(String remark5) {
        this.remark5 = remark5;
    }

    public String getRemark6() {
        return remark6;
    }

    public void setRemark6(String remark6) {
        this.remark6 = remark6;
    }

    public long getRetain_price() {
        return Retain_price;
    }

    public void setRetain_price(long retain_price) {
        Retain_price = retain_price;
    }

    public int getRetainState() {
        return RetainState;
    }

    public void setRetainState(int retainState) {
        RetainState = retainState;
    }

    public int getRoleUse() {
        return RoleUse;
    }

    public void setRoleUse(int roleUse) {
        RoleUse = roleUse;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopTel() {
        return shopTel;
    }

    public void setShopTel(String shopTel) {
        this.shopTel = shopTel;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getSort() {
        return Sort;
    }

    public void setSort(int sort) {
        Sort = sort;
    }

    public int getState() {
        return State;
    }

    public void setState(int state) {
        State = state;
    }

    public String getTimeAdd() {
        return TimeAdd;
    }

    public void setTimeAdd(String timeAdd) {
        TimeAdd = timeAdd;
    }

    public long getTimeE() {
        return TimeE;
    }

    public void setTimeE(long timeE) {
        TimeE = timeE;
    }

    public long getTimeExamine() {
        return TimeExamine;
    }

    public void setTimeExamine(long timeExamine) {
        TimeExamine = timeExamine;
    }

    public long getTimeS() {
        return TimeS;
    }

    public void setTimeS(long timeS) {
        TimeS = timeS;
    }

    public int getTimeSurplus() {
        return TimeSurplus;
    }

    public void setTimeSurplus(int timeSurplus) {
        TimeSurplus = timeSurplus;
    }

    public String getTimeYKJ() {
        return TimeYKJ;
    }

    public void setTimeYKJ(String timeYKJ) {
        TimeYKJ = timeYKJ;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {
        UserID = userID;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public int getVisitCount() {
        return visitCount;
    }

    public void setVisitCount(int visitCount) {
        this.visitCount = visitCount;
    }

    public String getWeights() {
        return weights;
    }

    public void setWeights(String weights) {
        this.weights = weights;
    }

    public long getY_price() {
        return y_price;
    }

    public void setY_price(long y_price) {
        this.y_price = y_price;
    }

    public String getUserTel() {
        return userTel;
    }

    public void setUserTel(String userTel) {
        this.userTel = userTel;
    }

    public long getActivePrice() {
        return ActivePrice;
    }

    public void setActivePrice(long activePrice) {
        ActivePrice = activePrice;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.Id);
        dest.writeString(this.paipinName);
        dest.writeString(this.paipinNum);
        dest.writeInt(this.paipinCate);
        dest.writeString(this.pinzhong);
        dest.writeString(this.size);
        dest.writeString(this.weights);
        dest.writeString(this.describle);
        dest.writeLong(this.y_price);
        dest.writeLong(this.Retain_price);
        dest.writeLong(this.ActualEndTim);
        dest.writeInt(this.visitCount);
        dest.writeInt(this.loveCount);
        dest.writeString(this.video);
        dest.writeString(this.kuaidiName);
        dest.writeString(this.kuaidiNum);
        dest.writeInt(this.Prefecture);
        dest.writeString(this.userTel);
        dest.writeString(this.shopTel);
        dest.writeString(this.shopName);
        dest.writeString(this.remark3);
        dest.writeInt(this.flat1);
        dest.writeString(this.remark5);
        dest.writeString(this.remark6);
        dest.writeInt(this.flat8);
        dest.writeString(this.RegTim2);
        dest.writeString(this.Img);
        dest.writeString(this.cateName);
        dest.writeInt(this.IsRetain_price);
        dest.writeString(this.ReasonDesc);
        dest.writeInt(this.IsSurplus);
        dest.writeInt(this.TimeSurplus);
        dest.writeInt(this.UserID);
        dest.writeString(this.Brand);
        dest.writeString(this.Pieces);
        dest.writeString(this.Material);
        dest.writeInt(this.Number);
        dest.writeString(this.Production);
        dest.writeString(this.PicFixedPrice);
        dest.writeLong(this.S_price);
        dest.writeLong(this.M_price);
        dest.writeLong(this.Markup);
        dest.writeLong(this.TimeS);
        dest.writeLong(this.TimeE);
        dest.writeString(this.PicMainTomorrow);
        dest.writeString(this.PicCarousel);
        dest.writeString(this.PicDetails);
        dest.writeDouble(this.Freight);
        dest.writeDouble(this.InsurancePremium);
        dest.writeInt(this.IsExamine);
        dest.writeInt(this.Position);
        dest.writeInt(this.State);
        dest.writeLong(this.TimeExamine);
        dest.writeInt(this.IsStock);
        dest.writeInt(this.Sort);
        dest.writeInt(this.RetainState);
        dest.writeInt(this.RoleUse);
        dest.writeString(this.TimeAdd);
        dest.writeString(this.TimeYKJ);
        dest.writeLong(this.MaxPrice);
        dest.writeInt(this.IsDel);
        dest.writeLong(this.MarketPrice);
        dest.writeLong(this.ActivePrice);
        dest.writeString(this.MerchantAvatar);
        dest.writeString(this.Introduction);
    }

    protected PaiPinInfo(Parcel in) {
        this.Id = in.readInt();
        this.paipinName = in.readString();
        this.paipinNum = in.readString();
        this.paipinCate = in.readInt();
        this.pinzhong = in.readString();
        this.size = in.readString();
        this.weights = in.readString();
        this.describle = in.readString();
        this.y_price = in.readLong();
        this.Retain_price = in.readLong();
        this.ActualEndTim = in.readLong();
        this.visitCount = in.readInt();
        this.loveCount = in.readInt();
        this.video = in.readString();
        this.kuaidiName = in.readString();
        this.kuaidiNum = in.readString();
        this.Prefecture = in.readInt();
        this.userTel = in.readString();
        this.shopTel = in.readString();
        this.shopName = in.readString();
        this.remark3 = in.readString();
        this.flat1 = in.readInt();
        this.remark5 = in.readString();
        this.remark6 = in.readString();
        this.flat8 = in.readInt();
        this.RegTim2 = in.readString();
        this.Img = in.readString();
        this.cateName = in.readString();
        this.IsRetain_price = in.readInt();
        this.ReasonDesc = in.readString();
        this.IsSurplus = in.readInt();
        this.TimeSurplus = in.readInt();
        this.UserID = in.readInt();
        this.Brand = in.readString();
        this.Pieces = in.readString();
        this.Material = in.readString();
        this.Number = in.readInt();
        this.Production = in.readString();
        this.PicFixedPrice = in.readString();
        this.S_price = in.readLong();
        this.M_price = in.readLong();
        this.Markup = in.readLong();
        this.TimeS = in.readLong();
        this.TimeE = in.readLong();
        this.PicMainTomorrow = in.readString();
        this.PicCarousel = in.readString();
        this.PicDetails = in.readString();
        this.Freight = in.readDouble();
        this.InsurancePremium = in.readDouble();
        this.IsExamine = in.readInt();
        this.Position = in.readInt();
        this.State = in.readInt();
        this.TimeExamine = in.readLong();
        this.IsStock = in.readInt();
        this.Sort = in.readInt();
        this.RetainState = in.readInt();
        this.RoleUse = in.readInt();
        this.TimeAdd = in.readString();
        this.TimeYKJ = in.readString();
        this.MaxPrice = in.readLong();
        this.IsDel = in.readInt();
        this.MarketPrice = in.readLong();
        this.ActivePrice = in.readLong();
        this.MerchantAvatar = in.readString();
        this.Introduction = in.readString();
    }

    public static final Creator<PaiPinInfo> CREATOR = new Creator<PaiPinInfo>() {
        @Override
        public PaiPinInfo createFromParcel(Parcel source) {
            return new PaiPinInfo(source);
        }

        @Override
        public PaiPinInfo[] newArray(int size) {
            return new PaiPinInfo[size];
        }
    };
}
