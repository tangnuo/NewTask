package com.kedacom.module_news;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.kedacom.module_common.constant.RouteConstants;
import com.kedacom.module_lib.base.common.BaseActivity;

@Route(path = RouteConstants.NEWS_ACTIVITY)
public class NewsActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
    }
}
