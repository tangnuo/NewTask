package com.example.caowj.newtask.example.bean;

import android.os.Parcel;
import android.os.Parcelable;


/**
 * Created by Pan S.Q
 * on 2017/1/18.
 */

public class Banner implements Parcelable {
    public static final Parcelable.Creator<Banner> CREATOR = new Parcelable.Creator<Banner>() {
        @Override
        public Banner createFromParcel(Parcel source) {
            return new Banner(source);
        }

        @Override
        public Banner[] newArray(int size) {
            return new Banner[size];
        }
    };
    //广告标题
    private String title;
    //广告图片
    private String img;
    //广告链接地址
    private String url;

    public Banner() {
    }

    protected Banner(Parcel in) {
        this.title = in.readString();
        this.img = in.readString();
        this.url = in.readString();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
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
        dest.writeString(this.title);
        dest.writeString(this.img);
        dest.writeString(this.url);
    }
}
