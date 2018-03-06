package com.example.caowj.newtask.example.activity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewStub;
import android.widget.Button;

import com.example.caowj.newtask.R;
import com.example.caowj.newtask.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class TestUIActivity extends BaseActivity {

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
                // 这里调用的是inflate方法，当然，也可以调用setVisibility方法（但是不建议这么做）
                // 只能点击一次加载视图按钮，因为inflate只能被调用一次
                // 如果再次点击按钮，会抛出异常"ViewStub must have a non-null ViewGroup viewParent"
                View lazyView = stub.inflate();
                btnTest1.setEnabled(false);
                break;
            case R.id.btn_test2:

                break;
        }
    }
}
