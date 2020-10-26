package com.kedacom.module_learn.bean

/**
 * @Dec ：图片上传返回值
 * @Author : Caowj
 * @Date : 2018/8/28 11:18
 */
class UploadInfo {
    var fileName: String? = null
    var fileSize: String? = null
    var resultCode = 0
    var resultMsg: String? = null
    var updateTime = 0
    var uploadType: String? = null
    var url: String? = null

    override fun toString(): String {
        return "UploadInfo{" +
                "fileName='" + fileName + '\'' +
                ", fileSize='" + fileSize + '\'' +
                ", resultCode=" + resultCode +
                ", resultMsg='" + resultMsg + '\'' +
                ", updateTime=" + updateTime +
                ", uploadType='" + uploadType + '\'' +
                ", url='" + url + '\'' +
                '}'
    }
}