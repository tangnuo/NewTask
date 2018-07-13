package com.kedacom.utils.DataList;

import android.Manifest;

import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.Manifest.permission.ADD_VOICEMAIL;
import static android.Manifest.permission.ANSWER_PHONE_CALLS;
import static android.Manifest.permission.BODY_SENSORS;
import static android.Manifest.permission.CALL_PHONE;
import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.GET_ACCOUNTS;
import static android.Manifest.permission.PROCESS_OUTGOING_CALLS;
import static android.Manifest.permission.READ_CALENDAR;
import static android.Manifest.permission.READ_CALL_LOG;
import static android.Manifest.permission.READ_CONTACTS;
import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.READ_PHONE_NUMBERS;
import static android.Manifest.permission.READ_PHONE_STATE;
import static android.Manifest.permission.READ_SMS;
import static android.Manifest.permission.RECEIVE_MMS;
import static android.Manifest.permission.RECEIVE_SMS;
import static android.Manifest.permission.RECEIVE_WAP_PUSH;
import static android.Manifest.permission.RECORD_AUDIO;
import static android.Manifest.permission.SEND_SMS;
import static android.Manifest.permission.USE_SIP;
import static android.Manifest.permission.WRITE_CALENDAR;
import static android.Manifest.permission.WRITE_CALL_LOG;
import static android.Manifest.permission.WRITE_CONTACTS;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

/**
 * @Dec ：数据资源合集
 * @Author : Caowj
 * @Date : 2018/4/28 15:47
 */

public class DataList {


    /**
     * 通过组id，获取想对应的危险权限
     * <p>
     * https://developer.android.google.cn/guide/topics/permissions/overview#permission-groups
     *
     * @param groupId <p>
     *                1:CALENDAR
     *                <p>
     *                2:CAMERA
     *                <p>
     *                3:CONTACTS
     *                <p>
     *                4:LOCATION
     *                <p>
     *                5:MICROPHONE
     *                <p>
     *                6:PHONE
     *                <p>
     *                7:SENSORS
     *                <p>
     *                8:SMS
     *                <p>
     *                9:STORAGE
     * @return
     */
    public static String[] getDangerousPermissionsByGroup(int groupId) {
        String[] permissionArray = new String[0];
        switch (groupId) {
            case 1:
                permissionArray = new String[]{READ_CALENDAR, WRITE_CALENDAR};
                break;
            case 2:
                permissionArray = new String[]{CAMERA};
                break;
            case 3:
                permissionArray = new String[]{READ_CONTACTS, WRITE_CONTACTS, GET_ACCOUNTS};
                break;
            case 4:
                permissionArray = new String[]{ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION};
                break;
            case 5:
                permissionArray = new String[]{RECORD_AUDIO};
                break;
            case 6:
                permissionArray = new String[]{READ_PHONE_STATE, READ_PHONE_NUMBERS, CALL_PHONE,
                        ANSWER_PHONE_CALLS, READ_CALL_LOG, WRITE_CALL_LOG, ADD_VOICEMAIL,
                        USE_SIP, PROCESS_OUTGOING_CALLS};
                break;
            case 7:
                permissionArray = new String[]{BODY_SENSORS};
                break;
            case 8:
                permissionArray = new String[]{SEND_SMS, RECEIVE_SMS, READ_SMS,
                        RECEIVE_WAP_PUSH, RECEIVE_MMS};
                break;
            case 9:
                permissionArray = new String[]{READ_EXTERNAL_STORAGE,
                        WRITE_EXTERNAL_STORAGE};
                break;
        }
        return permissionArray;
    }

    /**
     * 获取指定数目的图片
     *
     * @param count
     * @return
     */
    public static List<String> getImageUrlListByCount(int count) {
        List<String> imgList = getImageUrlList();
        if (count < imgList.size()) {
            List<String> newImgList = new ArrayList<>();
            for (int i = 0; i < count; i++) {
                newImgList.add(imgList.get(i));
            }
            return newImgList;
        } else {
            return imgList;
        }
    }


