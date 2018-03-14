package com.example.caowj.newtask.example.adapter;

import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.caowj.newtask.R;
import com.example.caowj.newtask.example.bean.Person2;
import com.example.caowj.newtask.example.viewHolder.SomethingHolder;
import com.example.caowj.newtask.utils.LogUtil;

import java.util.List;

/**
 * 自定义viewholder实现接口功能
 * <p>
 * 参考：https://github.com/FrankKwok/MultiItem
 * </p>
 * package: com.example.caowj.newtask.example.adapter
 * author: Administrator
 * date: 2017/9/14 15:18
 */
public class MultipleItemQuickAdapter3 extends BaseMultiItemQuickAdapter<Person2, BaseViewHolder> {
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public MultipleItemQuickAdapter3(List<Person2> data) {
        super(data);
        addItemType(Person2.TEXT, R.layout.item_list);
        addItemType(Person2.IMG, R.layout.item_list2);
    }

    @Override
    protected BaseViewHolder onCreateDefViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case Person2.TEXT:
                return new BaseViewHolder(getItemView(R.layout.item_list, parent));
            case Person2.IMG:
                return new SomethingHolder(getItemView(R.layout.item_list2, parent));
            default:
                return super.onCreateDefViewHolder(parent, viewType);
        }
    }

    @Override
    protected void convert(BaseViewHolder helper, Person2 item) {
        switch (helper.getItemViewType()) {
            case 1:
                helper.setText(R.id.tv_item, item.getName());
                break;
            case 2:
                // 转型后可使用相关功能
                SomethingHolder holder = (SomethingHolder) helper;
                holder.setText(R.id.tv_item2, item.getName());
                break;

            default:
                LogUtil.myD("....default....");
                break;
        }
    }
}
