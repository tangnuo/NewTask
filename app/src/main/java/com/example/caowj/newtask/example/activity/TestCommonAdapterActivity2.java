package com.example.caowj.newtask.example.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.caowj.newtask.R;
import com.example.caowj.newtask.base.BaseActivity;
import com.example.caowj.newtask.example.adapter.MultipleItemQuickAdapter2;
import com.example.caowj.newtask.example.adapter.TestCommonAdapterAdapter2;
import com.example.caowj.newtask.example.bean.Person;
import com.example.caowj.newtask.example.bean.Person2;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 使用：https://github.com/CymChad/BaseRecyclerViewAdapterHelper
 */
public class TestCommonAdapterActivity2 extends BaseActivity {

    @BindView(R.id.mRecyclerView)
    RecyclerView mRecyclerView;
    private List<Person> personList;
    private List<Person2> personList2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        singleType();
        multipleType();
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_test_common_adapter2;
    }

    @Override
    protected void initData() {
        super.initData();
        personList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Person person = new Person("single--" + i);
            personList.add(person);
        }

        personList2 = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            //数据组装时，注意Type
            if (i > 4) {
                Person2 person3 = new Person2(Person2.IMG, "multiple--" + i);
                personList2.add(person3);
            } else {
                Person2 person2 = new Person2(Person2.TEXT, "multiple--" + i);
                personList2.add(person2);
            }
        }

        mRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
    }

    @Override
    protected void memoryRecovery() {

    }

    /**
     * 一种Type
     */
    private void singleType() {

        TestCommonAdapterAdapter2 testCommonAdapterAdapter2 = new TestCommonAdapterAdapter2(R.layout.item_list, personList);
        mRecyclerView.setAdapter(testCommonAdapterAdapter2);
    }

    /**
     * 多种Type类型，存在严重Bug.
     */
    private void multipleType() {
        MultipleItemQuickAdapter2 multipleItemAdapter = new MultipleItemQuickAdapter2(personList2);
        mRecyclerView.setAdapter(multipleItemAdapter);
    }
}
