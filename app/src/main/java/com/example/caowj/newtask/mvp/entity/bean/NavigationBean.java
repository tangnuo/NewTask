package com.example.caowj.newtask.mvp.entity.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.kedacom.utils.StringTool;

/**
 * 导航栏信息
 *
 * @author caowj
 */
public class NavigationBean implements Parcelable {

    public static final Parcelable.Creator<NavigationBean> CREATOR = new Parcelable.Creator<NavigationBean>() {
        public NavigationBean createFromParcel(Parcel in) {
            return new NavigationBean(in);
        }

        public NavigationBean[] newArray(int size) {
            return new NavigationBean[size];
        }
    };
    /**
     * typeId（不是下标）
     */
    private int Id;
    /**
     * 导航栏标题
     */
    private String cateName;
    private boolean checkedStatus = false;// 选中状态

    public NavigationBean() {
        super();
    }

    public NavigationBean(int Id, String title, boolean checkedStatus) {
        super();
        this.Id = Id;
        this.cateName = title;
        this.checkedStatus = checkedStatus;
    }

    public NavigationBean(Parcel in) {
        Id = in.readInt();
        cateName = in.readString();
    }

    public boolean isCheckedStatus() {
        return checkedStatus;
    }

    public void setCheckedStatus(boolean checkedStatus) {
        this.checkedStatus = checkedStatus;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getCateName() {
        return StringTool.convert(cateName);
    }

    public void setCateName(String cateName) {
        this.cateName = cateName;
    }

    @Override
    public int describeContents() {

        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(Id);
        dest.writeString(cateName);
    }
}
