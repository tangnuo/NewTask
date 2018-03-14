package com.example.caowj.newtask.example.bean;


import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pan S.Q
 * on 2017/5/3.
 * 精选-文章
 */

public class ChoiceArticle implements Parcelable {

    private int id;
    //文章标题
    private String title;
    //文章内容
    private String introduce;
    //文章封面
    private String img;
    //文章跳转链接地址
    private String aurl;
    //标签显示位置信息
    private String tags;
    //标签显示位置集合
    private List<ArticleLabel> articleLabelList;

    public List<ArticleLabel> getArticleLabelList() {
        return articleLabelList;
    }

    public void setArticleLabelList(List<ArticleLabel> articleLabelList) {
        this.articleLabelList = articleLabelList;
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

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getAurl() {
        return aurl;
    }

    public void setAurl(String aurl) {
        this.aurl = aurl;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.title);
        dest.writeString(this.introduce);
        dest.writeString(this.img);
        dest.writeString(this.aurl);
        dest.writeString(this.tags);
        dest.writeList(this.articleLabelList);
    }

    public ChoiceArticle() {
    }

    protected ChoiceArticle(Parcel in) {
        this.id = in.readInt();
        this.title = in.readString();
        this.introduce = in.readString();
        this.img = in.readString();
        this.aurl = in.readString();
        this.tags = in.readString();
        this.articleLabelList = new ArrayList<ArticleLabel>();
        in.readList(this.articleLabelList, ArticleLabel.class.getClassLoader());
    }

    public static final Parcelable.Creator<ChoiceArticle> CREATOR = new Parcelable.Creator<ChoiceArticle>() {
        @Override
        public ChoiceArticle createFromParcel(Parcel source) {
            return new ChoiceArticle(source);
        }

        @Override
        public ChoiceArticle[] newArray(int size) {
            return new ChoiceArticle[size];
        }
    };
}
