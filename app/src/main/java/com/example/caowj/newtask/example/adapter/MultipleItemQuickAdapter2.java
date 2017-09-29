package com.example.caowj.newtask.example.adapter;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.caowj.newtask.R;
import com.example.caowj.newtask.example.bean.Person2;
import com.example.caowj.newtask.utils.LogUtil;

import java.util.List;

/**
 * package: com.example.caowj.newtask.example.adapter
 * author: Administrator
 * date: 2017/9/14 15:18
 */
public class MultipleItemQuickAdapter2 extends BaseMultiItemQuickAdapter<Person2, BaseViewHolder> {
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public MultipleItemQuickAdapter2(List<Person2> data) {
        super(data);
        addItemType(Person2.TEXT, R.layout.item_list);
        addItemType(Person2.IMG, R.layout.item_list2);
    }


    @Override
    protected void convert(BaseViewHolder helper, Person2 item) {
        switch (helper.getItemViewType()) {
            case 1:
                helper.setText(R.id.tv_item, item.getName());
                break;
            case 2:
                helper.setText(R.id.tv_item2, item.getName());
                break;

            default:
                LogUtil.myD("....default....");
                break;
        }
    }
}
