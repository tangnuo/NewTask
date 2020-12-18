package com.kedacom.module_learn.activity

import android.os.Bundle
import android.os.Environment
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.kedacom.module_learn.R
import com.kedacom.module_learn.utils.FileUtil
import java.io.ByteArrayOutputStream
import java.io.InputStream


/**
 * android 10，文件存储
 * https://blog.csdn.net/zhendong_hu/article/details/104921985
 * https://blog.csdn.net/yehui928186846/article/details/101706238
 *
 * 1、沙盒目录
 * 2、公共目录（多媒体）
 */
class ScopedStorageActivity2 : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scoped_storage2)

        findViewById<Button>(R.id.btn1).setOnClickListener(this)
        findViewById<Button>(R.id.btn2).setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn1 -> {
                val am = resources.assets
                val image = readFile(am.open("yuner.png"))
                FileUtil.saveImage2SandBox(this, "test_yuner.png", image, Environment.DIRECTORY_PICTURES)
            }
            R.id.btn2 -> {
                FileUtil.loadImageFromSandBox(this, "test_yuner.png", Environment.DIRECTORY_PICTURES)
            }
        }
    }

    @Throws(Exception::class)
    fun readFile(inStream: InputStream): ByteArray {
//        val inStream = FileInputStream(path)
        val buffer = ByteArray(1024)
        var len = -1
        val outStream = ByteArrayOutputStream()
        while (inStream.read(buffer).also { len = it } != -1) {
            outStream.write(buffer, 0, len)
        }
        val data: ByteArray = outStream.toByteArray()
        outStream.close()
        inStream.close()
        return data
    }

}