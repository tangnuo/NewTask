package com.example.caowj.newtask.example.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.caowj.newtask.R;
import com.example.caowj.newtask.example.bean.Person;

import java.util.List;

/**
 * 基于BRVAH的通用Adapter
 * @deprecated
 * package: com.example.caowj.newtask.example.adapter
 * author: Administrator
 * date: 2017/9/14 15:06
 */
public class CommonAdapter3 extends BaseQuickAdapter<Person, BaseViewHolder> {
    public CommonAdapter3(@Nullable List<Person> data) {
        super(data);
    }

    public CommonAdapter3(int item_list, List<Person> personList) {
        super(item_list, personList);
    }

    @Override
    protected void convert(BaseViewHolder helper, Person item) {
        helper.setText(R.id.tv_item, item.getName());
    }
}
