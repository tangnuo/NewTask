package com.example.caowj.newtask.mvp.entity.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 系统图片
 * package: com.jsfengling.qipai.data
 * className: SysPicInfo
 *
 * @author caowj
 * date：2015年10月12日 下午5:48:18
 */
public class SysPicInfo implements Parcelable {

    public static final Parcelable.Creator<SysPicInfo> CREATOR = new Parcelable.Creator<SysPicInfo>() {

        @Override
        public SysPicInfo createFromParcel(Parcel source) {
            return new SysPicInfo(source);
        }

        @Override
        public SysPicInfo[] newArray(int size) {
            return new SysPicInfo[size];
        }
    };
    private int Id;
    /**
     * 配图编号
     */
    private String PicNum;
    /**
     * 配图种类  商品配图
     */
    private String PicCate;
    /**
     * 配图
     */
    private String shopingPic;
    /**
     * 添加时间
     */
    private long Addtime;
    /**
     * IP地址
     */
    private String AddIP;
    /**
     * 添加图片的商家ID
     */
    private long shopId;
    /**
     * 以下为备注字段 加入我们有疑问
     */
    private String remark1;
    private String remark2;
    private String remark3;
    /**
     * 是否删除
     */
    private int flat1;
    /**
     * 是否冻结
     */
    private int flat2;
    /***
     * 账户余额
     */
    private String remark4;
    /**
     * 支付密码
     */
    private String remark5;
    private String remark6;
    /**
     * 上级 用户编号 如无上级 0
     */
    private int flat7;
    private int flat8;
    /**
     * 用户注册时间
     */
    private String RegTim1;
    /**
     * 申请时间 （用户申请成为商家）
     */
    private String RegTim2;

    public SysPicInfo() {
        super();
    }

    public SysPicInfo(String shopingPic) {
        this.shopingPic = shopingPic;
    }

    public SysPicInfo(Parcel in) {
        Id = in.readInt();
        PicNum = in.readString();
        PicCate = in.readString();
        shopingPic = in.readString();
        AddIP = in.readString();
        Addtime = in.readLong();
        shopId = in.readLong();
        remark1 = in.readString();
        remark2 = in.readString();
        remark3 = in.readString();
        remark4 = in.readString();
        remark5 = in.readString();
        remark6 = in.readString();
        flat1 = in.readInt();
        flat2 = in.readInt();
        flat7 = in.readInt();
        flat8 = in.readInt();
        RegTim1 = in.readString();
        RegTim2 = in.readString();
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getPicNum() {
        return PicNum;
    }

    public void setPicNum(String picNum) {
        PicNum = picNum;
    }

    public String getPicCate() {
        return PicCate;
    }

    public void setPicCate(String picCate) {
        PicCate = picCate;
    }

    public String getShopingPic() {
        return shopingPic;
    }

    public void setShopingPic(String shopingPic) {
        this.shopingPic = shopingPic;
    }

    public long getAddtime() {
        return Addtime;
    }

    public void setAddtime(long addtime) {
        Addtime = addtime;
    }

    public String getAddIP() {
        return AddIP;
    }

    public void setAddIP(String addIP) {
        AddIP = addIP;
    }

    public long getShopId() {
        return shopId;
    }

    public void setShopId(long shopId) {
        this.shopId = shopId;
    }

    public String getRemark1() {
        return remark1;
    }

    public void setRemark1(String remark1) {
        this.remark1 = remark1;
    }

    public String getRemark2() {
        return remark2;
    }

    public void setRemark2(String remark2) {
        this.remark2 = remark2;
    }

    public String getRemark3() {
        return remark3;
    }

    public void setRemark3(String remark3) {
        this.remark3 = remark3;
    }

    public int getFlat1() {
        return flat1;
    }

    public void setFlat1(int flat1) {
        this.flat1 = flat1;
    }

    public int getFlat2() {
        return flat2;
    }

    public void setFlat2(int flat2) {
        this.flat2 = flat2;
    }

    public String getRemark4() {
        return remark4;
    }

    public void setRemark4(String remark4) {
        this.remark4 = remark4;
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

    public int getFlat7() {
        return flat7;
    }

    public void setFlat7(int flat7) {
        this.flat7 = flat7;
    }

    public int getFlat8() {
        return flat8;
    }

    public void setFlat8(int flat8) {
        this.flat8 = flat8;
    }

    public String getRegTim1() {
        return RegTim1;
    }

    public void setRegTim1(String regTim1) {
        RegTim1 = regTim1;
    }

    public String getRegTim2() {
        return RegTim2;
    }

    public void setRegTim2(String regTim2) {
        RegTim2 = regTim2;
    }

    @Override
    public int describeContents() {

        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(Id);
        dest.writeString(PicNum);
        dest.writeString(PicCate);
        dest.writeString(shopingPic);
        dest.writeString(AddIP);
        dest.writeLong(Addtime);
        dest.writeLong(shopId);
        dest.writeString(remark1);
        dest.writeString(remark2);
        dest.writeString(remark3);
        dest.writeString(remark4);
        dest.writeString(remark5);
        dest.writeString(remark6);
        dest.writeInt(flat1);
        dest.writeInt(flat2);
        dest.writeInt(flat7);
        dest.writeInt(flat8);
        dest.writeString(RegTim1);
        dest.writeString(RegTim2);
    }
}
