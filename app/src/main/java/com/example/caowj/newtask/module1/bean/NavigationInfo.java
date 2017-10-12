package com.example.caowj.newtask.module1.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 导航栏信息
 *
 * @author caowj
 */
public class NavigationInfo implements Parcelable {

    public static final Parcelable.Creator<NavigationInfo> CREATOR = new Parcelable.Creator<NavigationInfo>() {
        public NavigationInfo createFromParcel(Parcel in) {
            return new NavigationInfo(in);
        }

        public NavigationInfo[] newArray(int size) {
            return new NavigationInfo[size];
        }
    };
    /**
     * id
     */
    private int id;
    /**
     * 导航栏标题
     */
    private String title;
    private boolean checkedStatus = false;// 选中状态

    public NavigationInfo() {
        super();
    }

    public NavigationInfo(int id, String title, boolean checkedStatus) {
        super();
        this.id = id;
        this.title = title;
        this.checkedStatus = checkedStatus;
    }

    public NavigationInfo(Parcel in) {
        id = in.readInt();
        title = in.readString();
    }

    public boolean isCheckedStatus() {
        return checkedStatus;
    }

    public void setCheckedStatus(boolean checkedStatus) {
        this.checkedStatus = checkedStatus;
    }

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

    @Override
    public int describeContents() {

        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(title);
    }
}
