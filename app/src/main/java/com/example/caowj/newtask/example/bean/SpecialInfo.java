package com.example.caowj.newtask.example.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 专场分区
 * package: com.jsfengling.qipai.data
 * className: AuctionRecord
 *
 * @author caowj
 *         date：2015年9月24日 下午2:39:31
 */
public class SpecialInfo implements Parcelable {

    private int ID;

    private String Name;

    private String Pic;

    private String PicNo;

    private int Sort;

    private String Sdesc;

    private long TimeStr;

    private long TimeEnd;

    private long TimeAdd;

    private long TimeDel;

    private int IsDel;

    private String Describe;

    private int ProductCount;

    private int LoveCount;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPic() {
        return Pic;
    }

    public void setPic(String pic) {
        Pic = pic;
    }

    public String getPicNo() {
        return PicNo;
    }

    public void setPicNo(String picNo) {
        PicNo = picNo;
    }

    public int getSort() {
        return Sort;
    }

    public void setSort(int sort) {
        Sort = sort;
    }

    public String getSdesc() {
        return Sdesc;
    }

    public void setSdesc(String sdesc) {
        Sdesc = sdesc;
    }

    public long getTimeStr() {
        return TimeStr;
    }

    public void setTimeStr(long timeStr) {
        TimeStr = timeStr;
    }

    public long getTimeEnd() {
        return TimeEnd;
    }

    public void setTimeEnd(long timeEnd) {
        TimeEnd = timeEnd;
    }

    public long getTimeAdd() {
        return TimeAdd;
    }

    public void setTimeAdd(long timeAdd) {
        TimeAdd = timeAdd;
    }

    public long getTimeDel() {
        return TimeDel;
    }

    public void setTimeDel(long timeDel) {
        TimeDel = timeDel;
    }

    public int getIsDel() {
        return IsDel;
    }

    public void setIsDel(int isDel) {
        IsDel = isDel;
    }

    public String getDescribe() {
        return Describe;
    }

    public void setDescribe(String describe) {
        Describe = describe;
    }

    public int getProductCount() {
        return ProductCount;
    }

    public void setProductCount(int productCount) {
        ProductCount = productCount;
    }

    public int getLoveCount() {
        return LoveCount;
    }

    public void setLoveCount(int loveCount) {
        LoveCount = loveCount;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.ID);
        dest.writeString(this.Name);
        dest.writeString(this.Pic);
        dest.writeString(this.PicNo);
        dest.writeInt(this.Sort);
        dest.writeString(this.Sdesc);
        dest.writeLong(this.TimeStr);
        dest.writeLong(this.TimeEnd);
        dest.writeLong(this.TimeAdd);
        dest.writeLong(this.TimeDel);
        dest.writeInt(this.IsDel);
        dest.writeString(this.Describe);
        dest.writeInt(this.ProductCount);
        dest.writeInt(this.LoveCount);
    }

    public SpecialInfo() {
    }

    protected SpecialInfo(Parcel in) {
        this.ID = in.readInt();
        this.Name = in.readString();
        this.Pic = in.readString();
        this.PicNo = in.readString();
        this.Sort = in.readInt();
        this.Sdesc = in.readString();
        this.TimeStr = in.readLong();
        this.TimeEnd = in.readLong();
        this.TimeAdd = in.readLong();
        this.TimeDel = in.readLong();
        this.IsDel = in.readInt();
        this.Describe = in.readString();
        this.ProductCount = in.readInt();
        this.LoveCount = in.readInt();
    }

    public static final Creator<SpecialInfo> CREATOR = new Creator<SpecialInfo>() {
        @Override
        public SpecialInfo createFromParcel(Parcel source) {
            return new SpecialInfo(source);
        }

        @Override
        public SpecialInfo[] newArray(int size) {
            return new SpecialInfo[size];
        }
    };
}