    /**
     * 获取图片数据集合
     *
     * @return
     */
    public static List<String> getImageUrlList() {
        List<String> imgList = new ArrayList<>();
        imgList.add("https://farm3.staticflickr.com/2891/33833709921_bec1f88a9b_k.jpg");
        imgList.add("http://img.my.csdn.net/uploads/201309/01/1378037235_3453.jpg");
        imgList.add("http://img.my.csdn.net/uploads/201309/01/1378037235_7476.jpg");
        imgList.add("http://img.my.csdn.net/uploads/201309/01/1378037235_9280.jpg");
        imgList.add("http://img.my.csdn.net/uploads/201309/01/1378037234_3539.jpg");
        imgList.add("http://img.my.csdn.net/uploads/201309/01/1378037234_6318.jpg");
        imgList.add("http://img.my.csdn.net/uploads/201309/01/1378037194_2965.jpg");
        imgList.add("http://img.my.csdn.net/uploads/201309/01/1378037193_1687.jpg");
        imgList.add("http://img.my.csdn.net/uploads/201309/01/1378037193_1286.jpg");
        imgList.add("http://img.my.csdn.net/uploads/201309/01/1378037192_8379.jpg");
        imgList.add("http://img.my.csdn.net/uploads/201309/01/1378037178_9374.jpg");
        imgList.add("http://img.my.csdn.net/uploads/201309/01/1378037177_1254.jpg");
        imgList.add("http://img.my.csdn.net/uploads/201309/01/1378037177_6203.jpg");
        imgList.add("http://img.my.csdn.net/uploads/201309/01/1378037152_6352.jpg");
        imgList.add("http://img.my.csdn.net/uploads/201309/01/1378037151_9565.jpg");
        imgList.add("http://img.my.csdn.net/uploads/201309/01/1378037151_7904.jpg");
        imgList.add("http://img.my.csdn.net/uploads/201309/01/1378037148_7104.jpg");
        imgList.add("http://img.my.csdn.net/uploads/201309/01/1378037129_8825.jpg");
        imgList.add("http://img.my.csdn.net/uploads/201309/01/1378037128_5291.jpg");
        imgList.add("http://img.my.csdn.net/uploads/201309/01/1378037128_3531.jpg");
        imgList.add("http://img.my.csdn.net/uploads/201309/01/1378037127_1085.jpg");
        imgList.add("http://img.my.csdn.net/uploads/201309/01/1378037095_7515.jpg");
        imgList.add("http://img.my.csdn.net/uploads/201309/01/1378037094_8001.jpg");
        imgList.add("http://img.my.csdn.net/uploads/201309/01/1378037093_7168.jpg");
        imgList.add("http://img.my.csdn.net/uploads/201309/01/1378037091_4950.jpg");
        imgList.add("http://img.my.csdn.net/uploads/201308/31/1377949643_6410.jpg");
        imgList.add("http://img.my.csdn.net/uploads/201308/31/1377949642_6939.jpg");
        imgList.add("http://img.my.csdn.net/uploads/201308/31/1377949630_4505.jpg");
        imgList.add("http://img.my.csdn.net/uploads/201308/31/1377949630_4593.jpg");
        imgList.add("http://img.my.csdn.net/uploads/201308/31/1377949629_7309.jpg");
        imgList.add("http://img.my.csdn.net/uploads/201308/31/1377949629_8247.jpg");
        imgList.add("http://img.my.csdn.net/uploads/201308/31/1377949615_1986.jpg");
        imgList.add("http://img.my.csdn.net/uploads/201308/31/1377949614_8482.jpg");
        imgList.add("http://img.my.csdn.net/uploads/201308/31/1377949614_3743.jpg");
        imgList.add("http://img.my.csdn.net/uploads/201308/31/1377949614_4199.jpg");
        imgList.add("http://img.my.csdn.net/uploads/201308/31/1377949599_3416.jpg");
        imgList.add("http://img.my.csdn.net/uploads/201308/31/1377949599_5269.jpg");
        imgList.add("http://img.my.csdn.net/uploads/201308/31/1377949598_7858.jpg");
        imgList.add("http://img.my.csdn.net/uploads/201308/31/1377949598_9982.jpg");
        imgList.add("http://img.my.csdn.net/uploads/201308/31/1377949578_2770.jpg");
        imgList.add("http://img.my.csdn.net/uploads/201308/31/1377949578_8744.jpg");
        imgList.add("http://img.my.csdn.net/uploads/201308/31/1377949577_5210.jpg");
        imgList.add("http://img.my.csdn.net/uploads/201308/31/1377949577_1998.jpg");
        imgList.add("http://img.my.csdn.net/uploads/201308/31/1377949482_8813.jpg");
        imgList.add("http://img.my.csdn.net/uploads/201308/31/1377949481_6577.jpg");
        imgList.add("http://img.my.csdn.net/uploads/201308/31/1377949480_4490.jpg");
        imgList.add("http://img.my.csdn.net/uploads/201308/31/1377949455_6792.jpg");
        imgList.add("http://img.my.csdn.net/uploads/201308/31/1377949455_6345.jpg");
        imgList.add("http://img.my.csdn.net/uploads/201308/31/1377949442_4553.jpg");
        imgList.add("http://img.my.csdn.net/uploads/201308/31/1377949441_8987.jpg");
        imgList.add("http://img.my.csdn.net/uploads/201308/31/1377949441_5454.jpg");
        imgList.add("http://img.my.csdn.net/uploads/201308/31/1377949454_6367.jpg");
        imgList.add("http://img.my.csdn.net/uploads/201308/31/1377949442_4562.jpg");
        imgList.add("http://img.my.csdn.net/uploads/201407/26/1406383299_1976.jpg");
        imgList.add("http://img.my.csdn.net/uploads/201407/26/1406383291_6518.jpg");
        imgList.add("http://img.my.csdn.net/uploads/201407/26/1406383291_8239.jpg");
        imgList.add("http://img.my.csdn.net/uploads/201407/26/1406383290_9329.jpg");
        imgList.add("http://img.my.csdn.net/uploads/201407/26/1406383290_1042.jpg");
        imgList.add("http://img.my.csdn.net/uploads/201407/26/1406383275_3977.jpg");
        imgList.add("http://img.my.csdn.net/uploads/201407/26/1406383265_8550.jpg");
        imgList.add("http://img.my.csdn.net/uploads/201407/26/1406383264_3954.jpg");
        imgList.add("http://img.my.csdn.net/uploads/201407/26/1406383264_4787.jpg");
        imgList.add("http://img.my.csdn.net/uploads/201407/26/1406383264_8243.jpg");
        imgList.add("http://img.my.csdn.net/uploads/201407/26/1406383248_3693.jpg");
        imgList.add("http://img.my.csdn.net/uploads/201407/26/1406383243_5120.jpg");
        imgList.add("http://img.my.csdn.net/uploads/201407/26/1406383242_3127.jpg");
        imgList.add("http://img.my.csdn.net/uploads/201407/26/1406383242_9576.jpg");
        imgList.add("http://img.my.csdn.net/uploads/201407/26/1406383242_1721.jpg");
        imgList.add("http://img.my.csdn.net/uploads/201407/26/1406383219_5806.jpg");
        imgList.add("http://img.my.csdn.net/uploads/201407/26/1406383214_7794.jpg");
        imgList.add("http://img.my.csdn.net/uploads/201407/26/1406383213_4418.jpg");
        imgList.add("http://img.my.csdn.net/uploads/201407/26/1406383213_3557.jpg");
        imgList.add("http://img.my.csdn.net/uploads/201407/26/1406383210_8779.jpg");
        imgList.add("http://img.my.csdn.net/uploads/201407/26/1406383172_4577.jpg");
        imgList.add("http://img.my.csdn.net/uploads/201407/26/1406383166_3407.jpg");
        imgList.add("http://img.my.csdn.net/uploads/201407/26/1406383166_2224.jpg");
        imgList.add("http://img.my.csdn.net/uploads/201407/26/1406383166_7301.jpg");
        imgList.add("http://img.my.csdn.net/uploads/201407/26/1406383165_7197.jpg");
        imgList.add("http://img.my.csdn.net/uploads/201407/26/1406383150_8410.jpg");
        imgList.add("http://img.my.csdn.net/uploads/201407/26/1406383131_3736.jpg");
        imgList.add("http://img.my.csdn.net/uploads/201407/26/1406383130_5094.jpg");
        imgList.add("http://img.my.csdn.net/uploads/201407/26/1406383130_7393.jpg");
        imgList.add("http://img.my.csdn.net/uploads/201407/26/1406383129_8813.jpg");
        imgList.add("http://img.my.csdn.net/uploads/201407/26/1406383100_3554.jpg");
        imgList.add("http://img.my.csdn.net/uploads/201407/26/1406383093_7894.jpg");
        imgList.add("http://img.my.csdn.net/uploads/201407/26/1406383092_2432.jpg");
        imgList.add("http://img.my.csdn.net/uploads/201407/26/1406383092_3071.jpg");
        imgList.add("http://img.my.csdn.net/uploads/201407/26/1406383091_3119.jpg");
        imgList.add("http://img.my.csdn.net/uploads/201407/26/1406383059_6589.jpg");
        imgList.add("http://img.my.csdn.net/uploads/201407/26/1406383059_8814.jpg");
        imgList.add("http://img.my.csdn.net/uploads/201407/26/1406383059_2237.jpg");
        imgList.add("http://img.my.csdn.net/uploads/201407/26/1406383058_4330.jpg");
        imgList.add("http://img.my.csdn.net/uploads/201407/26/1406383038_3602.jpg");
        imgList.add("http://img.my.csdn.net/uploads/201407/26/1406382942_3079.jpg");
        imgList.add("http://img.my.csdn.net/uploads/201407/26/1406382942_8125.jpg");
        imgList.add("http://img.my.csdn.net/uploads/201407/26/1406382942_4881.jpg");
        imgList.add("http://img.my.csdn.net/uploads/201407/26/1406382941_4559.jpg");
        imgList.add("http://img.my.csdn.net/uploads/201407/26/1406382941_3845.jpg");
        imgList.add("http://img.my.csdn.net/uploads/201407/26/1406382924_8955.jpg");
        imgList.add("http://img.my.csdn.net/uploads/201407/26/1406382923_2141.jpg");
        imgList.add("http://img.my.csdn.net/uploads/201407/26/1406382923_8437.jpg");
        imgList.add("http://img.my.csdn.net/uploads/201407/26/1406382922_6166.jpg");
        imgList.add("http://img.my.csdn.net/uploads/201407/26/1406382922_4843.jpg");
        imgList.add("http://img.my.csdn.net/uploads/201407/26/1406382905_5804.jpg");
        imgList.add("http://img.my.csdn.net/uploads/201407/26/1406382904_3362.jpg");
        imgList.add("http://img.my.csdn.net/uploads/201407/26/1406382904_2312.jpg");
        imgList.add("http://img.my.csdn.net/uploads/201407/26/1406382904_4960.jpg");
        imgList.add("http://img.my.csdn.net/uploads/201407/26/1406382900_2418.jpg");
        imgList.add("http://img.my.csdn.net/uploads/201407/26/1406382881_4490.jpg");
        imgList.add("http://img.my.csdn.net/uploads/201407/26/1406382881_5935.jpg");
        imgList.add("http://img.my.csdn.net/uploads/201407/26/1406382880_3865.jpg");
        imgList.add("http://img.my.csdn.net/uploads/201407/26/1406382880_4662.jpg");
        imgList.add("http://img.my.csdn.net/uploads/201407/26/1406382879_2553.jpg");
        imgList.add("http://img.my.csdn.net/uploads/201407/26/1406382862_5375.jpg");
        imgList.add("http://img.my.csdn.net/uploads/201407/26/1406382862_1748.jpg");
        imgList.add("http://img.my.csdn.net/uploads/201407/26/1406382861_7618.jpg");
        imgList.add("http://img.my.csdn.net/uploads/201407/26/1406382861_8606.jpg");
        imgList.add("http://img.my.csdn.net/uploads/201407/26/1406382861_8949.jpg");
        imgList.add("http://img.my.csdn.net/uploads/201407/26/1406382841_9821.jpg");
        imgList.add("http://img.my.csdn.net/uploads/201407/26/1406382840_6603.jpg");
        imgList.add("http://img.my.csdn.net/uploads/201407/26/1406382840_2405.jpg");
        imgList.add("http://img.my.csdn.net/uploads/201407/26/1406382840_6354.jpg");
        imgList.add("http://img.my.csdn.net/uploads/201407/26/1406382839_5779.jpg");
        imgList.add("http://img.my.csdn.net/uploads/201407/26/1406382810_7578.jpg");
        imgList.add("http://img.my.csdn.net/uploads/201407/26/1406382810_2436.jpg");
        imgList.add("http://img.my.csdn.net/uploads/201407/26/1406382809_3883.jpg");
        imgList.add("http://img.my.csdn.net/uploads/201407/26/1406382809_6269.jpg");
        imgList.add("http://img.my.csdn.net/uploads/201407/26/1406382808_4179.jpg");
        imgList.add("http://img.my.csdn.net/uploads/201407/26/1406382790_8326.jpg");
        imgList.add("http://img.my.csdn.net/uploads/201407/26/1406382789_7174.jpg");
        imgList.add("http://img.my.csdn.net/uploads/201407/26/1406382789_5170.jpg");
        imgList.add("http://img.my.csdn.net/uploads/201407/26/1406382789_4118.jpg");
        imgList.add("http://img.my.csdn.net/uploads/201407/26/1406382788_9532.jpg");
        imgList.add("http://img.my.csdn.net/uploads/201407/26/1406382767_3184.jpg");
        imgList.add("http://img.my.csdn.net/uploads/201407/26/1406382767_4772.jpg");
        imgList.add("http://img.my.csdn.net/uploads/201407/26/1406382766_4924.jpg");
        imgList.add("http://img.my.csdn.net/uploads/201407/26/1406382766_5762.jpg");
        imgList.add("http://img.my.csdn.net/uploads/201407/26/1406382765_7341.jpg");

        return imgList;
    }

