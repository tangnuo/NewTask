package com.kedacom.module_common.customview.bean;/**
 * @author Dick.Pan
 * @date 2017/10/9
 */

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 滚动通知
 *
 * @author Dick.Pan
 * @Date 2017/10/9$
 */
public class ScrollNotification implements Parcelable {
    public static final Creator<ScrollNotification> CREATOR = new Creator<ScrollNotification>() {
        @Override
        public ScrollNotification createFromParcel(Parcel source) {
            return new ScrollNotification(source);
        }

        @Override
        public ScrollNotification[] newArray(int size) {
            return new ScrollNotification[size];
        }
    };
    private Integer id;
    private String title;

    public ScrollNotification() {
    }

    protected ScrollNotification(Parcel in) {
        this.id = in.readInt();
        this.title = in.readString();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.title);
    }
}
