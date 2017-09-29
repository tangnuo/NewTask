package com.example.caowj.newtask.example.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.caowj.newtask.R;
import com.example.caowj.newtask.example.mDragger.ApiService;
import com.example.caowj.newtask.example.mDragger.DaggerUserComponent;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TestDaggerActivity extends AppCompatActivity {

    @Inject
    ApiService apiService;
    @BindView(R.id.tv_hint)
    TextView tvHint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_dragger);
        ButterKnife.bind(this);

        tvHint.setText("@Inject标识需要使用依赖\n@Modules标识提供依赖，内部通过@Provide具体操作\n@Component是连接的桥梁");


        //Dagger会自动创建这个类，以Dagger开头+UserComponent
        DaggerUserComponent.create().inject(this);
        apiService.register(this);
    }
}
