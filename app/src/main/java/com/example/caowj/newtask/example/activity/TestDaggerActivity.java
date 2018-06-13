package com.example.caowj.newtask.example.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.caowj.newtask.R;
import com.example.caowj.newtask.base.BaseActivity;
import com.example.caowj.newtask.example.mDagger.ApiService;
import com.example.caowj.newtask.example.mDagger.UserManager;
import com.example.caowj.newtask.example.mDagger.UserTools;
import com.example.caowj.newtask.example.mDagger.component.DaggerMultiModuleComponent;
import com.example.caowj.newtask.example.mDagger.component.DaggerUserComponent;
import com.example.caowj.newtask.example.mDagger.module.HttpModule;
import com.example.caowj.newtask.example.mDagger.module.UserModule;
import com.kedacom.utils.LogUtil;

import javax.inject.Inject;
import javax.inject.Named;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 首次集成dagger2<br/>
 * <p>
 * http://blog.csdn.net/chenrushui/article/details/71426097
 */
public class TestDaggerActivity extends BaseActivity {

    @BindView(R.id.tv_hint)
    TextView tvHint;


    @Inject
    ApiService apiService;
    @Inject
    UserManager userManager;

    @Named("dev")
    @Inject
    UserTools userToolsDev;

    @Named("release")
    @Inject
    UserTools UserToolsRelease;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);

        tvHint.setText("@Inject标识需要使用依赖\n\n@Modules标识提供依赖，内部通过@Provide具体操作\n\n@Component是连接的桥梁");


//        test1();
//        test2();
//        test3();
        test4();

    }

    private void test1() {
        LogUtil.myD("test1...");
        //Dagger会自动创建这个类，以Dagger开头+UserComponent
        DaggerUserComponent.create().inject(this);
        apiService.register(this);
    }

    /**
     * 测试：module类中的方法需要对象参数
     */
    private void test2() {
        LogUtil.myD("test2...");
        DaggerUserComponent.builder().userModule(new UserModule(this)).build().inject(this);
        userManager.register(this);
    }

    /**
     * 通过@Named @Qualifier：区别不同对象的实例
     * 比如：访问测试服务器，访问正式服务器
     */
    private void test3() {
        LogUtil.myD("test3...");
        DaggerUserComponent.builder().userModule(new UserModule(this)).build().inject(this);

//此时这两个两个对象的内存地址是不一样的
        LogUtil.myD(userToolsDev.toString());
        LogUtil.myD(UserToolsRelease.toString());

        userToolsDev.getUserInfo(this);
        UserToolsRelease.getUserInfo(this);
    }

    /**
     * 自定义module之间相互include，component之间相互依赖
     */
    private void test4() {
        LogUtil.myD("test4...");
        DaggerMultiModuleComponent.builder().httpModule(new HttpModule()).userModule(new UserModule()).build().inject(this);
        apiService.register(this);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_test_dragger;
    }

    @Override
    protected void memoryRecovery() {

    }


}
