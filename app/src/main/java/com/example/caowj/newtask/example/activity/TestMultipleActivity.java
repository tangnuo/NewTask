package com.example.caowj.newtask.example.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.caowj.newtask.other.multipleThread.DownloadUtil;

/**
 * 多线程下载、断点续传
 *
 * @author Caowj
 * @Date 2018/8/16 13:11
 */
public class TestMultipleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        test1();
    }

    private void test1() {
        String path = "http://localhost:8080/healthcare/html/HealthCare.war";
        String targetPath = "D:/healthcare.war";
        final DownloadUtil download = new DownloadUtil(path, targetPath, 4);
        download.download();
        // 主线程负责下载文件，在启动一个线程负责监控下载的进度
        new Thread(new Runnable() {

            @Override
            public void run() {
                while (download.getDownRate() < 1) {
                    System.out.println(download.getDownRate());
                    try {
                        Thread.sleep(200); // 200毫秒扫描一次
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

        }).start();
    }
}
