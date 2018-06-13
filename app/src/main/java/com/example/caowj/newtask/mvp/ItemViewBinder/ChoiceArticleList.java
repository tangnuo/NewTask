package com.example.caowj.newtask.mvp.ItemViewBinder;

import com.example.caowj.newtask.example.bean.ChoiceArticle;
import com.example.caowj.newtask.mvp.entity.CommonBean;

import java.util.List;

/**
 * @Author : caowj
 * @Date : 2018/3/16
 */
public class ChoiceArticleList extends CommonBean {

    //标签显示位置集合
    private List<ChoiceArticle> data;

    public List<ChoiceArticle> getData() {
        return data;
    }

    public void setData(List<ChoiceArticle> data) {
        this.data = data;
    }
}