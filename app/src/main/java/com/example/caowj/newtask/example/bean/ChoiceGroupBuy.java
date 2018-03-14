package com.example.caowj.newtask.example.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Pan S.Q
 * on 2017/5/3.
 * 精选团购
 */

public class ChoiceGroupBuy implements Parcelable {
    //团购商品Id
    private int id;
    //团购商品名称
    private String title;
    //团购商品封面
    private String picmain;
    //团购商品网页跳转地址
    private String url;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPicmain() {
        return picmain;
    }

    public void setPicmain(String picmain) {
        this.picmain = picmain;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.title);
        dest.writeString(this.picmain);
        dest.writeString(this.url);
    }

    public ChoiceGroupBuy() {
    }

    protected ChoiceGroupBuy(Parcel in) {
        this.id = in.readInt();
        this.title = in.readString();
        this.picmain = in.readString();
        this.url = in.readString();
    }

    public static final Parcelable.Creator<ChoiceGroupBuy> CREATOR = new Parcelable.Creator<ChoiceGroupBuy>() {
        @Override
        public ChoiceGroupBuy createFromParcel(Parcel source) {
            return new ChoiceGroupBuy(source);
        }

        @Override
        public ChoiceGroupBuy[] newArray(int size) {
            return new ChoiceGroupBuy[size];
        }
    };
}
