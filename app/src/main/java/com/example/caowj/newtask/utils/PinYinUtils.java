package com.example.caowj.newtask.utils;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * package: com.example.caowj.newtask.utils
 * author: Administrator
 * date: 2017/9/4 17:33
 */
public class PinYinUtils {
    /**
     * 得到 全拼
     *
     * @param src
     * @return
     */
    public static String getPingYin(String src) {
        char[] t1 = null;
        t1 = src.toCharArray();
        String[] t2 = new String[t1.length];
        HanyuPinyinOutputFormat t3 = new HanyuPinyinOutputFormat();
        t3.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        t3.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        t3.setVCharType(HanyuPinyinVCharType.WITH_V);
        String t4 = "";
        int t0 = t1.length;
        try {
            for (int i = 0; i < t0; i++) {
                // 判断是否为汉字字符
                if (java.lang.Character.toString(t1[i]).matches(
                        "[\\u4E00-\\u9FA5]+")) {
                    t2 = PinyinHelper.toHanyuPinyinStringArray(t1[i], t3);
                    t4 += t2[0];
                } else {
                    t4 += java.lang.Character.toString(t1[i]);
                }
            }
            return t4;
        } catch (BadHanyuPinyinOutputFormatCombination e1) {
            e1.printStackTrace();
        }
        return t4;
    }

    /**
     * 得到首字母
     *
     * @param str
     * @return
     */
    public static String getHeadChar(String str) {

        String convert = "";
        char word = str.charAt(0);
        String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(word);
        if (pinyinArray != null) {
            convert += pinyinArray[0].charAt(0);
        } else {
            convert += word;
        }
        return convert.toUpperCase();
    }

    /**
     * 得到中文首字母缩写
     *
     * @param str
     * @return
     */
    public static String getPinYinHeadChar(String str) {

        String convert = "";
        for (int j = 0; j < str.length(); j++) {
            char word = str.charAt(j);
            String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(word);
            if (pinyinArray != null) {
                convert += pinyinArray[0].charAt(0);
            } else {
                convert += word;
            }
        }
        return convert.toUpperCase();
    }

    /**
     * 得到指定汉字的拼音
     * 注意:不应该被频繁调用，它消耗一定内存
     *
     * @param hanzi
     * @return
     */
    public static String getPinYin(String hanzi) {
        String pinyin = "";

        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();//控制转换是否大小写，是否带音标
        format.setCaseType(HanyuPinyinCaseType.UPPERCASE);//大写
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);

        //由于不能直接对多个汉字转换，只能对单个汉字转换
        char[] arr = hanzi.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            if (Character.isWhitespace(arr[i])) {
                continue;//如果是空格，则不处理，进行下次遍历
            }

            //汉字是2个字节存储，肯定大于127，所以大于127就可以当为汉字转换
            if (arr[i] > 127) {
                try {
                    //由于多音字的存在，单 dan shan
                    String[] pinyinArr = PinyinHelper.toHanyuPinyinStringArray(arr[i], format);

                    if (pinyinArr != null) {
                        pinyin += pinyinArr[0];
                    } else {
                        pinyin += arr[i];
                    }
                } catch (BadHanyuPinyinOutputFormatCombination e) {
                    e.printStackTrace();
                    //不是正确的汉字
                    pinyin += arr[i];
                }
            } else {
                //不是汉字，
                pinyin += arr[i];
            }
        }
        return pinyin;
    }
}
