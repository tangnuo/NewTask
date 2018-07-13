package com.example.caowj.newtask.example.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;

import com.example.caowj.newtask.R;
import com.example.caowj.newtask.designPattern.TestDesignPatternActivity;
import com.example.caowj.newtask.example.adapter.FunctionListAdapter;
import com.example.caowj.newtask.mvp.activity.MvpLoginActivity;
import com.example.caowj.newtask.mvp.activity.QipaiIndexActivity;
import com.example.caowj.newtask.mvp.activity.QipaiNewActivity;
import com.kedacom.base.mvc.BaseButterKnifeActivity;
import com.kedacom.utils.LogUtil;

import butterknife.BindView;

public class FunctionListActivity extends BaseButterKnifeActivity {

    @BindView(R.id.mRecyclerView)
    RecyclerView mRecyclerView;

    private SparseArray<Class> sparseArray;
    private FunctionListAdapter functionListAdapter;

    public static void newInstance(Context context) {
        //显示Intent
//        Intent intent = new Intent(context, FunctionListActivity.class);
//        context.startActivity(intent);

//        隐式Intent
        String mAction = context.getPackageName() + ".FunctionList";
        Intent intent = new Intent(mAction);
        context.startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtil.myD("44444");

        mRecyclerView.setAdapter(functionListAdapter);
    }

    @Override
    protected int getContentView() {
        return R.layout.common_layout_recyclerview;
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        LogUtil.myD("222222");
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
    }

    /**
     *
     */
    @Override
    protected void initData() {
        super.initData();
//        nameList = new ArrayList<>();
//        nameList.add("底部导航栏1");
//        nameList.add("底部导航栏2");
//        nameList.add("底部导航栏3");
//        nameList.add("底部导航栏4");
//        nameList.add("底部导航栏5");
//        nameList.add("底部导航栏6");

        sparseArray = new SparseArray<>();
        sparseArray.put(0, BottomNavigationActivity.class);
        sparseArray.put(1, MvpLoginActivity.class);
        sparseArray.put(2, TranslucentSystemBarActivity.class);
        sparseArray.put(3, SwipeRefreshActivity.class);
        sparseArray.put(4, ElemSearchActivity.class);
        sparseArray.put(5, IndexableListActivity.class);
        sparseArray.put(6, IndexableListActivity2.class);
        sparseArray.put(7, ViewShowActivity.class);
        sparseArray.put(8, ZoomImageActivity.class);
        sparseArray.put(9, GestureDetectorActivity.class);
        sparseArray.put(10, SearchViewActivity.class);
        sparseArray.put(11, TestCommonAdapterActivity1.class);
        sparseArray.put(12, TestCommonAdapterActivity2.class);
        sparseArray.put(13, TestMinaActivity.class);
        sparseArray.put(14, TestDaggerActivity.class);
        sparseArray.put(15, TestDaggerActivity2.class);
        sparseArray.put(16, TestDaggerActivity3.class);
        sparseArray.put(17, TextViewShowHtmlActivity.class);
        sparseArray.put(18, TestRrmActivity.class);
        sparseArray.put(19, QipaiNewActivity.class);
        sparseArray.put(20, ElemTabActivity.class);
        sparseArray.put(21, TestGreenDaoActivity.class);
        sparseArray.put(22, TestLruCacheActivity.class);
        sparseArray.put(23, TestLruCacheActivity2.class);
        sparseArray.put(24, TextSwitcherActivity.class);
        sparseArray.put(25, TestThreadPoolActivity.class);
        sparseArray.put(26, TestAudioActivity.class);
        sparseArray.put(27, TestAudioListActivity.class);
        sparseArray.put(28, TestTextureViewActivity.class);
        sparseArray.put(29, TestIntentServiceActivity.class);
        sparseArray.put(30, TestFragmentDialogActivity.class);
        sparseArray.put(31, TestUIActivity.class);
        sparseArray.put(32, TestNestedScrollActivity.class);
        sparseArray.put(33, QipaiIndexActivity.class);
        sparseArray.put(34, TestCountdownActivity.class);
        sparseArray.put(35, TestOpenGLActivity.class);
        sparseArray.put(36, TestSynchronizedActivity.class);
        sparseArray.put(37, TestWeiboDetailActivity.class);
        sparseArray.put(38, TestVelocityTrackerActivity.class);
        sparseArray.put(39, TestMasterVoiceActivity.class);
        sparseArray.put(40, TestTaoBaoDetailActivity.class);
        sparseArray.put(41, TestOkHttpActivity.class);
        sparseArray.put(42, TestAnnotationActivity.class);
        sparseArray.put(43, TestReflectActivity.class);
        sparseArray.put(44, TestFingerPrintActivity.class);
        sparseArray.put(45, TestPermissionActivity.class);
        sparseArray.put(46, TestDesignPatternActivity.class);
        sparseArray.put(47, TestSideLetterActivity.class);
        sparseArray.put(48, ConstraintLayoutTestActivity.class);
        sparseArray.put(49, TestPagingActivity.class);
        sparseArray.put(50, TestDownloadManagerActivity.class);


        functionListAdapter = new FunctionListAdapter(mActivity, sparseArray);
    }

    @Override
    protected void memoryRecovery() {

    }
}
