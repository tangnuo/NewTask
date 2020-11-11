package com.kedacom.module_common.bean.main;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @Dec ：
 * @Author : Caowj
 * @Date : 2018/8/22 17:14
 */
public class UserInfo implements Parcelable {
    public static final Creator<UserInfo> CREATOR = new Creator<UserInfo>() {
        @Override
        public UserInfo createFromParcel(Parcel source) {
            return new UserInfo(source);
        }

        @Override
        public UserInfo[] newArray(int size) {
            return new UserInfo[size];
        }
    };
    private int userId;
    private String userName;
    private String address;

    public UserInfo(int userId, String userName, String address) {
        this.userId = userId;
        this.userName = userName;
        this.address = address;
    }

    public UserInfo() {
    }

    protected UserInfo(Parcel in) {
        this.userId = in.readInt();
        this.userName = in.readString();
        this.address = in.readString();
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.userId);
        dest.writeString(this.userName);
        dest.writeString(this.address);
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
