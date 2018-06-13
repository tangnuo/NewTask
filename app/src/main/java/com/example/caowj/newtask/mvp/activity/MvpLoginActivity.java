package com.example.caowj.newtask.mvp.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.caowj.newtask.R;
import com.example.caowj.newtask.base.BaseActivity;
import com.example.caowj.newtask.mvp.presenter.impl.UserPresenter;
import com.example.caowj.newtask.mvp.view.IUserView;

/**
 * MVP基础案例（登录功能）
 */
public class MvpLoginActivity extends BaseActivity implements View.OnClickListener, IUserView {

    private EditText name, password;
    private ProgressBar progress;
    private UserPresenter presenter = new UserPresenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_mvp_login;
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        name = (EditText) findViewById(R.id.name);
        password = (EditText) findViewById(R.id.password);
        progress = (ProgressBar) findViewById(R.id.progress);
        findViewById(R.id.submit).setOnClickListener(this);
    }

    @Override
    protected void initData() {
        super.initData();
    }

    @Override
    protected void memoryRecovery() {

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.submit:
                presenter.login();
                break;
        }
    }


    @Override
    public String getName() {
        return name.getText().toString();
    }


    @Override
    public String getPassword() {
        return password.getText().toString();
    }


    @Override
    public void showProgress() {
        progress.setVisibility(View.VISIBLE);
    }


    @Override
    public void hideProgress() {
        progress.setVisibility(View.GONE);
    }


    @Override
    public void toastSuccess() {
        Toast.makeText(this, "登录成功！", Toast.LENGTH_SHORT).show();
    }


    @Override
    public void toastFailed() {
        Toast.makeText(this, "登录失败！", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void errorToast(String toastMessage) {
        Toast.makeText(this, toastMessage, Toast.LENGTH_SHORT).show();
    }

}
