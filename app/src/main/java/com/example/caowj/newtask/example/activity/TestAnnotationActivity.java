package com.example.caowj.newtask.example.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.caowj.newtask.R;
import com.example.caowj.newtask.example.helper.annotation.InjectView;
import com.example.caowj.newtask.example.helper.annotation.InjectViewUtils;
import com.example.caowj.newtask.example.helper.annotation.OnClick;

/**
 * 用注解加反射实现的类似ButterKnife功能
 * <p>
 * <br/>
 * https://blog.csdn.net/silenceoo/article/details/77620017
 */
public class TestAnnotationActivity extends AppCompatActivity {

    @InjectView(R.id.button4)
    Button button4;
    @InjectView(R.id.button5)
    Button button5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_annotation);
        InjectViewUtils.inject(this);

        button4.setText("点击我111啊！！！");
        button5.setText("点击我222啊！！！");
    }

    @OnClick({R.id.button4, R.id.button5})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.button4:
                Toast.makeText(this, "我是按钮11111", Toast.LENGTH_SHORT).show();
                break;
            case R.id.button5:
                Toast.makeText(this, "我是按钮22222", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
