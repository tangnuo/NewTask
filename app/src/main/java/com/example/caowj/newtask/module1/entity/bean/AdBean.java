package com.example.caowj.newtask.module1.entity.bean;

/**
 * package: com.example.caowj.newtask.module1.bean
 * author: Administrator
 * date: 2017/10/12 15:52
 */
public class AdBean {

    private int Id;
    /**
     * 广告标题
     */
    private String Title;
    /**
     * 广告内容
     */
    private String Content;
    /**
     * 广告图片
     */
    private String Images;
    /**
     * 广告连接地址
     */
    private String AdUrl;
    /**
     * 排序
     */
    private int Sort;
    /**以下为备注字段 加入我们有疑问*/
    /**
     * 添加时间
     */
    private long AddTime;
    /**
     * 3：今日广告；4：一口价广告
     */
    private String remark1;
    /**
     * 广告区域 2：今日；3宝贝预展；4一口价
     */
    private String remark2;
    private String remark3;
    /**
     * 是否删除
     */
    private int flat1;
    /**
     * 广告类型： 0站内，1站外
     */
    private int flat2;
    private String remark4;
    private String remark5;
    private String remark6;
    /**
     * 类型编号
     */
    private int flat7;
    /**
     * 拍品Id
     */
    private int flat8;
    private String RegTim1;
    private String RegTim2;


    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getImages() {
        return Images;
    }

    public void setImages(String images) {
        Images = images;
    }

    public String getAdUrl() {
        return AdUrl;
    }

    public void setAdUrl(String adUrl) {
        AdUrl = adUrl;
    }

    public int getSort() {
        return Sort;
    }

    public void setSort(int sort) {
        Sort = sort;
    }

    public long getAddTime() {
        return AddTime;
    }

    public void setAddTime(long addTime) {
        AddTime = addTime;
    }

    public String getRemark1() {
        return remark1;
    }

    public void setRemark1(String remark1) {
        this.remark1 = remark1;
    }

    public String getRemark2() {
        return remark2;
    }

    public void setRemark2(String remark2) {
        this.remark2 = remark2;
    }

    public String getRemark3() {
        return remark3;
    }

    public void setRemark3(String remark3) {
        this.remark3 = remark3;
    }

    public int getFlat1() {
        return flat1;
    }

    public void setFlat1(int flat1) {
        this.flat1 = flat1;
    }

    public int getFlat2() {
        return flat2;
    }

    public void setFlat2(int flat2) {
        this.flat2 = flat2;
    }

    public String getRemark4() {
        return remark4;
    }

    public void setRemark4(String remark4) {
        this.remark4 = remark4;
    }

    public String getRemark5() {
        return remark5;
    }

    public void setRemark5(String remark5) {
        this.remark5 = remark5;
    }

    public String getRemark6() {
        return remark6;
    }

    public void setRemark6(String remark6) {
        this.remark6 = remark6;
    }

    public int getFlat7() {
        return flat7;
    }

    public void setFlat7(int flat7) {
        this.flat7 = flat7;
    }

    public int getFlat8() {
        return flat8;
    }

    public void setFlat8(int flat8) {
        this.flat8 = flat8;
    }

    public String getRegTim1() {
        return RegTim1;
    }

    public void setRegTim1(String regTim1) {
        RegTim1 = regTim1;
    }

    public String getRegTim2() {
        return RegTim2;
    }

    public void setRegTim2(String regTim2) {
        RegTim2 = regTim2;
    }
}
