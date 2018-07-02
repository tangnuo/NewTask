package com.example.caowj.newtask.example.activity;

import android.os.Bundle;
import android.os.Environment;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.TextView;

import com.example.caowj.newtask.R;
import com.example.caowj.newtask.example.bean.GankInfo;
import com.example.caowj.newtask.utils.business.MyAndroidUtils;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import com.kedacom.base.common.BaseHandler;
import com.kedacom.base.mvc.BaseButterKnifeActivity;
import com.kedacom.utils.GankUtil;
import com.kedacom.utils.GsonUtil;
import com.kedacom.utils.LogUtil;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * 使用OKHttp3
 * <p>
 * 参考写法，有些代码没有输出结果。
 * </p>
 * https://blog.csdn.net/fightingxia/article/details/70947701
 */
public class TestOkHttpActivity extends BaseButterKnifeActivity {

    public static final String TYPE = "application/octet-stream";
    private static final String POST_URL = "http://zhushou.72g.com/app/gift/gift_list/";
    @BindView(R.id.btn_test1)
    Button btnTest1;
    @BindView(R.id.btn_test2)
    Button btnTest2;
    @BindView(R.id.stub)
    ViewStub stub;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    private OkHttpClient client;
    private MyHandler myHandler = new MyHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tvTitle.setText("参考代码写法，有些代码没有输出结果。");
        initOkHttp();
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_test_ok_http;
    }

    @Override
    protected void memoryRecovery() {

    }

    private void initOkHttp() {
        client = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .build();


//        方法二：
//        client = new OkHttpClient();
    }


    @OnClick({R.id.btn_test1, R.id.btn_test2, R.id.stub})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_test1:

//                ThreadPoolManager.getInstance().addTask(new Runnable() {
//                    @Override
//                    public void run() {
//                        testGet();
//                    }
//                });

                testGetSynchronous();
                break;
            case R.id.btn_test2:
