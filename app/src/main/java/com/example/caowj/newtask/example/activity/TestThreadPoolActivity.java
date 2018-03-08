package com.example.caowj.newtask.example.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.caowj.newtask.R;
import com.example.caowj.newtask.base.BaseActivity;
import com.example.caowj.newtask.other.PausableThreadPoolExecutor;
import com.example.caowj.newtask.other.PriorityRunnable;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 线程池
 */
public class TestThreadPoolActivity extends BaseActivity {

    @BindView(R.id.btn_thread1)
    Button btnThread1;
    @BindView(R.id.btn_thread2)
    Button btnThread2;
    @BindView(R.id.btn_thread3)
    Button btnThread3;
    @BindView(R.id.btn_thread4)
    Button btnThread4;
    @BindView(R.id.btn_thread5)
    Button btnThread5;
    @BindView(R.id.btn_thread6)
    Button btnThread6;
    @BindView(R.id.btn_thread7)
    Button btnThread7;
    @BindView(R.id.btn_thread8)
    Button btnThread8;
    @BindView(R.id.btn_thread9)
    Button btnThread9;
    @BindView(R.id.btn_thread10)
    Button btnThread10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected int getContentView() {
        return R.layout.activity_test_thread_pool;
    }

    @Override
    protected void memoryRecovery() {

    }

    @OnClick({R.id.btn_thread1, R.id.btn_thread2, R.id.btn_thread3, R.id.btn_thread4, R.id.btn_thread5, R.id.btn_thread6, R.id.btn_thread7, R.id.btn_thread8, R.id.btn_thread9, R.id.btn_thread10})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_thread1:
                test1();
                break;
            case R.id.btn_thread2:
                test2();
                break;
            case R.id.btn_thread3:
                test3();
                break;
            case R.id.btn_thread4:
                test4();
                break;
            case R.id.btn_thread5:
                test5();
                break;
            case R.id.btn_thread6:
                test6();
                break;
            case R.id.btn_thread7:
                test7();
                break;
            case R.id.btn_thread8:
                break;
            case R.id.btn_thread9:
                break;
            case R.id.btn_thread10:
                break;
        }
    }

    private void test7() {
        PausableThreadPoolExecutor pausableThreadPoolExecutor = new PausableThreadPoolExecutor(1, 1, 0L, TimeUnit.SECONDS, new PriorityBlockingQueue<Runnable>());
        for (int i = 1; i <= 100; i++) {
            final int priority = i;
            pausableThreadPoolExecutor.execute(new PriorityRunnable(priority) {
                @Override
                public void doSth() {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Log.d("caowj", "当前线程的优先级：" + priority);
                        }
                    });
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }


////        可以定义变量控制按钮的开关：
//        if (isPause) {
//            pausableThreadPoolExecutor.resume();
//            isPause = false;
//        } else {
//            pausableThreadPoolExecutor.pause();
//            isPause = true;
//        }
    }

    /**
     * 自定义线程池ThreadPoolExecutor
     * <p>
     * 根据任务的优先级进行优先处理任务
     */
    private void test6() {
        ExecutorService priorityThreadPool = new ThreadPoolExecutor(3, 3, 0L, TimeUnit.SECONDS, new PriorityBlockingQueue<Runnable>());
        for (int i = 1; i <= 10; i++) {
            final int priority = i;
            priorityThreadPool.execute(new PriorityRunnable(priority) {
                @Override
                public void doSth() {
                    String threadName = Thread.currentThread().getName();
                    Log.v("zxy", "线程：" + threadName + ",正在执行优先级为：" + priority + "的任务");
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    private void test5() {
        ScheduledExecutorService singleThreadScheduledPool = Executors.newSingleThreadScheduledExecutor();
        //延迟1秒后，每隔2秒执行一次该任务
        singleThreadScheduledPool.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                String threadName = Thread.currentThread().getName();
                Log.v("zxy", "线程：" + threadName + ",正在执行");
            }
        }, 1, 2, TimeUnit.SECONDS);
    }

    private void test4() {
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(3);
        //延迟2秒后执行该任务
        scheduledThreadPool.schedule(new Runnable() {
            @Override
            public void run() {
                String threadName = Thread.currentThread().getName();
                Log.v("zxy", "线程：" + threadName + ",正在执行");
            }
        }, 2, TimeUnit.SECONDS);
        //延迟1秒后，每隔2秒执行一次该任务
        scheduledThreadPool.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                String threadName = Thread.currentThread().getName();
                Log.v("zxy", "线程：" + threadName + ",正在执行");
            }
        }, 1, 2, TimeUnit.SECONDS);
    }

    private void test3() {
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        for (int i = 1; i <= 10; i++) {
            final int index = i;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            cachedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    String threadName = Thread.currentThread().getName();
                    Log.v("zxy", "线程：" + threadName + ",正在执行第" + index + "个任务");
                    try {
                        long time = index * 500;
                        Thread.sleep(time);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    private void test2() {
        ExecutorService singleThreadPool = Executors.newSingleThreadExecutor();
        for (int i = 1; i <= 10; i++) {
            final int index = i;
            singleThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    String threadName = Thread.currentThread().getName();
                    Log.v("zxy", "线程：" + threadName + ",正在执行第" + index + "个任务");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    /**
     * 创建一个固定线程数量的线程池
     */
    private void test1() {
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);
        for (int i = 1; i <= 10; i++) {
            final int index = i;
            fixedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    String threadName = Thread.currentThread().getName();
                    Log.v("zxy", "线程：" + threadName + ",正在执行第" + index + "个任务");
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }
}
