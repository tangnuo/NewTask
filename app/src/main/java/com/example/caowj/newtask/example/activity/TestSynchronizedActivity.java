package com.example.caowj.newtask.example.activity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewStub;
import android.widget.Button;

import com.example.caowj.newtask.R;
import com.example.caowj.newtask.base.BaseActivity;
import com.example.caowj.newtask.other.thread.Counter;
import com.example.caowj.newtask.other.thread.SyncThread;
import com.example.caowj.newtask.other.thread.SyncThread2;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * synchronized的用法<br/>
 * https://blog.csdn.net/luoweifu/article/details/46613015
 */
public class TestSynchronizedActivity extends BaseActivity {

    @BindView(R.id.btn_test1)
    Button btnTest1;
    @BindView(R.id.btn_test2)
    Button btnTest2;
    @BindView(R.id.stub)
    ViewStub stub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_test_ui;
    }

    @Override
    protected void memoryRecovery() {

    }

    @OnClick({R.id.btn_test1, R.id.btn_test2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_test1:
                test1();
                break;
            case R.id.btn_test2:

                break;
        }
    }


    /**
     * synchronized修饰代码块,相同对象。(synchronized只锁定对象)
     */
    private void test1() {
        SyncThread syncThread = new SyncThread();
        Thread thread1 = new Thread(syncThread, "SyncThread1");
        Thread thread2 = new Thread(syncThread, "SyncThread2");
        thread1.start();
        thread2.start();
    }


    /**
     * synchronized修饰代码块，不同对象。(synchronized只锁定对象)
     */
    private void test2() {
        SyncThread syncThread = new SyncThread();
        SyncThread syncThread2 = new SyncThread();
        Thread thread1 = new Thread(syncThread, "SyncThread1");
        Thread thread2 = new Thread(syncThread2, "SyncThread2");
        thread1.start();
        thread2.start();
    }

    private void test3() {
        Counter counter = new Counter();
        Thread thread1 = new Thread(counter, "A");
        Thread thread2 = new Thread(counter, "B");
        thread1.start();
        thread2.start();
    }

    /**
     * synchronized修饰静态方法，不同对象。（synchronized锁定了类。）
     * <br/>对比test2() {@link TestSynchronizedActivity#test2()}
     */
    private void test4() {
        SyncThread2 syncThread1 = new SyncThread2();
        SyncThread2 syncThread2 = new SyncThread2();
        Thread thread1 = new Thread(syncThread1, "SyncThread1");
        Thread thread2 = new Thread(syncThread2, "SyncThread2");
        thread1.start();
        thread2.start();
    }

}
