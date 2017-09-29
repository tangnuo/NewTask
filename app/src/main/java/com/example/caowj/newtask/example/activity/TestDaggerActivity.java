package com.example.caowj.newtask.example.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.caowj.newtask.R;
import com.example.caowj.newtask.base.BaseActivity;
import com.example.caowj.newtask.example.mDagger.ApiService;
import com.example.caowj.newtask.example.mDagger.DaggerUserComponent;
import com.example.caowj.newtask.example.mDagger.UserManager;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 首次集成dagger2<br/>
 *
 * http://blog.csdn.net/chenrushui/article/details/71426097
 */
public class TestDaggerActivity extends BaseActivity {

    @BindView(R.id.tv_hint)
    TextView tvHint;


    @Inject
    ApiService apiService;
    @Inject
    UserManager userManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);

        tvHint.setText("@Inject标识需要使用依赖\n\n@Modules标识提供依赖，内部通过@Provide具体操作\n\n@Component是连接的桥梁");


        //主要两个问题:
        //1）自定义Module需要传递上下文怎么办
        //2）自定义Module中的方法中需要参数对象怎么处理？(构造函数、自己提供方法)


        //Dagger会自动创建这个类，以Dagger开头+UserComponent
        DaggerUserComponent.create().inject(this);

        //create()等同于builder().userModule(new UserModule()).build()
//        DaggerUserComponent.builder().userModule(new UserModule()).build().inject(this);


        //Dagger的关系非常简单，MainActivity中需要对象，那么就在Module中提供对象；而他们之间的桥梁就是componnent

//        测试代码1
//        apiService.register(this);


//        测试代码2:
        userManager.register(this);


    }


    @Override
    protected int getContentView() {
        return R.layout.activity_test_dragger;
    }

    @Override
    protected void memoryRecovery() {

    }
}
