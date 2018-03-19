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

public class ChoiceArticle {

    private int id;
    //文章标题
    private String title;
    //文章内容
    private String introduce;
    //文章封面
    private String img;
    //文章跳转链接地址
    private String aurl;
    //标签显示位置集合
    private List<ArticleLabel> tags;

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

    public List<ArticleLabel> getTags() {
        return tags;
    }

    public void setTags(List<ArticleLabel> tags) {
        this.tags = tags;
    }
}