    /**
     * 获取音频数据集合
     *
     * @return
     */
    public static List<String> getAudioList() {
        List<String> audioList = new ArrayList<>();
        audioList.add("http://mp4.111ttt.cn/2017/xc/08/ANB.mp4?#.mp3");
        audioList.add("http://sc1.111ttt.cn:8282/2017/1/11m/11/304112003137.m4a?tflag=1516348800&pin=1d3e05b2704f5dca0b6ff2e5d040bf2c#.mp3");
        audioList.add("http://sc1.111ttt.cn:8282/2017/1/11m/11/304112004168.m4a?tflag=1516348800&pin=1d3e05b2704f5dca0b6ff2e5d040bf2c#.mp3");
        audioList.add("http://sc1.111ttt.cn:8282/2017/1/11m/11/304112002593.m4a?tflag=1516348800&pin=1d3e05b2704f5dca0b6ff2e5d040bf2c#.mp3");
        audioList.add("http://sc1.111ttt.cn:8282/2017/1/11m/11/304112002493.m4a?tflag=1516348800&pin=1d3e05b2704f5dca0b6ff2e5d040bf2c#.mp3");
        audioList.add("http://mp4.111ttt.cn/2017/xc/08/ANB.mp4?#.mp3");
        audioList.add("http://sc1.111ttt.cn:8282/2017/1/11m/11/304112003137.m4a?tflag=1516348800&pin=1d3e05b2704f5dca0b6ff2e5d040bf2c#.mp3");
        audioList.add("http://sc1.111ttt.cn:8282/2017/1/11m/11/304112004168.m4a?tflag=1516348800&pin=1d3e05b2704f5dca0b6ff2e5d040bf2c#.mp3");
        audioList.add("http://sc1.111ttt.cn:8282/2017/1/11m/11/304112002593.m4a?tflag=1516348800&pin=1d3e05b2704f5dca0b6ff2e5d040bf2c#.mp3");
        audioList.add("http://sc1.111ttt.cn:8282/2017/1/11m/11/304112002493.m4a?tflag=1516348800&pin=1d3e05b2704f5dca0b6ff2e5d040bf2c#.mp3");
        audioList.add("http://mp4.111ttt.cn/2017/xc/08/ANB.mp4?#.mp3");
        audioList.add("http://sc1.111ttt.cn:8282/2017/1/11m/11/304112003137.m4a?tflag=1516348800&pin=1d3e05b2704f5dca0b6ff2e5d040bf2c#.mp3");
        audioList.add("http://sc1.111ttt.cn:8282/2017/1/11m/11/304112004168.m4a?tflag=1516348800&pin=1d3e05b2704f5dca0b6ff2e5d040bf2c#.mp3");
        audioList.add("http://sc1.111ttt.cn:8282/2017/1/11m/11/304112002593.m4a?tflag=1516348800&pin=1d3e05b2704f5dca0b6ff2e5d040bf2c#.mp3");
        audioList.add("http://sc1.111ttt.cn:8282/2017/1/11m/11/304112002493.m4a?tflag=1516348800&pin=1d3e05b2704f5dca0b6ff2e5d040bf2c#.mp3");
        audioList.add("http://mp4.111ttt.cn/2017/xc/08/ANB.mp4?#.mp3");
        audioList.add("http://sc1.111ttt.cn:8282/2017/1/11m/11/304112003137.m4a?tflag=1516348800&pin=1d3e05b2704f5dca0b6ff2e5d040bf2c#.mp3");
        audioList.add("http://sc1.111ttt.cn:8282/2017/1/11m/11/304112004168.m4a?tflag=1516348800&pin=1d3e05b2704f5dca0b6ff2e5d040bf2c#.mp3");
        audioList.add("http://sc1.111ttt.cn:8282/2017/1/11m/11/304112002593.m4a?tflag=1516348800&pin=1d3e05b2704f5dca0b6ff2e5d040bf2c#.mp3");
        audioList.add("http://sc1.111ttt.cn:8282/2017/1/11m/11/304112002493.m4a?tflag=1516348800&pin=1d3e05b2704f5dca0b6ff2e5d040bf2c#.mp3");
        audioList.add("http://mp4.111ttt.cn/2017/xc/08/ANB.mp4?#.mp3");
        audioList.add("http://sc1.111ttt.cn:8282/2017/1/11m/11/304112003137.m4a?tflag=1516348800&pin=1d3e05b2704f5dca0b6ff2e5d040bf2c#.mp3");
        audioList.add("http://sc1.111ttt.cn:8282/2017/1/11m/11/304112004168.m4a?tflag=1516348800&pin=1d3e05b2704f5dca0b6ff2e5d040bf2c#.mp3");
        audioList.add("http://sc1.111ttt.cn:8282/2017/1/11m/11/304112002593.m4a?tflag=1516348800&pin=1d3e05b2704f5dca0b6ff2e5d040bf2c#.mp3");
        audioList.add("http://sc1.111ttt.cn:8282/2017/1/11m/11/304112002493.m4a?tflag=1516348800&pin=1d3e05b2704f5dca0b6ff2e5d040bf2c#.mp3");
        audioList.add("http://mp4.111ttt.cn/2017/xc/08/ANB.mp4?#.mp3");
        audioList.add("http://sc1.111ttt.cn:8282/2017/1/11m/11/304112003137.m4a?tflag=1516348800&pin=1d3e05b2704f5dca0b6ff2e5d040bf2c#.mp3");
        audioList.add("http://sc1.111ttt.cn:8282/2017/1/11m/11/304112004168.m4a?tflag=1516348800&pin=1d3e05b2704f5dca0b6ff2e5d040bf2c#.mp3");
        audioList.add("http://sc1.111ttt.cn:8282/2017/1/11m/11/304112002593.m4a?tflag=1516348800&pin=1d3e05b2704f5dca0b6ff2e5d040bf2c#.mp3");
        audioList.add("http://sc1.111ttt.cn:8282/2017/1/11m/11/304112002493.m4a?tflag=1516348800&pin=1d3e05b2704f5dca0b6ff2e5d040bf2c#.mp3");
        return audioList;
    }