//                testPost();
//                testPost2();
                testPostForm();
                break;
            case R.id.stub:
                break;
        }
    }


    /**
     * 测试GET请求（异步）
     */
    private void testGetAsynchronous() {

        Request request = new Request.Builder()
                .get()
                .url(GankUtil.getUrl(4, 5, 1))
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String string = response.body().string();
//                        Log.i(TAG, "onResponse: "+string);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        LogUtil.myD(mTag + "get_onResponse: " + string);
                    }
                });
            }
        });
    }

    /**
     * 测试GET请求（同步）
     */
    private void testGetSynchronous() {
        String url = GankUtil.getUrl(1, 10, 1);

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response;
        try {
            response = client.newCall(request).execute();
            String ss = response.body().string();
//            LogUtil.myD(mTag + "请求结果1：" + ss);

            sendMsg(100, ss);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 测试POST请求(提交表单)
     */
    private void testPostForm() {
        //    请求条件：platform=2&gifttype=2&compare=60841c5b7c69a1bbb3f06536ed685a48
        //    请求参数：page=1&code=news&pageSize=20&parentid=0&type=1

        RequestBody requestBodyPost = new FormBody.Builder()
                .add("page", "1")
                .add("code", "news")
                .add("pageSize", "20")
                .add("parentid", "0")
                .add("type", "1")
                .build();
        Request requestPost = new Request.Builder()
                .url(POST_URL)
                .post(requestBodyPost)
                .build();
        client.newCall(requestPost).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String string = response.body().string();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        LogUtil.myD(mTag + "post_onResponse: " + string);
                    }
                });
            }
        });
    }

    /**
     * 测试POST请求(提交文件)
     */
    private void testPostFile() {
        File file = new File(Environment.getExternalStorageDirectory(), "dd.mp4");
        if (!file.exists()) {
            MyAndroidUtils.showShortToast(mActivity, "文件不存在");
        } else {
            RequestBody fileBody = RequestBody.create(MediaType.parse(TYPE), file);

//            使用MultipartBody同时传递键值对参数和File对象(传递多重的body)
//            RequestBody requestBody = new MultipartBody.Builder().addFormDataPart("filename", file.getName(), fileBody).build();

            Request requestPostFile = new Request.Builder()
                    .url("http://10.11.64.50/upload/UploadServlet")
                    .post(fileBody)
                    .build();
            client.newCall(requestPostFile).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                }

                @Override
                public void onResponse(Call call, final Response response) throws IOException {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            LogUtil.myD(mTag + "post_onResponse: " + response.toString());
                        }
                    });
                }
            });
        }
    }

    /**
     * 测试POST请求（提交JSON）
     */
    private void testPostJson() {
        OkHttpClient client = new OkHttpClient();//创建OkHttpClient对象。
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");//数据类型为json格式，
        String jsonStr = "{\"username\":\"lisi\",\"nickname\":\"李四\"}";//json数据.
        RequestBody body = RequestBody.create(JSON, jsonStr);

        Request request = new Request.Builder()//创建Request 对象。
                .url("http://www.baidu.com")
                .post(body)//传递请求体
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                LogUtil.myW(mTag + ",,," + e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {//回调的方法执行在子线程。
                    Log.d("kwwl", "获取数据成功了");
                    Log.d("kwwl", "response.code()==" + response.code());
                    Log.d("kwwl", "response.body().string()==" + response.body().string());
                }
            }
        });//回调方法的使用与get异步请求相同，此时略。
    }

    /**
     * 测试POST请求（MultipartBody,多重body）
     */
    private void testPostMulti() {
        File file = new File(Environment.getExternalStorageDirectory(), "dd.mp4");
        if (!file.exists()) {
            MyAndroidUtils.showShortToast(mActivity, "文件不存在");
        } else {
            OkHttpClient client = new OkHttpClient();
            MultipartBody multipartBody = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("groupId", "1104")//添加键值对参数
                    .addFormDataPart("title", "title")
                    .addFormDataPart("file", file.getName(), RequestBody.create(MediaType.parse("file/*"), file))//添加文件
                    .build();
            final Request request = new Request.Builder()
                    .url("https://www.baidu.com/")
                    .post(multipartBody)
                    .build();


            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    LogUtil.myW(mTag + ",,," + e.getMessage());
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    if (response.isSuccessful()) {//回调的方法执行在子线程。
                        Log.d("kwwl", "获取数据成功了");
                        Log.d("kwwl", "response.code()==" + response.code());
                        Log.d("kwwl", "response.body().string()==" + response.body().string());
                    }
                }
            });//回调方法的使用与get异步请求相同，此时略。
        }
    }

    /**
     * 使用handler发送消息
     *
     * @param code
     * @param object
     */
    private void sendMsg(int code, Object object) {
        Message message = new Message();
        message.what = code;
        message.obj = object;
        myHandler.sendMessage(message);
    }

    /**
     * 解析json数据
     *
     * @param jsonStr
     */
    private void getDataFromJson(String jsonStr) {
        JsonParser parser = new JsonParser();
        JsonElement jsonElement = parser.parse(jsonStr);
        JsonObject jsonObj = jsonElement.getAsJsonObject();

        JsonPrimitive primitive = jsonObj.getAsJsonPrimitive("error");
        Boolean error = primitive.getAsBoolean();

        JsonElement jsonElement2 = jsonObj.get("results");
        List<GankInfo> tempList = GsonUtil.parserArrayStrToBeans(jsonElement2.toString(), GankInfo.class);

        LogUtil.myD(mTag + "error:" + error + ",,size:" + tempList.size());
    }

    private static class MyHandler extends BaseHandler<TestOkHttpActivity> {


        public MyHandler(TestOkHttpActivity referencedObject) {
            super(referencedObject);
        }

        @Override
        public void handleMessage2(Message msg, int what) {
            TestOkHttpActivity testOkHttpActivity = getWeakReference();

            String jsonStr = (String) msg.obj;
            switch (msg.what) {
                case 100:
                    testOkHttpActivity.getDataFromJson(jsonStr);
                    break;
            }

        }
    }

}
