package com.example.caowj.newtask;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.caowj.newtask.base.BaseActivity;
import com.example.caowj.newtask.example.activity.FunctionListActivity;
import com.example.caowj.newtask.utils.AppIconManager;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.btn_go)
    Button btnGo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new AppIconManager().pmTest(this);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        btnGo.setText("去吧");
    }

    @Override
    protected void initData() {
        super.initData();
    }

    @Override
    protected void memoryRecovery() {

    }

    @OnClick(R.id.btn_go)
    public void onViewClicked() {
        FunctionListActivity.newInstance(this);
    }
}