    /**
     * 获取图片数组
     *
     * @return
     */
    public static String[] getImageArray() {
        String[] imageThumbUrls = new String[]{
                "https://farm3.staticflickr.com/2891/33833709921_bec1f88a9b_k.jpg",
                "https://farm4.staticflickr.com/3928/33939620626_44365c6e2b_k.jpg",
                "http://img.my.csdn.net/uploads/201309/01/1378037235_3453.jpg",
                "http://img.my.csdn.net/uploads/201309/01/1378037235_7476.jpg",
                "http://img.my.csdn.net/uploads/201309/01/1378037235_9280.jpg",
                "http://img.my.csdn.net/uploads/201309/01/1378037234_3539.jpg",
                "http://img.my.csdn.net/uploads/201309/01/1378037234_6318.jpg",
                "http://img.my.csdn.net/uploads/201309/01/1378037194_2965.jpg",
                "http://img.my.csdn.net/uploads/201309/01/1378037193_1687.jpg",
                "http://img.my.csdn.net/uploads/201309/01/1378037193_1286.jpg",
                "http://img.my.csdn.net/uploads/201309/01/1378037192_8379.jpg",
                "http://img.my.csdn.net/uploads/201309/01/1378037178_9374.jpg",
                "http://img.my.csdn.net/uploads/201309/01/1378037177_1254.jpg",
                "http://img.my.csdn.net/uploads/201309/01/1378037177_6203.jpg",
                "http://img.my.csdn.net/uploads/201309/01/1378037152_6352.jpg",
                "http://img.my.csdn.net/uploads/201309/01/1378037151_9565.jpg",
                "http://img.my.csdn.net/uploads/201309/01/1378037151_7904.jpg",
                "http://img.my.csdn.net/uploads/201309/01/1378037148_7104.jpg",
                "http://img.my.csdn.net/uploads/201309/01/1378037129_8825.jpg",
                "http://img.my.csdn.net/uploads/201309/01/1378037128_5291.jpg",
                "http://img.my.csdn.net/uploads/201309/01/1378037128_3531.jpg",
                "http://img.my.csdn.net/uploads/201309/01/1378037127_1085.jpg",
                "http://img.my.csdn.net/uploads/201309/01/1378037095_7515.jpg",
                "http://img.my.csdn.net/uploads/201309/01/1378037094_8001.jpg",
                "http://img.my.csdn.net/uploads/201309/01/1378037093_7168.jpg",
                "http://img.my.csdn.net/uploads/201309/01/1378037091_4950.jpg",
                "http://img.my.csdn.net/uploads/201308/31/1377949643_6410.jpg",
                "http://img.my.csdn.net/uploads/201308/31/1377949642_6939.jpg",
                "http://img.my.csdn.net/uploads/201308/31/1377949630_4505.jpg",
                "http://img.my.csdn.net/uploads/201308/31/1377949630_4593.jpg",
                "http://img.my.csdn.net/uploads/201308/31/1377949629_7309.jpg",
                "http://img.my.csdn.net/uploads/201308/31/1377949629_8247.jpg",
                "http://img.my.csdn.net/uploads/201308/31/1377949615_1986.jpg",
                "http://img.my.csdn.net/uploads/201308/31/1377949614_8482.jpg",
                "http://img.my.csdn.net/uploads/201308/31/1377949614_3743.jpg",
                "http://img.my.csdn.net/uploads/201308/31/1377949614_4199.jpg",
                "http://img.my.csdn.net/uploads/201308/31/1377949599_3416.jpg",
                "http://img.my.csdn.net/uploads/201308/31/1377949599_5269.jpg",
                "http://img.my.csdn.net/uploads/201308/31/1377949598_7858.jpg",
                "http://img.my.csdn.net/uploads/201308/31/1377949598_9982.jpg",
                "http://img.my.csdn.net/uploads/201308/31/1377949578_2770.jpg",
                "http://img.my.csdn.net/uploads/201308/31/1377949578_8744.jpg",
                "http://img.my.csdn.net/uploads/201308/31/1377949577_5210.jpg",
                "http://img.my.csdn.net/uploads/201308/31/1377949577_1998.jpg",
                "http://img.my.csdn.net/uploads/201308/31/1377949482_8813.jpg",
                "http://img.my.csdn.net/uploads/201308/31/1377949481_6577.jpg",
                "http://img.my.csdn.net/uploads/201308/31/1377949480_4490.jpg",
                "http://img.my.csdn.net/uploads/201308/31/1377949455_6792.jpg",
                "http://img.my.csdn.net/uploads/201308/31/1377949455_6345.jpg",
                "http://img.my.csdn.net/uploads/201308/31/1377949442_4553.jpg",
                "http://img.my.csdn.net/uploads/201308/31/1377949441_8987.jpg",
                "http://img.my.csdn.net/uploads/201308/31/1377949441_5454.jpg",
                "http://img.my.csdn.net/uploads/201308/31/1377949454_6367.jpg",
                "http://img.my.csdn.net/uploads/201308/31/1377949442_4562.jpg",
                "http://img.my.csdn.net/uploads/201407/26/1406383299_1976.jpg",
                "http://img.my.csdn.net/uploads/201407/26/1406383291_6518.jpg",
                "http://img.my.csdn.net/uploads/201407/26/1406383291_8239.jpg",
                "http://img.my.csdn.net/uploads/201407/26/1406383290_9329.jpg",
                "http://img.my.csdn.net/uploads/201407/26/1406383290_1042.jpg",
                "http://img.my.csdn.net/uploads/201407/26/1406383275_3977.jpg",
                "http://img.my.csdn.net/uploads/201407/26/1406383265_8550.jpg",
                "http://img.my.csdn.net/uploads/201407/26/1406383264_3954.jpg",
                "http://img.my.csdn.net/uploads/201407/26/1406383264_4787.jpg",
                "http://img.my.csdn.net/uploads/201407/26/1406383264_8243.jpg",
                "http://img.my.csdn.net/uploads/201407/26/1406383248_3693.jpg",
                "http://img.my.csdn.net/uploads/201407/26/1406383243_5120.jpg",
                "http://img.my.csdn.net/uploads/201407/26/1406383242_3127.jpg",
                "http://img.my.csdn.net/uploads/201407/26/1406383242_9576.jpg",
                "http://img.my.csdn.net/uploads/201407/26/1406383242_1721.jpg",
                "http://img.my.csdn.net/uploads/201407/26/1406383219_5806.jpg",
                "http://img.my.csdn.net/uploads/201407/26/1406383214_7794.jpg",
                "http://img.my.csdn.net/uploads/201407/26/1406383213_4418.jpg",
                "http://img.my.csdn.net/uploads/201407/26/1406383213_3557.jpg",
                "http://img.my.csdn.net/uploads/201407/26/1406383210_8779.jpg",
                "http://img.my.csdn.net/uploads/201407/26/1406383172_4577.jpg",
                "http://img.my.csdn.net/uploads/201407/26/1406383166_3407.jpg",
                "http://img.my.csdn.net/uploads/201407/26/1406383166_2224.jpg",
                "http://img.my.csdn.net/uploads/201407/26/1406383166_7301.jpg",
                "http://img.my.csdn.net/uploads/201407/26/1406383165_7197.jpg",
                "http://img.my.csdn.net/uploads/201407/26/1406383150_8410.jpg",
                "http://img.my.csdn.net/uploads/201407/26/1406383131_3736.jpg",
                "http://img.my.csdn.net/uploads/201407/26/1406383130_5094.jpg",
                "http://img.my.csdn.net/uploads/201407/26/1406383130_7393.jpg",
                "http://img.my.csdn.net/uploads/201407/26/1406383129_8813.jpg",
                "http://img.my.csdn.net/uploads/201407/26/1406383100_3554.jpg",
                "http://img.my.csdn.net/uploads/201407/26/1406383093_7894.jpg",
                "http://img.my.csdn.net/uploads/201407/26/1406383092_2432.jpg",
                "http://img.my.csdn.net/uploads/201407/26/1406383092_3071.jpg",
                "http://img.my.csdn.net/uploads/201407/26/1406383091_3119.jpg",
                "http://img.my.csdn.net/uploads/201407/26/1406383059_6589.jpg",
                "http://img.my.csdn.net/uploads/201407/26/1406383059_8814.jpg",
                "http://img.my.csdn.net/uploads/201407/26/1406383059_2237.jpg",
                "http://img.my.csdn.net/uploads/201407/26/1406383058_4330.jpg",
                "http://img.my.csdn.net/uploads/201407/26/1406383038_3602.jpg",
                "http://img.my.csdn.net/uploads/201407/26/1406382942_3079.jpg",
                "http://img.my.csdn.net/uploads/201407/26/1406382942_8125.jpg",
                "http://img.my.csdn.net/uploads/201407/26/1406382942_4881.jpg",
                "http://img.my.csdn.net/uploads/201407/26/1406382941_4559.jpg",
                "http://img.my.csdn.net/uploads/201407/26/1406382941_3845.jpg",
                "http://img.my.csdn.net/uploads/201407/26/1406382924_8955.jpg",
                "http://img.my.csdn.net/uploads/201407/26/1406382923_2141.jpg",
                "http://img.my.csdn.net/uploads/201407/26/1406382923_8437.jpg",
                "http://img.my.csdn.net/uploads/201407/26/1406382922_6166.jpg",
                "http://img.my.csdn.net/uploads/201407/26/1406382922_4843.jpg",
                "http://img.my.csdn.net/uploads/201407/26/1406382905_5804.jpg",
                "http://img.my.csdn.net/uploads/201407/26/1406382904_3362.jpg",
                "http://img.my.csdn.net/uploads/201407/26/1406382904_2312.jpg",
                "http://img.my.csdn.net/uploads/201407/26/1406382904_4960.jpg",
                "http://img.my.csdn.net/uploads/201407/26/1406382900_2418.jpg",
                "http://img.my.csdn.net/uploads/201407/26/1406382881_4490.jpg",
                "http://img.my.csdn.net/uploads/201407/26/1406382881_5935.jpg",
                "http://img.my.csdn.net/uploads/201407/26/1406382880_3865.jpg",
                "http://img.my.csdn.net/uploads/201407/26/1406382880_4662.jpg",
                "http://img.my.csdn.net/uploads/201407/26/1406382879_2553.jpg",
                "http://img.my.csdn.net/uploads/201407/26/1406382862_5375.jpg",
                "http://img.my.csdn.net/uploads/201407/26/1406382862_1748.jpg",
                "http://img.my.csdn.net/uploads/201407/26/1406382861_7618.jpg",
                "http://img.my.csdn.net/uploads/201407/26/1406382861_8606.jpg",
                "http://img.my.csdn.net/uploads/201407/26/1406382861_8949.jpg",
                "http://img.my.csdn.net/uploads/201407/26/1406382841_9821.jpg",
                "http://img.my.csdn.net/uploads/201407/26/1406382840_6603.jpg",
                "http://img.my.csdn.net/uploads/201407/26/1406382840_2405.jpg",
                "http://img.my.csdn.net/uploads/201407/26/1406382840_6354.jpg",
                "http://img.my.csdn.net/uploads/201407/26/1406382839_5779.jpg",
                "http://img.my.csdn.net/uploads/201407/26/1406382810_7578.jpg",
                "http://img.my.csdn.net/uploads/201407/26/1406382810_2436.jpg",
                "http://img.my.csdn.net/uploads/201407/26/1406382809_3883.jpg",
                "http://img.my.csdn.net/uploads/201407/26/1406382809_6269.jpg",
                "http://img.my.csdn.net/uploads/201407/26/1406382808_4179.jpg",
                "http://img.my.csdn.net/uploads/201407/26/1406382790_8326.jpg",
                "http://img.my.csdn.net/uploads/201407/26/1406382789_7174.jpg",
                "http://img.my.csdn.net/uploads/201407/26/1406382789_5170.jpg",
                "http://img.my.csdn.net/uploads/201407/26/1406382789_4118.jpg",
                "http://img.my.csdn.net/uploads/201407/26/1406382788_9532.jpg",
                "http://img.my.csdn.net/uploads/201407/26/1406382767_3184.jpg",
                "http://img.my.csdn.net/uploads/201407/26/1406382767_4772.jpg",
                "http://img.my.csdn.net/uploads/201407/26/1406382766_4924.jpg",
                "http://img.my.csdn.net/uploads/201407/26/1406382766_5762.jpg",
                "http://img.my.csdn.net/uploads/201407/26/1406382765_7341.jpg"
        };

        return imageThumbUrls;
    }

}
