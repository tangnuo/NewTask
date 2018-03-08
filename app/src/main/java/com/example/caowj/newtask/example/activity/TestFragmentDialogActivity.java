package com.example.caowj.newtask.example.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.caowj.newtask.R;
import com.example.caowj.newtask.base.BaseActivity;
import com.example.caowj.newtask.widget.MyDialogFragment1;
import com.example.caowj.newtask.widget.MyDialogFragment2;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * FragmentDialog替代AlertDialog
 */
public class TestFragmentDialogActivity extends BaseActivity {

    @BindView(R.id.btn_test)
    Button btnTest;
    @BindView(R.id.btn_test2)
    Button btnTest2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    protected int getContentView() {
        return R.layout.activity_test_fragment_dialog;
    }

    @Override
    protected void memoryRecovery() {

    }

    @OnClick({R.id.btn_test, R.id.btn_test2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_test:
                MyDialogFragment1.getInstance(false).show(getSupportFragmentManager(), "dialog_fragment");
                break;
            case R.id.btn_test2:
                MyDialogFragment2.getInstance().show(getSupportFragmentManager(), "alertDialog");
                break;
        }
    }
}
