package com.example.caowj.newtask.example.bean;


/**
 * Created by Pan S.Q
 * on 2017/5/3.
 * 精选文章-标签
 */

public class ArticleLabel {

    private int tid;
    //标签的内容
    private String wordposition;
    //x轴显示位置占比
    private float x;
    //y轴显示位置占比
    private float y;
    //标签的显示方向0：左 1右
    private int position;

    public int getId() {
        return tid;
    }

    public void setId(int tid) {
        this.tid = tid;
    }

    public String getWordposition() {
        return wordposition;
    }

    public void setWordposition(String wordposition) {
        this.wordposition = wordposition;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

}
