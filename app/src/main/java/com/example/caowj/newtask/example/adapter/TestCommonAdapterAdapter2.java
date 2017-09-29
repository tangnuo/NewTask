package com.example.caowj.newtask.example.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.caowj.newtask.R;
import com.example.caowj.newtask.example.bean.Person;

import java.util.List;

/**
 * package: com.example.caowj.newtask.example.adapter
 * author: Administrator
 * date: 2017/9/14 15:06
 */
public class TestCommonAdapterAdapter2 extends BaseQuickAdapter<Person, BaseViewHolder> {
    public TestCommonAdapterAdapter2(@Nullable List<Person> data) {
        super(data);
    }

    public TestCommonAdapterAdapter2(int item_list, List<Person> personList) {
        super(item_list, personList);
    }

    @Override
    protected void convert(BaseViewHolder helper, Person item) {
        helper.setText(R.id.tv_item, item.getName());
    }
}
