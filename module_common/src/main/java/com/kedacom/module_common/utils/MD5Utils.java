package com.kedacom.module_common.utils;

import android.text.TextUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 自定义MD5方法
 */
public class MD5Utils {
    private static final char HEX_DIGITS[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'A', 'B', 'C', 'D', 'E', 'F'};

    public static String toHexString(byte[] b) {
        StringBuilder sb = new StringBuilder(b.length * 2);
        for (int i = 0; i < b.length; i++) {
            sb.append(HEX_DIGITS[(b[i] & 0xf0) >>> 4]);
            sb.append(HEX_DIGITS[b[i] & 0x0f]);
        }
        return sb.toString();
    }

    static public String getMD5(String fileName) throws IOException, NoSuchAlgorithmException {
        return getMD5(new File(fileName));
    }

    public static String getMD5(File file) throws IOException, NoSuchAlgorithmException {
        if (!file.exists()) throw new FileNotFoundException(file.getPath());

        InputStream fis;
        byte[] buffer = new byte[1024];
        int numRead = 0;
        MessageDigest md5;
        fis = new FileInputStream(file);
        md5 = MessageDigest.getInstance("MD5");
        while ((numRead = fis.read(buffer)) > 0) {
            md5.update(buffer, 0, numRead);
        }
        fis.close();
        return toHexString(md5.digest());
    }


    /**
     * md5加密<br/>
     * http://www.cnblogs.com/whoislcj/p/5885006.html<br/>
     *
     * @param string
     * @return
     */
    public static String md5(String string) {
        if (TextUtils.isEmpty(string)) {
            return "";
        }
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
            byte[] bytes = md5.digest(string.getBytes());
            String result = "";
            for (byte b : bytes) {
                String temp = Integer.toHexString(b & 0xff);
                if (temp.length() == 1) {
                    temp = "0" + temp;
                }
                result += temp;
            }
//            LogUtil.myD(string+"：MD5加密后："+result);
//            LogUtil.myD(string+"：MD5加密--后："+toHexString(string.getBytes()));
//            LogUtil.myD(string+"：MD5加密~~后："+ MD5.getMessageDigest(string.getBytes()));
            return result;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
}