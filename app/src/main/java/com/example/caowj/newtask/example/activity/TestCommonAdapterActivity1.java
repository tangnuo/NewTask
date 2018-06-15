package com.example.caowj.newtask.example.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.caowj.newtask.R;
import com.example.caowj.newtask.example.bean.Person;
import com.kedacom.base.adapter.CommonAdapter;
import com.kedacom.base.adapter.CommonViewHolder;
import com.kedacom.base.adapter.MultiItemCommonAdapter;
import com.kedacom.base.adapter.MultiItemTypeSupport;
import com.kedacom.base.mvc.BaseButterKnifeActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * RecyclerView的共同Adapter（鸿洋）<p/>
 * <p>
 * 基础版：http://blog.csdn.net/lmj623565791/article/details/51118836/
 * <p>
 * 升级版（头部、加载更多、停止维护了）：https://github.com/hongyangAndroid/base-adapter
 */
public class TestCommonAdapterActivity1 extends BaseButterKnifeActivity {

    @BindView(R.id.mRecyclerView)
    RecyclerView mRecyclerView;

    private List<String> mDatas;
    private List<Person> personList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
//        singleType();
        multipleType();
    }

    @Override
    protected void initData() {
        super.initData();
        mDatas = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            mDatas.add("adapter--" + i);
        }

        personList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Person person = new Person("test--" + i);
            personList.add(person);
        }
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_test_common_adapter1;
    }

    @Override
    protected void memoryRecovery() {

    }

    /**
     * 一种Type
     */
    private void singleType() {

        mRecyclerView.setAdapter(new CommonAdapter<String>(mActivity, R.layout.item_list, mDatas) {
            @Override
            public void convert(CommonViewHolder holder, String s) {
                TextView tv = holder.getView(R.id.tv_item);
                tv.setText(s);
            }
        });
    }

    /**
     * 多种Type类型，存在严重Bug.<br/>
     * <p>
     * 稳定版参考：https://github.com/hongyangAndroid/base-adapter
     */
    private void multipleType() {
        final MultiItemTypeSupport multiItemSupport = new MultiItemTypeSupport<Person>() {
            @Override
            public int getLayoutId(int itemType) {
                //根据itemType返回item布局文件id
                if (itemType == 1) {
                    return R.layout.item_list;
                } else if (itemType == 2) {
                    return R.layout.item_list2;
                } else {

                    return 0;
                }
            }

            @Override
            public int getItemViewType(int postion, Person msg) {
                //根据当前的bean返回item type
                if (postion % 2 == 0) {
                    return 2;
                } else {
                    return 1;
                }
            }
        };


        mRecyclerView.setAdapter(new MultiItemCommonAdapter<Person>(mActivity, personList, multiItemSupport) {

            @Override
            public void convert(CommonViewHolder holder, Person s) {
                //TODO 需要优化，不同的布局有不同的ID.

                holder.setText(R.id.tv_item, s.getName());
                holder.setText(R.id.tv_item2, s.getName());

//                if (holder instanceof ViewHolder1) {
//                    holder.setText(R.id.tv_item, s.getName());
//                } else if (holder instanceof ViewHolder2) {
//                    holder.setText(R.id.tv_item2, s.getName());
//                }else{
//                    LogUtil.myD(mTag+"未知的ViewHolder");
//                }
            }
        });
    }


    class ViewHolder1 extends CommonViewHolder {
        @BindView(R.id.tv_item)
        TextView tvItem;

        ViewHolder1(View view) {
            super(mActivity, view, null);
            ButterKnife.bind(this, view);
        }
    }

    class ViewHolder2 extends CommonViewHolder {
        @BindView(R.id.tv_item2)
        TextView tvItem2;

        ViewHolder2(View view) {
            super(mActivity, view, null);
            ButterKnife.bind(this, view);
        }
    }
}
