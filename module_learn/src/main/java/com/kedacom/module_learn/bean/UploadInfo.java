package com.kedacom.module_learn.bean;

/**
 * @Dec ：图片上传返回值
 * @Author : Caowj
 * @Date : 2018/8/28 11:18
 */
public class UploadInfo {

    private String fileName;

    private String fileSize;

    private int resultCode;

    private String resultMsg;

    private int updateTime;

    private String uploadType;

    private String url;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }

    public int getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(int updateTime) {
        this.updateTime = updateTime;
    }

    public String getUploadType() {
        return uploadType;
    }

    public void setUploadType(String uploadType) {
        this.uploadType = uploadType;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    @Override
    public String toString() {
        return "UploadInfo{" +
                "fileName='" + fileName + '\'' +
                ", fileSize='" + fileSize + '\'' +
                ", resultCode=" + resultCode +
                ", resultMsg='" + resultMsg + '\'' +
                ", updateTime=" + updateTime +
                ", uploadType='" + uploadType + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
