package com.kedacom.module_learn.utils

import android.content.Context
import android.os.Environment
import android.text.TextUtils
import android.util.Log
import java.io.*

/**
 * <pre>
 *     作者：Caowj
 *     邮箱：caoweijian@kedacom.com
 *     日期：2020/11/18 14:17
 * </pre>
 */

object FileUtil {

    /**
     * storage location: sdcard/Android/data/packagename/files/[Pictures、Movies。。]/file.jpg
     *
     * @param context
     * @param fileName 文件名(*.jpg)
     * @param imageArray 图片
     * @param environmentType
     */
    fun saveImage2SandBox(context: Context, fileName: String, imageArray: ByteArray, environmentType: String) {

        if (TextUtils.isEmpty(fileName) || imageArray.isEmpty()) {
            Log.e("caowj", "saveImage2SandBox: fileName is null or image is null!")
            return
        }

        //如果没有指明目录，默认是Pictures
        val standardDirectory: File? = if (!TextUtils.isEmpty(environmentType)) {
            context.getExternalFilesDir(environmentType)
        } else {
            context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        }

        //存储路径:/storage/emulated/0/Android/data/com.kedacom.module_learn/files/Pictures/
        Log.d("caowj", "存储路径:$standardDirectory")

        //创建文件夹
        val imageFileDirctory = File("$standardDirectory")
        if (!imageFileDirctory.exists()) {
            if (!imageFileDirctory.mkdir()) {
                Log.e("caowj", "saveImage2SandBox: mkdir failed! Directory: $imageFileDirctory")
                return
            }
        }

        //保存文件
        try {
            // /storage/emulated/0/Android/data/com.kedacom.module_learn/files/Pictures/test_yuner.png
            val imageFile = File("$imageFileDirctory/$fileName")
            val fileOutputStream = FileOutputStream(imageFile)
            fileOutputStream.write(imageArray)
            fileOutputStream.flush()
            fileOutputStream.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }


    /** storage location: sdcard/Android/data/packagename
     *
     * @param context
     * @param fileName
     * @param environmentType
     * @return
     */
    fun loadImageFromSandBox(context: Context, fileName: String, environmentType: String): ByteArray? {
        if (TextUtils.isEmpty(fileName)) {
            Log.e("caowj", "loadImageFromSandBox: fileName is null")
            return null
        }
        val type: String = if (!TextUtils.isEmpty(environmentType)) {
            environmentType
        } else {
            Environment.DIRECTORY_PICTURES
        }
        val direction: File? = context.getExternalFilesDir(type)

        val files = direction?.listFiles()
        if (files != null) {
            for (file in files) {
                val name = file.name // 此处的文件名不带路径
                if (file.isFile && fileName == name) {
                    try {
                        val inputStream: InputStream = FileInputStream(file)
                        val image = ByteArray(inputStream.available())
                        inputStream.read(image)
                        inputStream.close()
                        Log.d("caowj", "图片读取完成")
                        return image
                    } catch (e: FileNotFoundException) {
                        e.printStackTrace()
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                }
            }
        }
        return null
    }

}