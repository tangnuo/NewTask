package com.example.caowj.newtask.example.activity;

import android.os.Bundle;
import android.view.Window;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.caowj.newtask.R;
import com.kedacom.base.mvc.BaseActivity1;
import com.kedacom.utils.StatusBarCompat;

import butterknife.BindView;

/**
 * 沉浸式状态栏<br/>
 * http://blog.csdn.net/fan7983377/article/details/51604657
 */
public class TranslucentSystemBarActivity extends BaseActivity1 {

    @BindView(R.id.mWebView)
    WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        //设置标题栏颜色
        StatusBarCompat.compat(this, getResources().getColor(R.color.colorAccent));
//        StatusBarCompat.compat(this, Color.RED);

        String url = "http://blog.csdn.net/fan7983377/article/details/51604657";
//        String url = "http://www.jianshu.com/p/34a8b40b9308";
        mWebView.loadUrl(url);
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                //webView打开URl.
                return true;
            }
        });
        mWebView.loadUrl(url);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_translucent_system_bar;
    }

    @Override
    protected void memoryRecovery() {

    }
}
